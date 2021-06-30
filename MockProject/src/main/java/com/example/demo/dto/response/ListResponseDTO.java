package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponseDTO<T> extends BaseResponseDTO {
    private List<T> data;

    public ListResponseDTO() {
        super();
    }

    public ListResponseDTO(List<T> data) {
        super();
        this.data = data;
    }
}
