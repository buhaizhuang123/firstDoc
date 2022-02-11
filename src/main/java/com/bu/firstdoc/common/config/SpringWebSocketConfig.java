package com.bu.firstdoc.common.config;

import com.bu.firstdoc.common.TextHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author haizhuangbu
 * @date 3:18 下午 2022/2/10
 * @mark SpringWebSocketConfig
 */
@Configuration
public class SpringWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 添加通信地址
        registry.addHandler(buildTextHandler(),"ws").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler buildTextHandler(){
        return new TextHandler();
    }

}
