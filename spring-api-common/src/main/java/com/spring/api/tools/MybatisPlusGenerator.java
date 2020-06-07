package com.spring.api.tools;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author: yangyongkang
 * Date: 2020/1/7
 * Time: 11:16
 * Description:自动生成工具
 */
public class MybatisPlusGenerator {
	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 选择 freemarker 引擎，默认 Veloctiy
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setSwagger2(true);
		gc.setAuthor("yangyk");
		gc.setOutputDir("I:\\源码\\springboot-learning\\src\\main\\java\\com\\spring\\boot");
		// 是否覆盖同名文件，默认是false
		gc.setFileOverride(false);
		// 不需要ActiveRecord特性的请改为false
		gc.setActiveRecord(true);
		// XML 二级缓存
		gc.setEnableCache(false);
		// XML ResultMap
		gc.setBaseResultMap(true);
		// XML columList
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
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
		// 此处可以修改为您的表前缀
		strategy.setTablePrefix(new String[]{"tb_"});
		//【实体】是否为lombok模型
		strategy.setEntityLombokModel(true);
		// 表名生成策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 需要生成的表
		strategy.setInclude(new String[]{"tb_sys_menu"});
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		//自定义实体父类
		//strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		//strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		//strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.baomidou.demo.TestController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuilderModel(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.spring.boot").setMapper("mapper")
				.setService("service")
				.setController("controller")
				.setEntity("model")
				.setXml("mapper");
		mpg.setPackageInfo(pc);
		// 执行生成
		mpg.execute();

		// 打印注入设置【可无】
//        System.err.println(mpg.getCfg().getMap().get("abc"));
	}
}
