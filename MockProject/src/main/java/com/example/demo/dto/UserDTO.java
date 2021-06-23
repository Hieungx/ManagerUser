package com.example.demo.dto;


import com.example.demo.entity.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("skill")
    private List<Skill> skills;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

}
