package com.example.demo.utils;

import com.example.demo.constant.ErrorCode;
import com.example.demo.exception.MockProjectException;
import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

public class ValidationUtils {
    public static ObjectId validateObjectId(String id){
        if (StringUtils.isEmpty(id)){
            throw new MockProjectException(ErrorCode.OBJECT_ID_INVALID);
        }
        try {
            ObjectId objectId = new ObjectId(id);
            return objectId;
        } catch (Exception e){
            throw new MockProjectException(ErrorCode.OBJECT_ID_INVALID);
        }
    }
}


