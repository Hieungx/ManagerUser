package com.example.demo.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public abstract class CommonMapper {
    @Named("CONVERT_OBJECT_ID_TO_STRING")
    String convertObjectIdToString(ObjectId id) {
        return id != null ? id.toString() : null;
    }

    @Named("CONVERT_STRING_TO_OBJECT_ID")
    ObjectId convertStringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }
}
