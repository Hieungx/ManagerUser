package com.example.demo.repository.Impl;

import com.example.demo.constant.ErrorCode;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.response.BaseResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Resource
    MongoTemplate mongoTemplate;
    @Resource
    UserMapper userMapper;

    @Override
    public User findUserByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public User findUserById(String Id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(Id));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public BaseResponseDTO deleleUserByUserName(String userName) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        Query query = new Query();
        if(!ObjectUtils.isEmpty(userName)){
            query.addCriteria(Criteria.where("username").is(userName));
            mongoTemplate.remove(query,User.class);
        }else {
            baseResponseDTO.setCode(ErrorCode.DELETE_USER_FAILED);
            baseResponseDTO.setMessage("Can not delete user");
        }
        return baseResponseDTO;
    }
}
