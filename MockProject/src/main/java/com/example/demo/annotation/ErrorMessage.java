package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Retention dùng để chú thích mức độ tồn tại của một annotation nào đó
@Retention(RetentionPolicy.RUNTIME) // Tồn tại trong lúc chạy chương trình
//Target chú thích phạm vi sử dụng của 1 annotation
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})  //Được sử dụng trên biến và annotation
public @interface ErrorMessage {
    String value() default "";
}
