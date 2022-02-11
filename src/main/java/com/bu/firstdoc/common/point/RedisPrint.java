package com.bu.firstdoc.common.point;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bu.firstdoc.sys.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author haizhuangbu
 * @date 2:58 下午 2022/1/13
 * @mark RedisPrint
 */
@Component
@Aspect
@Slf4j
public class RedisPrint {

    private final String PRE_FIX = "redisSys";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(com.bu.firstdoc.common.point.anno.RedisSys)")
    public void exe() {
    }

    @Around(value = "exe()")
    public Object around(ProceedingJoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        String key = args[0].hashCode() + PRE_FIX;

        log.info("进入redis缓存 key:{}", key);

        redisTemplate.setValueSerializer(new StringRedisSerializer());

        ValueOperations<String, String> strRedis = redisTemplate.opsForValue();

        String value = strRedis.get(key);
        log.info("redis key {}", key);
        if (StringUtils.hasText(value)) {
            log.info("redis 缓存返回");
            System.out.println(value);
            return JSONArray.parseArray(value, UserInfo.class);
        }

        try {
            Object proceed = joinPoint.proceed(args);
            log.info("------------1 ");
            value = JSON.toJSONString(proceed);
            log.info("------------2 {}", value);
            strRedis.set(key, value, 20, TimeUnit.SECONDS);
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info(" ====  {}", value);
        return value;
    }

}
