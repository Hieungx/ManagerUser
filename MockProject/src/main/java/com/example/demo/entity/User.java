package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private ObjectId id;
    @Field("username")
    private String userName;
    @Field("name")
    private String name;
    @Field("skill")
    private List<Skill> skills;
    @Field("email")
    private String email;
    @Field("password")
    private String password;
    @Field("role")
    private String role;

}
