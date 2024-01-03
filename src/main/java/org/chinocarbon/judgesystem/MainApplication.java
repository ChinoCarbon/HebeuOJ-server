package org.chinocarbon.judgesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("org.chinocarbon.judgesystem.dao")
@SpringBootApplication()
@ServletComponentScan(basePackages = "org.chinocarbon")
public class MainApplication
{

    public static void main(String[] args)
    {
        final ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        System.out.println(run.getBean("sqlSessionFactory"));
    }

}
