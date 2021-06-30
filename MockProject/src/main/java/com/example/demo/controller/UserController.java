package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.dto.response.BaseResponseDTO;
import com.example.demo.dto.response.CreateOrUpdateResponseDTO;
import com.example.demo.dto.response.ListResponseDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserRepository userRepository;

    @GetMapping("/get-by-username")
    public ResponseEntity<UserDTO> getByUsername(@RequestParam String username) {
        UserDTO userDTO = userService.getByUsername(username);
        return ResponseEntity.ok(userDTO);
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/get-all-user")
    public ResponseEntity<List<UserDTO>> getAll() {
        //SecuConTextHold đang giữ tất cả config người dùng vừa đâng nhập vào
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //chứa các thông tin đăng nhập
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            System.out.println(authentication.getName());
//        }
        List<UserDTO> list = userService.getListUser();
        return  ResponseEntity.ok(list);
    }

    @PostMapping("/create-user")
    public ResponseEntity<CreateOrUpdateResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        CreateOrUpdateResponseDTO response = userService.createUser(userDTO);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PutMapping("/edit-user")
    public ResponseEntity<CreateOrUpdateResponseDTO> editUser(@RequestBody UserDTO userDTO) {
        CreateOrUpdateResponseDTO response = userService.editUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<BaseResponseDTO> deleteUserByUserName(@RequestParam String userName) {
        BaseResponseDTO response = userService.deleteUserByUserName(userName);
        return ResponseEntity.ok(response);
    }

    //Xong 2 cái API tuy nhiên password ko show ra
}
