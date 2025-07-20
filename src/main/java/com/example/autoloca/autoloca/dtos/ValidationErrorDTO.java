package com.example.autoloca.autoloca.dtos;

import java.util.List;

public record ValidationErrorDTO(
        List<String> errors,
        int status
) {
}
