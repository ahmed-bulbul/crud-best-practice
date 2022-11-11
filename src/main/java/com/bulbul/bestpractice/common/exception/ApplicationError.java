package com.bulbul.bestpractice.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationError {
    private String code;
    private String message;

    public ApplicationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
