package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MockProjectException extends RuntimeException {
    private String code;
    private String message;

    public MockProjectException(String code) {
        this.code = code;
        this.message = "";
    }
}
