package org.chinocarbon.judgesystem.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hedon Wang
 * @create 2020-07-18 13:49
 */
@Configuration
public class MybatisPlusConfig
{
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer()
    {
        return configuration -> configuration.addInterceptor(new com.github.pagehelper.PageInterceptor());
    }
}
