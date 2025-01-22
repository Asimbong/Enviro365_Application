package asimbongembende.service;

import com.enviro.assessment.grad001.asimbongembende.domain.WasteCategory;
import com.enviro.assessment.grad001.asimbongembende.factory.WasteCategoryFactory;
import com.enviro.assessment.grad001.asimbongembende.repository.WasteCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing WasteCategories.
 */
@Service
public class WasteCategoryService {
    private final WasteCategoryRepository repository;

    /**
     * Constructor for WasteCategoryService.
     *
     * @param repository the WasteCategoryRepository to be used
     */
    public WasteCategoryService(WasteCategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all waste categories.
     *
     * @return a list of all WasteCategories
     */
    public List<WasteCategory> findAll() {
        return repository.findAll();
    }

    /**
     * Get a waste category by ID.
     *
     * @param id the ID of the WasteCategory to retrieve
     * @return an Optional containing the WasteCategory if found, or empty if not found
     */
    public Optional<WasteCategory> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Create a new waste category.
     *
     * @param name the name of the WasteCategory
     * @param pricePerKg the price per kilogram of the WasteCategory
     * @return the created WasteCategory
     */
    public WasteCategory createWasteCategory(String name, double pricePerKg) {
        WasteCategory wasteCategory = WasteCategoryFactory.createWasteCategory(name, pricePerKg);
        return repository.save(wasteCategory);
    }

    /**
     * Update an existing waste category.
     * @param id the ID of the WasteCategory to update
     * @param name the new name of the WasteCategory
     * @param pricePerKg the new price per kilogram of the WasteCategory
     * @return the updated WasteCategory
     */
    public WasteCategory updateWasteCategory(Long id, String name, double pricePerKg) {
        WasteCategory wasteCategory = WasteCategoryFactory.createWasteCategory(id, name, pricePerKg);
        return repository.save(wasteCategory);
    }

    /**
     * Delete a waste category by ID.
     *
     * @param id the ID of the WasteCategory to delete
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}