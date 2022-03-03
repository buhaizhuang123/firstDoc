package com.bu.firstdoc.product.service;

import com.bu.firstdoc.product.model.BookDto;

import java.io.IOException;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/2/23 10:05 上午
 * @mark BookService
 */
public interface BookService {

    Integer insertBook() throws IOException;

    List<BookDto> listBookInfo();

    BookDto findOneBook(String id);

    void listMap(String path);

    BookDto findOneBookByName(String name);
}
