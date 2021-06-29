package com.example.demo.service.Impl;


import com.example.demo.dto.ExportUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.response.ListResponseDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.ExportService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @Resource
    UserService userService;

    @Override
    public List<ExportUserDTO> getAllUser() {
        List<UserDTO> list = (List<UserDTO>) userService.getListUser();
        List<ExportUserDTO> listExport = new ArrayList<>();
        for (UserDTO userDTO : list){
            ExportUserDTO tmp = new ExportUserDTO();
            tmp.setUserName(userDTO.getUserName());
            tmp.setName(userDTO.getName());
            tmp.setAge(userDTO.getAge());
            tmp.setEmail(userDTO.getEmail());
            listExport.add(tmp);
        }
        return listExport;
    }
}
