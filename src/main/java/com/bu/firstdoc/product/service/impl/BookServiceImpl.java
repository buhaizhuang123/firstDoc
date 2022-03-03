package com.bu.firstdoc.product.service.impl;

import com.bu.firstdoc.firstDoc.mapper.BookMapper;
import com.bu.firstdoc.product.model.BookDto;
import com.bu.firstdoc.product.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/2/23 10:05 上午
 * @mark BookServiceImpl
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer insertBook() throws IOException {
        BookDto bookDto = new BookDto();

        InputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/Applications/tools/wd/后端书籍/");


            byte[] bytes = new byte[10240];
            int len;
            while ((len = fileInputStream.read(bytes)) > 0) {
                fileInputStream.read(bytes, 0, len);
            }
            bookDto.setBCreDate(new Date());
            bookDto.setBName("大话设计模式(带目录完整版).pdf");
            bookDto.setBContent(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Integer integer = bookMapper.insertBook(bookDto);
        return integer;
    }

    @Override
    public List<BookDto> listBookInfo() {
        return bookMapper.listBooks();
    }

    @Override
    public BookDto findOneBook(String id) {
        return bookMapper.findOneBook(id);
    }

    @Override
    public void listMap(String path) {
        File file = new File(path);
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                String path1 = listFile.getPath();
                listMap(path1);
            } else {
                String bookName = listFile.getName();
                BookDto bookDto = new BookDto();
                bookDto.setFilePath(path + "/" + bookName);
                bookDto.setBCreDate(new Date());
                bookDto.setBName(bookName);
                bookMapper.insertBook(bookDto);
            }

        }
    }

    @Override
    public BookDto findOneBookByName(String name) {
        BookDto bookDto =  bookMapper.findBooKByName(name);
        return bookDto;
    }

}
