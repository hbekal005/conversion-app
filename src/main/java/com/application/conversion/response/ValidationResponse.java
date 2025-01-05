package com.application.conversion.response;

import lombok.Getter;

@Getter
public class ValidationResponse {
    private final double convertedValue;
    private final String response;

    public ValidationResponse(double convertedValue, String response) {
        this.convertedValue = convertedValue;
        this.response = response;
    }

}