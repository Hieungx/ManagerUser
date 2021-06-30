package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
    private int id;
    @Field("title")
    private String title;
    @Field("quantity")
    private int quantity;
    @Field("price")
    private Double price;
    @Field("totalMoney")
    private Double totalMoney;

    public Book(String title,Integer quantity, double price){
        super();
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

}
