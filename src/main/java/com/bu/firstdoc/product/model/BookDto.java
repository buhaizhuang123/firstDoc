package com.bu.firstdoc.product.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2022/2/23 10:01 上午
 * @mark BookDto BOOK_INFO
 */
@Data
public class BookDto implements Serializable {

    private static final long serialVersionUID = -185008606966813263L;
    private BigDecimal id;

    private String bName;

    private byte[] bContent;

    private Date bCreDate;

    private String filePath;

}
