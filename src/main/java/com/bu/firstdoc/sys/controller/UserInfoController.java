package com.bu.firstdoc.sys.controller;

import com.bu.firstdoc.common.ExceptionCommon.BException;
import com.bu.firstdoc.sys.model.UserInfo;
import com.bu.firstdoc.sys.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author haizhuangbu
 * @date 1:43 下午 2022/1/14
 * @mark UserInfoController
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    private volatile Enumeration<String> headerNames;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<UserInfo> getAllUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("布");
        return userInfoService.findAll(userInfo);
    }

    @GetMapping("/error")
    public Object error() throws Exception {
        throw new BException("出错了 %s", "123");
    }


    @RequestMapping(value = "http", method = RequestMethod.GET)
    public Map getHttp(HttpServletRequest request) {
        Map<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("getRequestURI", request.getRequestURI());
        stringStringHashMap.put("getContextPath", request.getContextPath());
        stringStringHashMap.put("getCookies", request.getCookies());
        stringStringHashMap.put("getLocale", request.getLocale());
        stringStringHashMap.put("getCharacterEncoding", request.getCharacterEncoding());
        stringStringHashMap.put("getMethod", request.getMethod());
        headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            log.info("key:{}", key);
            stringStringHashMap.put(key, request.getHeader(key));
        }
        stringStringHashMap.put("request.getPathInfo()", request.getPathInfo());
        stringStringHashMap.put("request.getLocalAddr()", request.getLocalAddr());
        stringStringHashMap.put("request.getLocalPort()", request.getLocalPort());
        stringStringHashMap.put("request.getRemoteAddr()", request.getRemoteAddr());
        stringStringHashMap.put("request.getRemotePort()", request.getRemotePort());
        stringStringHashMap.put("request.getServerPort()", request.getServerPort());
        stringStringHashMap.put("request.getRemoteHost()", request.getRemoteHost());
        return stringStringHashMap;
    }

}
