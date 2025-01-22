package asimbongembende.factory;

import com.enviro.assessment.grad001.asimbongembende.domain.WasteCategory;
import com.enviro.assessment.grad001.asimbongembende.util.Helper;

/**
 * Factory class for creating WasteCategory instances.
 */
public class WasteCategoryFactory {

    /**
     * Creates a new WasteCategory instance.
     *
     * @param name the name of the WasteCategory
     * @param pricePerKg the price per kilogram of the WasteCategory
     * @return the created WasteCategory
     */
    public static WasteCategory createWasteCategory(String name, double pricePerKg) {
        Helper.validateName(name);
        Helper.validatePricePerKg(pricePerKg);
        return new WasteCategory.Builder()
                .setName(name)
                .setPricePerKg(pricePerKg)
                .build();
    }

    /**
     * Creates a new WasteCategory instance with an ID.
     * @param id the ID of the WasteCategory
     * @param name the name of the WasteCategory
     * @param pricePerKg the price per kilogram of the WasteCategory
     * @return the created WasteCategory
     */
    public static WasteCategory createWasteCategory(Long id, String name, double pricePerKg) {
        Helper.validateName(name);
        Helper.validatePricePerKg(pricePerKg);
        return new WasteCategory.Builder()
                .setId(id)
                .setName(name)
                .setPricePerKg(pricePerKg)
                .build();
    }
}