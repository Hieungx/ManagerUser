package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Field("language")
    private String language;

    @Field("level")
    private String level;

    @Field("exp")
    private int exp;
}
