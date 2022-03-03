package com.bu.firstdoc.product.controller;

import com.bu.firstdoc.product.model.BookDto;
import com.bu.firstdoc.product.service.BookService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/2/23 4:19 下午
 * @mark BookController
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("list")
    public List<String> bookDtoList() {
        return bookService.listBookInfo().stream().map(BookDto::getBName)
                .collect(Collectors.toList());
    }

    @RequestMapping("getOne/{id}")
    public void ylBookInfo(@PathVariable(name = "id") String id, HttpServletResponse response) throws IOException {
        BookDto bookDto = bookService.findOneBook(id);
        response.setContentType("application/pdf");
        ServletOutputStream writer = null;
        writer = response.getOutputStream();
        byte[] bContent = bookDto.getBContent();
        writer.write(bContent);
//        return bookDto.getBContent();
    }


    @RequestMapping("lookBook/{name}")
    public void ylBookInfo1(@PathVariable(name = "name") String name, HttpServletResponse response) throws IOException {
        BookDto bookDto = bookService.findOneBookByName(name);
        String filePath = bookDto.getFilePath();

        response.setContentType("application/pdf");
        ServletOutputStream writer = null;
        writer = response.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        IOUtils.copy(fileInputStream, writer);
//        return bookDto.getBContent();
    }

}
