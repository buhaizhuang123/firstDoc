package com.bu.firstdoc.firstDoc.mapper;

import com.bu.firstdoc.product.model.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/2/23 9:59 上午
 * @mark BookMapper
 */
@Mapper
public interface BookMapper {


    Integer insertBook(BookDto bookDto);

    List<BookDto> listBooks();

    BookDto findOneBook(String id);

    BookDto findBooKByName(String name);
}
