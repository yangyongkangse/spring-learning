package com.spring.learning.annonation;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:35
 * Description: 多数据源事务控制器
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Documented
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface MultiDataSourceTransactional {
	/**
	 * Author: yangyk
	 * Date: 2020/9/24 14:35
	 * Description: 事务控制器数组
	 */
	String[] value() default {};
}
