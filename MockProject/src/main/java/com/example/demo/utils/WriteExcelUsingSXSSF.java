package com.example.demo.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Book;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.*;

public class WriteExcelUsingSXSSF {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_TITLE = 1;
    public static final int COLUMN_INDEX_PRICE = 2;
    public static final int COLUMN_INDEX_QUANTITY = 3;
    public static final int COLUMN_INDEX_TOTAL = 4;
    private static CellStyle cellStyleFormatNumber = null;

    public static void writeExcel(List<Book> books, String excelFilePath) {
        //Create WorkBook
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        // Create sheet
        SXSSFSheet sheet = workbook.createSheet("Books");
        
        // register the columns you wish to track and compute the column width
        sheet.trackAllColumnsForAutoSizing();
    }

}
