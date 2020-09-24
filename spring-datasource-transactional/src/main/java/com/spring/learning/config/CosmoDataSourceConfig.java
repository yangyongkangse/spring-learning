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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:37
 * Description: 数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.spring.learning.dao.cosmo", sqlSessionFactoryRef = "cosmoSqlSessionFactory")
//通过将指定的mapper指定给特定sqlSessionFactory,解决事务下datasource不能变更问题
public class CosmoDataSourceConfig {

	@Bean(name = "cosmoDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.cosmo")
	public DataSource cosmoDataSource() {
		return DataSourceBuilder.create().build();
	}

	// 事务控制器
	@Bean(name = "cosmoTransactionManager")
	public DataSourceTransactionManager cosmoTransactionManager() {
		return new DataSourceTransactionManager(cosmoDataSource());
	}

	@Bean(name = "cosmoSqlSessionFactory")
	public SqlSessionFactory cosmoSqlSessionFactory(@Qualifier("cosmoDataSource") DataSource cosmoDataSource)
			throws Exception {
		final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(cosmoDataSource);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:/mapper/cosmo/*.xml"));
		//设置sql控制台打印
		com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
		configuration.setLogImpl(Log4j2Impl.class);
		sessionFactory.setConfiguration(configuration);
		return sessionFactory.getObject();
	}
}
