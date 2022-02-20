package com.bu.firstdoc.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
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
 * @date 10:41 下午 2022/2/17
 * @mark klinDatasource
 */
@Configuration
@MapperScan(basePackages = "com/bu/firstdoc/klin/mapper/",sqlSessionFactoryRef = "klinSqlSessionFactory")
public class klinDatasource {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.klin")
    @Primary
    DataSource klin(){
        return DataSourceBuilder.create().build();
    }

    @Bean("klinSqlSessionFactory")
    @Primary
    SqlSessionFactory klinSqlSessionFactory(@Qualifier("klin") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapping/klin/DictMapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean("klinSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate klinSqlSessionTemplate(@Qualifier("klinSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
