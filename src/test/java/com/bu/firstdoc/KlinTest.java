package com.bu.firstdoc;

import com.bu.firstdoc.firstDoc.mapper.UserInfoMapper;
import com.bu.firstdoc.klin.mapper.DictMapper;
import com.bu.firstdoc.sys.model.UserInfo;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.spi.ValidationProvider;
import java.util.List;
import java.util.Set;

/**
 * @author haizhuangbu
 * @date 9:45 下午 2022/2/17
 * @mark KlinTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KlinTest {


    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private Validator validator;

    @Test
    public void testMapper(){

        UserInfo userInfo = new UserInfo();
        Set<ConstraintViolation<UserInfo>> validate = this.validator.validate(userInfo);
        validate.stream().forEach(val-> System.out.println(val.getMessage()));



        List all1 = userInfoMapper.findAll();
        List all = dictMapper.findAll();
        System.out.println(all);
        System.out.println(all1);
    }

}
