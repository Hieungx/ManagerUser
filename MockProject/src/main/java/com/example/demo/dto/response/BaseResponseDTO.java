package com.example.demo.dto.response;

import com.example.demo.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDTO {
    private String code;
    private String message;

    public BaseResponseDTO() {
        this.code = ErrorCode.SUCCESS;
        this.message = "Success!!!";
    }

    public BaseResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
