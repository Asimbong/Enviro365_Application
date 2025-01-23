package com.enviro.assessment.grad001.asimbongembende.service;

import com.enviro.assessment.grad001.asimbongembende.domain.DisposalGuideline;
import com.enviro.assessment.grad001.asimbongembende.domain.WasteCategory;
import com.enviro.assessment.grad001.asimbongembende.factory.DisposalGuidelineFactory;
import com.enviro.assessment.grad001.asimbongembende.repository.DisposalGuidelineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing DisposalGuidelines.
 */
@Service
public class DisposalGuidelineService {
    private final DisposalGuidelineRepository repository;

    /**
     * Constructor for DisposalGuidelineService.
     * @param repository the DisposalGuidelineRepository to be used
     */
    public DisposalGuidelineService(DisposalGuidelineRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all disposal guidelines.
     * @return a list of all DisposalGuidelines
     */
    public List<DisposalGuideline> findAll() {
        return repository.findAll();
    }

    /**
     * Get a disposal guideline by ID.
     * @param id the ID of the DisposalGuideline to retrieve
     * @return an Optional containing the DisposalGuideline if found, or empty if not found
     */
    public Optional<DisposalGuideline> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Create a new disposal guideline.
     * @param guideline the guideline of the DisposalGuideline
     * @param disposalMethod the disposal method of the DisposalGuideline
     * @param wasteCategory the WasteCategory associated with the DisposalGuideline
     * @return the created DisposalGuideline
     */
    public DisposalGuideline createDisposalGuideline(String guideline, String disposalMethod, WasteCategory wasteCategory) {
        DisposalGuideline disposalGuideline = DisposalGuidelineFactory.createDisposalGuideline(guideline, disposalMethod, wasteCategory);
        return repository.save(disposalGuideline);
    }

    /**
     * Update an existing disposal guideline.
     *
     * @param id the ID of the DisposalGuideline to update
     * @param guideline the new guideline of the DisposalGuideline
     * @param disposalMethod the new disposal method of the DisposalGuideline
     * @param wasteCategory the new WasteCategory associated with the DisposalGuideline
     * @return the updated DisposalGuideline
     */
    public DisposalGuideline updateDisposalGuideline(Long id, String guideline, String disposalMethod, WasteCategory wasteCategory) {
        DisposalGuideline disposalGuideline = DisposalGuidelineFactory.createDisposalGuideline(id, guideline, disposalMethod, wasteCategory);
        return repository.save(disposalGuideline);
    }

    /**
     * Delete a disposal guideline by ID.
     *
     * @param id the ID of the DisposalGuideline to delete
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}