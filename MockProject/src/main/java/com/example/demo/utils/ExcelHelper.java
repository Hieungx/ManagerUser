package com.example.demo.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.constant.Constant;
import com.example.demo.dto.ExportUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;

@Component
public class ExcelHelper {
    private String PATH_TO_TEMPLATE_EXCEL = "C:/Users/nthieu10/Desktop/Excel";

    public ByteArrayInputStream exportListUser(List<ExportUserDTO> input) throws Exception {
        File file = ResourceUtils.getFile(PATH_TO_TEMPLATE_EXCEL + "User.xlsx");
        String fileName = PATH_TO_TEMPLATE_EXCEL + "User.xlsx";
        File outputFile = new File(fileName);
        copyFile(file, outputFile);
        FileInputStream inputStream = new FileInputStream(outputFile);
        XSSFWorkbook oldWorKbook = new XSSFWorkbook(inputStream);
        XSSFWorkbook newWorkbook = new XSSFWorkbook();
        XSSFSheet oldSheet = oldWorKbook.getSheet("Sheet1");
        XSSFSheet newSheet = newWorkbook.createSheet("Sheet1");
        newSheet.createFreezePane(6, 2, 6, 2);

        //Style
        XSSFFont font = newWorkbook.createFont();
        font.setFontName("Arial");
        font.setBold(false);
        font.setFontHeightInPoints((short) 10);

        XSSFFont fontHeader = newWorkbook.createFont();
        fontHeader.setFontName("Arial");
        fontHeader.setBold(true);
        fontHeader.setFontHeightInPoints((short) 14);

        XSSFCellStyle cellRowStyle = newWorkbook.createCellStyle();
        cellRowStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellRowStyle.setBorderTop(BorderStyle.MEDIUM);
        cellRowStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellRowStyle.setBorderRight(BorderStyle.MEDIUM);
        cellRowStyle.setAlignment(HorizontalAlignment.CENTER);
        cellRowStyle.setWrapText(true);
        cellRowStyle.setFont(font);

        String cellRgbS = "d04437";
        byte[] cellRgbSB = Hex.decodeHex(cellRgbS); // get byte array from hex string
        XSSFColor cellColor = new XSSFColor(cellRgbSB, null);

        XSSFCellStyle highLightStyle = newWorkbook.createCellStyle();
        highLightStyle.setFillForegroundColor(cellColor);
        highLightStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        highLightStyle.setBorderBottom(BorderStyle.MEDIUM);
        highLightStyle.setBorderTop(BorderStyle.MEDIUM);
        highLightStyle.setAlignment(HorizontalAlignment.CENTER);
        highLightStyle.setBorderLeft(BorderStyle.MEDIUM);
        highLightStyle.setBorderRight(BorderStyle.MEDIUM);

        XSSFRow titleRow = newSheet.getRow(0);
        if (titleRow == null) {
            titleRow = newSheet.createRow(0);
            titleRow.setHeightInPoints(60);
        }
        XSSFRow headerRow = newSheet.getRow(1);
        if (headerRow == null) {
            headerRow = newSheet.createRow(1);
            headerRow.setHeightInPoints(60);
        }
        int startDateCell = 0;
        for (int k = 0; k < oldSheet.getRow(1).getLastCellNum(); k++) {
            XSSFCellStyle oldCellStyle = oldSheet.getRow(1).getCell(k).getCellStyle();
            XSSFCellStyle newCellStyle = newWorkbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCellStyle);
            String header = oldSheet.getRow(1).getCell(k).getStringCellValue();
            int width = oldSheet.getColumnWidth(k);
            newSheet.setColumnWidth(startDateCell, width);
            setCellValue(1, startDateCell, newSheet, header, newCellStyle);
            startDateCell++;
        }
        XSSFCellStyle titleCellStyle = newWorkbook.createCellStyle();
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFont(fontHeader);
        titleCellStyle.setWrapText(true);

        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, startDateCell);
        newSheet.addMergedRegion(cellAddresses);

        if (!CollectionUtils.isEmpty(input)) {
            for (int i = 0; i < input.size(); i++) {
                ExportUserDTO dto = input.get(i);
                int j = 0;
                //No.
                setCellValue(i + 2, j++, newSheet, i + 1, cellRowStyle);
                //UserName
                if (!ObjectUtils.isEmpty(dto.getUserName())) {
                    setCellValue(i + 2, j++, newSheet,dto.getUserName(), cellRowStyle);
                } else {
                    setCellValue(i + 2, j++, newSheet, Constant.ExportConstant.BLANK, cellRowStyle);
                }
                //FullName
                if (!ObjectUtils.isEmpty(dto.getName())) {
                    setCellValue(i + 2, j++, newSheet,dto.getName(), cellRowStyle);
                } else {
                    setCellValue(i + 2, j++, newSheet, Constant.ExportConstant.BLANK, cellRowStyle);
                }
                //Email
                if (!ObjectUtils.isEmpty(dto.getEmail())) {
                    setCellValue(i + 2, j++, newSheet,dto.getEmail(), cellRowStyle);
                } else {
                    setCellValue(i + 2, j++, newSheet, Constant.ExportConstant.BLANK, cellRowStyle);
                }
                //Age
                if (!ObjectUtils.isEmpty(dto.getAge())) {
                    setCellValue(i + 2, j++, newSheet,dto.getAge(), cellRowStyle);
                } else {
                    setCellValue(i + 2, j++, newSheet, Constant.ExportConstant.BLANK, cellRowStyle);
                }
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        newWorkbook.write(out);
        inputStream.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void setCellValue(int i, int j, XSSFSheet sheet, Object value, XSSFCellStyle cellRowStyle) {
        // khai bao hang
        XSSFRow currentRow = sheet.getRow(i);
        if (currentRow == null) {
            currentRow = sheet.createRow(i);
            currentRow.setHeightInPoints(25);
        }
        // khai bao o
        XSSFCell fDateCell = currentRow.getCell(j);
        if (fDateCell == null) {
            fDateCell = currentRow.createCell(j);
        }
        if (value != null) {
            switch (value.getClass().getName()) {
                case "java.util.Date":
                    fDateCell.setCellValue(DateFormat.format_ddMMyyyy((Date) value));
                    break;
                case "java.lang.Integer":
                    fDateCell.setCellValue((int) value);
                    break;
                case "java.lang.Float":
                    fDateCell.setCellValue((float) value);
                    break;
                case "java.lang.Double":
                    fDateCell.setCellValue((double) value);
                    break;
                default:
                    fDateCell.setCellValue((String) value);
                    break;
            }
        } else {
            fDateCell.setCellValue(Constant.ExportConstant.BLANK);
        }
        fDateCell.setCellStyle(cellRowStyle);
    }

    public final static int BUF_SIZE = 1024;

    public static void copyFile(File in, File out) throws Exception {
        try (FileInputStream fis = new FileInputStream(in); FileOutputStream fos = new FileOutputStream(out)) {
            byte[] buf = new byte[BUF_SIZE];
            int i;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }
    }
}