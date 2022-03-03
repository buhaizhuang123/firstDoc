package com.bu.firstdoc.product.controller;

import com.bu.firstdoc.product.service.ProService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author haizhuangbu
 * @Date
 * @Function
 * @Mark
 */
@RestController
@RequestMapping("/pro")
public class ProController {

    @Autowired
    private ProService proService;

    @RequestMapping("/com")
    public void comeBackPage(HttpServletResponse response) {
        try {
            String fileName = "/Applications/tools/wd/后端书籍/JVM/深入理解Java虚拟机JVM高级特性与最佳实践.pdf";
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            ServletOutputStream outputStream = response.getOutputStream();
            // 文件上传
//            response.setHeader("content-disposition", "attachment;filename="+"1.txt");
            // 文件预览
            response.setContentType("application/pdf");
//            IOUtils.copy(fileInputStream, writer);
            byte[] bytes = new byte[10240];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        FileOutputStream outputStream = new FileOutputStream("person.xls");

        Workbook workbook = new HSSFWorkbook();
        Sheet person = workbook.createSheet("person");
        Row row0 = person.createRow(0);
        Row row = person.createRow(1);
        Cell name = row.createCell(0);
        name.setCellValue("name");
        Cell age = row.createCell(1);
        age.setCellValue("age");
        Cell address = row.createCell(2);
        address.setCellValue("address");
        Cell city = row.createCell(3);
        city.setCellValue("city");
        Cell cell = row0.createCell(row.getFirstCellNum(), row.getLastCellNum());
        row0.setHeightInPoints(20);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue("你管我");
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
