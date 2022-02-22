package com.bu.firstdoc.product.controller;

import com.bu.firstdoc.product.service.ProService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
            FileInputStream fileInputStream = new FileInputStream(new File("/Applications/tools/wd/后端书籍/设计模式/24种设计模式介绍与6大设计原则.pdf"));
            ServletOutputStream outputStream = response.getOutputStream();
//            response.setHeader("content-disposition", "attachment;filename="+"1.txt");
            response.setContentType("application/pdf");
//            IOUtils.copy(fileInputStream, writer);
            byte[] bytes = new byte[10240];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
//            writer.flush();
//            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
