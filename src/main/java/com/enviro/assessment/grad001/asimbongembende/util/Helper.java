package com.enviro.assessment.grad001.asimbongembende.util;

public class Helper {
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
    }

    public static void validatePricePerKg(double pricePerKg) {
        if (pricePerKg <= 0) {
            throw new IllegalArgumentException("Price per kg must be greater than zero");
        }
    }

    public static void validateGuideline(String guideline) {
        if (guideline == null || guideline.trim().isEmpty()) {
            throw new IllegalArgumentException("Guideline is mandatory");
        }
    }

    public static void validateDisposalMethod(String disposalMethod) {
        if (disposalMethod == null || disposalMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("Disposal method is mandatory");
        }
    }
    public static void validateTip(String tip) {
        if (tip == null || tip.trim().isEmpty()) {
            throw new IllegalArgumentException("Tip is mandatory");
        }
    }
}