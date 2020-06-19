package com.spring.api.tools;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author: yangyongkang
 * Date: 2020/1/7
 * Time: 11:16
 * Description:自动生成工具
 */
public class MybatisPlusGenerator {
	public static void main(String[] args) {
		// 1、创建代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 这里使用的默认引擎，就没有setTemplateEngine，如果使用其他的引擎，还需要添加相关的依赖
		// 2、全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("I:\\源码\\generator");
		gc.setAuthor("yangyk");
		gc.setSwagger2(true);
		//生成后是否打开资源管理器
		gc.setOpen(true);
		//重新生成时文件是否覆盖
		gc.setFileOverride(true);
		//去掉Service接口的首字母I
		gc.setServiceName("%sService");
		// 不需要ActiveRecord特性的请改为false
		gc.setActiveRecord(true);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columnList
		gc.setBaseColumnList(false);
		/* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
		gc.setMapperName("%sDao");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		gc.setEntityName("%sModel");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
				if (fieldType.toLowerCase().contains("tinyint(1)")) {
					return DbColumnType.INTEGER;
				}
				if (fieldType.toLowerCase().contains("date") || fieldType.toLowerCase().contains("time") || fieldType.toLowerCase().contains("year")) {
					return DbColumnType.DATE;
				}
				return super.processTypeConvert(globalConfig, fieldType);
			}
		});
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		dsc.setUrl("jdbc:mysql://localhost:3306/learning?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
		mpg.setDataSource(dsc);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.spring.boot").setMapper("mapper")
				.setService("service")
				.setController("controller")
				.setEntity("model")
				.setXml("mapper");
		mpg.setPackageInfo(pc);
		//自定义模版
		TemplateConfig templateConfig = new TemplateConfig();
		templateConfig.setEntity("templates/entity.java.vm");
		templateConfig.setMapper("templates/mapper.java.vm");
		templateConfig.setXml("templates/mapper.xml.vm");
		templateConfig.setService("templates/service.java.vm");
		templateConfig.setServiceImpl("templates/serviceImpl.java.vm");
		templateConfig.setController("templates/controller.java.vm");
		mpg.setTemplate(templateConfig);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		//restful api风格控制器
		strategy.setRestControllerStyle(true);
		//url中驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		// 此处可以修改为您的表前缀
		strategy.setTablePrefix("tb_");
		//【实体】是否为lombok模型
		strategy.setEntityLombokModel(true);
		// 需要生成的表
		strategy.setInclude("tb_sys_menu");
		mpg.setStrategy(strategy);
		mpg.execute();
	}
}
