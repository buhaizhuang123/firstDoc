package com.bu.firstdoc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author haizhuangbu
 * @date 10:36 下午 2022/2/17
 * @mark FirstDatasource
 */

@Configuration
@MapperScan(basePackages = {"com/bu/firstdoc/firstDoc/mapper"},sqlSessionFactoryRef = "firstDocSqlSessionFactory")
public class FirstDatasource {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.firstdoc")
    DataSource firstDoc(){
        return DataSourceBuilder.create().build();
    }



    @Bean("firstDocSqlSessionFactory")
    SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDoc") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapping/firstDoc/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("firstDocSqlSessionTemplate")
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstDocSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
