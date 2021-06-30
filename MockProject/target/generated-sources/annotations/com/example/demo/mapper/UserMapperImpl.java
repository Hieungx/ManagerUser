package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Skill;
import com.example.demo.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-30T09:27:41+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( commonMapper.convertStringToObjectId( dto.getId() ) );
        user.setUserName( dto.getUserName() );
        user.setName( dto.getName() );
        List<Skill> list = dto.getSkills();
        if ( list != null ) {
            user.setSkills( new ArrayList<Skill>( list ) );
        }
        user.setAge( dto.getAge() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );

        return user;
    }

    @Override
    public UserDTO toDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( commonMapper.convertObjectIdToString( entity.getId() ) );
        userDTO.setUserName( entity.getUserName() );
        userDTO.setName( entity.getName() );
        List<Skill> list = entity.getSkills();
        if ( list != null ) {
            userDTO.setSkills( new ArrayList<Skill>( list ) );
        }
        userDTO.setEmail( entity.getEmail() );
        userDTO.setPassword( entity.getPassword() );
        userDTO.setAge( entity.getAge() );

        return userDTO;
    }

    @Override
    public List<UserDTO> toListDTO(List<User> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public List<User> toListEntity(List<UserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtoList.size() );
        for ( UserDTO userDTO : dtoList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }
}
