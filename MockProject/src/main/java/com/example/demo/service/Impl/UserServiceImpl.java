package com.example.demo.service.Impl;

import com.example.demo.constant.ErrorCode;
import com.example.demo.dto.PageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.response.BaseResponseDTO;
import com.example.demo.dto.response.CreateOrUpdateResponseDTO;
import com.example.demo.dto.response.ListResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.MockProjectException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.example.demo.validation.ValidationResult;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    UserRepository userRepository;

    @Resource
    UserMapper userMapper;

    //    @Override
//    public List<UserDTO> getListUser() {
//        List<User> users = userRepository.findAll();
//        return userMapper.toListDTO(users);
//    }
    @Override
    public ListResponseDTO<UserDTO> getListUser(int page, int size) {
        List<User> users = userRepository.findAll();
        long totalElement = users.size();
        PageDTO pageDTO = PageUtils.caculatePage(size, page, totalElement);
        List<UserDTO> userDTOS = userMapper.toListDTO(users);
        return new ListResponseDTO<>(userDTOS, pageDTO);
    }

    @Override
    public UserDTO getByUsername(String username) {
        ValidationResult validationResult = validateUserExisted(username);
        if (!validationResult.isSuccessful()) {
            throw new MockProjectException(validationResult.getCode(), validationResult.getMessage());
        }
        User user = userRepository.findUserByUserName(username);
        return userMapper.toDTO(user);
    }

    @Override
    public CreateOrUpdateResponseDTO createUser(UserDTO userDTO) {
        ValidationResult validationResult = validateCreateUser(userDTO);
        if (!validationResult.isSuccessful()) {
            return new CreateOrUpdateResponseDTO(ErrorCode.CREATE_FAILED, validationResult.getMessage());
        }
        userRepository.save(userMapper.toEntity(userDTO));

        return new CreateOrUpdateResponseDTO(ErrorCode.SUCCESS, validationResult.getMessage());
    }

    @Override
    public CreateOrUpdateResponseDTO editUser(UserDTO userDTO) {
        ValidationResult validationResult = validateUserExisted(userDTO.getUserName());
        validationResult.setSuccessful(true);
        validationResult.setMessage("Success");
        if (!validationResult.isSuccessful()) {
            return new CreateOrUpdateResponseDTO(validationResult.getCode(), validationResult.getMessage());
        }
        userRepository.save(userMapper.toEntity(userDTO));
        return new CreateOrUpdateResponseDTO(ErrorCode.SUCCESS, validationResult.getMessage());
    }

    @Override
    public BaseResponseDTO deleteUserByUserName(String userName) {
        ValidationResult validationResult = validateUserExisted(userName);
        if (!validationResult.isSuccessful()) {
            return new BaseResponseDTO(validationResult.getCode(), validationResult.getMessage());
        }
        BaseResponseDTO baseResponseDTO = userRepository.deleleUserByUserName(userName);
        return baseResponseDTO;
    }

    private ValidationResult validateCreateUser(UserDTO userDTO) {
        ValidationResult validationResult = new ValidationResult();
        if (userRepository.findUserByUserName(userDTO.getUserName()) != null) {
            validationResult.setSuccessful(false);
            validationResult.setMessage("member exsited");
        } else {
            validationResult.setSuccessful(true);
            validationResult.setMessage("Success!!");
        }
        return validationResult;
    }

    private ValidationResult validateUserExisted(String userName) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setSuccessful(true);
        if (ObjectUtils.isEmpty(userRepository.findUserByUserName(userName))) {
            validationResult.setSuccessful(false);
            validationResult.setCode(ErrorCode.USER_NOT_FOUND);
            validationResult.setMessage("User not found");
        }
        return validationResult;
    }


    // UserDetail : đóng gói username password
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + "not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
    }
}
