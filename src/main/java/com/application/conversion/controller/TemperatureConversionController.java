package com.application.conversion.controller;

import com.application.conversion.response.ValidationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureConversionController {

    @GetMapping("/validate")
    public ValidationResponse validateTemperature(@RequestParam String value, @RequestParam String inputUnit,
                                                  @RequestParam String targetUnit, @RequestParam String studentResponse) {

        double numericValue = 0.0;
        try {
            numericValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return new ValidationResponse(0, "invalid");
        }

        double numericStudentResponse = 0.0;

        try {
            numericStudentResponse = Double.parseDouble(studentResponse);
        } catch (NumberFormatException e) {
            return new ValidationResponse(0, "incorrect");
        }

        double celsius = convertToCelsius(numericValue, inputUnit);
        double convertedValue = convertFromCelsius(celsius, targetUnit);
        convertedValue = Math.round(convertedValue * 10.0) / 10.0; // Round to tenths place
        numericStudentResponse = Math.round(numericStudentResponse * 10.0) / 10.0; // Round student response to tenths place

        boolean isCorrect = convertedValue == numericStudentResponse;
        return new ValidationResponse(convertedValue, isCorrect ? "correct" : "incorrect");
    }

    private double convertToCelsius(double value, String from) {
        return switch (from.toLowerCase()) {
            case "celsius" -> value;
            case "fahrenheit" -> (value - 32) * 5 / 9;
            case "kelvin" -> value - 273.15;
            case "rankine" -> (value - 491.67) * 5 / 9;
            default -> throw new IllegalArgumentException("Invalid temperature scale: " + from);
        };
    }

    private double convertFromCelsius(double celsius, String to) {
        return switch (to.toLowerCase()) {
            case "celsius" -> celsius;
            case "fahrenheit" -> (celsius * 9 / 5) + 32;
            case "kelvin" -> celsius + 273.15;
            case "rankine" -> (celsius + 273.15) * 9 / 5;
            default -> throw new IllegalArgumentException("Invalid temperature scale: " + to);
        };
    }
}