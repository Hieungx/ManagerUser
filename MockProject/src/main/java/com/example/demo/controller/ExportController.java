package com.example.demo.controller;

import com.example.demo.constant.ErrorCode;
import com.example.demo.dto.ExportUserDTO;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.MockProjectException;
import com.example.demo.service.ExportService;
import com.example.demo.service.UserService;

import com.example.demo.utils.ExcelHelper;
import com.example.demo.utils.UserExcelExporter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/export")

public class ExportController {

    @Autowired
    ExcelHelper excelHelper;

    @Autowired
    ExportService exportService;

    @Autowired
    UserService userService;

    @SneakyThrows
    @GetMapping("/user")
    public ResponseEntity<Resource> exportListUser() {
        List<ExportUserDTO> list = exportService.getAllUser();
        System.out.println(list);
        if (!CollectionUtils.isEmpty(list)) {
            String filename = "List_User" + ".xlsx";
            ByteArrayInputStream in = excelHelper.exportListUser(list);
            InputStreamResource file = new InputStreamResource(in);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"))
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel; charset=UTF-8"))
                    .body(file);
        } else {
            throw new MockProjectException(ErrorCode.NO_DATA_FOUND_FIRST_STEP);
        }
    }

    @SneakyThrows
    @GetMapping("/my-export-users")
    public void exportToExcel(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headKey = "Content-Disposition";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormat.format(new Date());
        String fileName = "users_" + currentDateTime + ".xlsx";

        String headValue = "attachement; filename=" + fileName;

        response.setHeader(headKey, headValue);

        List<UserDTO> listUser = userService.getListUser();

        UserExcelExporter excelExporter = new UserExcelExporter(listUser);
        excelExporter.export(response);
    }

}
