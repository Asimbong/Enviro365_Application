package asimbongembende.factory;

import com.enviro.assessment.grad001.asimbongembende.domain.DisposalGuideline;
import com.enviro.assessment.grad001.asimbongembende.domain.WasteCategory;
import com.enviro.assessment.grad001.asimbongembende.util.Helper;

public class DisposalGuidelineFactory {
    public static DisposalGuideline createDisposalGuideline(String guideline, String disposalMethod, WasteCategory wasteCategory) {
        Helper.validateGuideline(guideline);
        Helper.validateDisposalMethod(disposalMethod);
        return new DisposalGuideline.Builder()
                .setGuideline(guideline)
                .setDisposalMethod(disposalMethod)
                .setWasteCategory(wasteCategory)
                .build();
    }
    public static DisposalGuideline createDisposalGuideline(Long id, String guideline, String disposalMethod, WasteCategory wasteCategory) {
        Helper.validateGuideline(guideline);
        Helper.validateDisposalMethod(disposalMethod);
        return new DisposalGuideline.Builder()
                .setId(id)
                .setGuideline(guideline)
                .setDisposalMethod(disposalMethod)
                .setWasteCategory(wasteCategory)
                .build();
    }
}