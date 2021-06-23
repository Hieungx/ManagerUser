package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CommonMapper.class})
public abstract class UserMapper implements BaseMapper<UserDTO, User> {
    @Override
    @Mapping(source = "id", target = "id", qualifiedByName = "CONVERT_STRING_TO_OBJECT_ID")
    @InheritConfiguration
    @Named("userToEntity")
    public abstract User toEntity(UserDTO dto);

    @Mapping(source = "id", target = "id", qualifiedByName = "CONVERT_OBJECT_ID_TO_STRING")
    @InheritConfiguration
    @Named("userToDTO")
    public abstract UserDTO toDTO(User entity);

    @Override
    @IterableMapping(qualifiedByName = "userToDTO")
    public abstract List<UserDTO> toListDTO(List<User> entityList);

    @Override
    @IterableMapping(qualifiedByName = "userToEntity")
    public abstract List<User> toListEntity(List<UserDTO> dtoList);
}
