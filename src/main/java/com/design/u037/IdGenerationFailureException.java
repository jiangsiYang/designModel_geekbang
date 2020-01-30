package com.design.u037;

public class IdGenerationFailureException extends RuntimeException {
    String tips;

    public IdGenerationFailureException(String tips) {
        this.tips = tips;
    }
}
