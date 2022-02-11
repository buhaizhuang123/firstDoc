package com.bu.firstdoc.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author haizhuangbu
 * @date 4:41 下午 2022/1/28
 * @mark PageController
 */
@Controller
public class PageController {

    @RequestMapping(value = "to/{page}",method = RequestMethod.GET)
    public String toPage(@PathVariable(value = "page") String page){
        return page;
    }

}
