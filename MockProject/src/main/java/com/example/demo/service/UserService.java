package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.response.BaseResponseDTO;
import com.example.demo.dto.response.CreateOrUpdateResponseDTO;
import com.example.demo.dto.response.ListResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ListResponseDTO<UserDTO> getListUser();

    CreateOrUpdateResponseDTO createUser(UserDTO userDTO);

    CreateOrUpdateResponseDTO editUser(UserDTO userDTO);

    BaseResponseDTO deleteUserByUserName(String userName);

    UserDTO getByUsername(String username);
}
