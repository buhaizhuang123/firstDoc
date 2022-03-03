package com.bu.firstdoc;

import com.bu.firstdoc.common.config.PageCommon;
import org.apache.ibatis.session.Configuration;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@SpringBootApplication
@MapperScan({"com/bu/firstdoc/klin/mapper", "com/bu/firstdoc/firstDoc/mapper"})
@EnableWebSocket
@EnableBatchProcessing
public class FirstDocApplication {

    public static void main(String[] args) {

        SpringApplication.run(FirstDocApplication.class, args);
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.addInterceptor(new PageCommon());
            }
        };
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(false)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
