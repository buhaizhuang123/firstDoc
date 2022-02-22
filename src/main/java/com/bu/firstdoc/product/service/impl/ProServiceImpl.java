package com.bu.firstdoc.product.service.impl;

import com.bu.firstdoc.product.service.ProService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/2/22 2:52 下午
 * @mark ProServiceImpl
 */
@Service
public class ProServiceImpl implements ProService {

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void downLoadFile(HttpServletResponse response) {


    }
}
