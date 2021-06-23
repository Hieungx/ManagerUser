package com.example.demo.dto.response;

import com.example.demo.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateOrUpdateResponseDTO {
    private String code;
    private String message;

    public CreateOrUpdateResponseDTO() {
        this.code = ErrorCode.SUCCESS;
        this.message = "Success!!!";
    }

    public CreateOrUpdateResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
