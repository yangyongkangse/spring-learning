package com.spring.learning.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * Author: yangyk
 * Date: 2020/9/24 14:47
 * Description: 二维码数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.spring.learning.dao.qrcode", sqlSessionFactoryRef = "qrcodeSqlSessionFactory")
//通过将指定的mapper指定给特定sqlSessionFactory,解决事务下datasource不能变更问题
public class QrcodeDataSourceConfig {

	@Bean(name = "qrcodeDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource qrcodeDataSource() {
		return DataSourceBuilder.create().build();
	}

	// 事务控制器
	@Bean(name = "qrcodeTransactionManager")
	@Primary
	public DataSourceTransactionManager qrcodeTransactionManager() {
		return new DataSourceTransactionManager(qrcodeDataSource());
	}

	@Bean(name = "qrcodeSqlSessionFactory")
	@Primary
	public SqlSessionFactory qrcodeSqlSessionFactory(@Qualifier("qrcodeDataSource") DataSource qrcodeDataSource)
			throws Exception {
		final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(qrcodeDataSource);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:/mapper/qrcode/*.xml"));
		//设置sql控制台打印
		com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
		configuration.setLogImpl(Log4j2Impl.class);
		sessionFactory.setConfiguration(configuration);
		return sessionFactory.getObject();
	}
}