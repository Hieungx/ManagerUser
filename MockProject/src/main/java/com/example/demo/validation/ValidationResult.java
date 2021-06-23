package com.example.demo.validation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResult {

    private boolean isSuccessful ;
    private String code;
    private String message;

}
