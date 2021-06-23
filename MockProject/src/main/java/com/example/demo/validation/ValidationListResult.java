package com.example.demo.validation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationListResult {

    private boolean isSuccessful ;
    private String code;
    private List<String> message;

}
