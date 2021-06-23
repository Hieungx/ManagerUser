package com.example.demo.repository;

import com.example.demo.dto.response.BaseResponseDTO;
import com.example.demo.entity.User;

public interface UserRepositoryCustom {
    User findUserByUserName(String name);

    User findUserById(String Id);

    BaseResponseDTO deleleUserByUserName(String userName);
}
