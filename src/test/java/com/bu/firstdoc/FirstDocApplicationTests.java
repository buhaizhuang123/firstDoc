package com.bu.firstdoc;

import com.alibaba.fastjson.JSON;
import com.bu.firstdoc.sys.dao.UserInfoDao;
import com.bu.firstdoc.sys.model.CheckObj;
import com.bu.firstdoc.sys.model.UserInfo;
import com.bu.firstdoc.sys.service.UserInfoService;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@SpringBootTest
class FirstDocApplicationTests {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void contextLoads() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("布");
        List<UserInfo> all = userInfoDao.findAll(userInfo, new RowBounds(0, 10));
        all.forEach(System.out::println);
    }

    @Test
    void insertUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString().replace("-", ""))
                .setId(UUID.randomUUID().toString().replace("-", ""))
                .setUserName("李佳君")
                .setUserBirDate(new Date())
                .setUserInfoData(JSON.toJSONString(userInfo));
        userInfoDao.saveUser(userInfo);
    }

    @Test
    public void tsRedis() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("布");
        List<UserInfo> all = userInfoService.findAll(userInfo);
        all.forEach(System.out::println);
    }

    /**
     * 测试异步
     */
    @Test
    public void tsYb() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = executorService.submit(() -> {
            System.out.println("执行方法");
            return "结果";
        });

        System.out.println("其他步骤");

        try {
            String o = future.get(1, TimeUnit.SECONDS);
            System.out.println(o);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }


    @Autowired
    private Validator validator;

    @Test
    public void tsV() {
        CheckObj checkObj = new CheckObj();
        Set<ConstraintViolation<CheckObj>> validate = validator.validate(checkObj);
        Iterator<ConstraintViolation<CheckObj>> iterator = validate.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next().getMessage() = " + iterator.next().getMessage());
        }
    }


    /**
     * 测试lombda toMap
     */
    public static void main(String[] args) {
        List<CheckObj> checkObjs = new ArrayList<>();
        checkObjs.add(new CheckObj().setId(UUID.randomUUID().toString()));
        Map<String, CheckObj> collect = checkObjs.stream().collect(Collectors.toMap(CheckObj::getId, CheckObj -> CheckObj));

        collect.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

}
