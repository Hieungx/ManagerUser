package com.example.demo.dto;

import com.example.demo.constant.ParamKey;
import com.example.demo.entity.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExportUserDTO {
    @JsonProperty("username")
    private String userName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("age")
    private int age;
}
