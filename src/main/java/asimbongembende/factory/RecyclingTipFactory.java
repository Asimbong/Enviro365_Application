package asimbongembende.factory;

import com.enviro.assessment.grad001.asimbongembende.domain.RecyclingTip;
import com.enviro.assessment.grad001.asimbongembende.util.Helper;

public class RecyclingTipFactory {
    public static RecyclingTip createRecyclingTip(String tip) {
        Helper.validateTip(tip);
        return new RecyclingTip.Builder()
                .setTip(tip)
                .build();
    }
    public static RecyclingTip createRecyclingTip(Long id, String tip) {
        Helper.validateTip(tip);
        return new RecyclingTip.Builder()
                .setId(id)
                .setTip(tip)
                .build();
    }
}