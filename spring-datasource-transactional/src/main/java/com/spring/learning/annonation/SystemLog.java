package com.spring.learning.annonation;

import com.spring.learning.util.Constant;
import org.apache.logging.log4j.util.Strings;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:35
 * Description: 自定义注解
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Documented
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface SystemLog {

	String description() default Strings.EMPTY;

	String type() default Constant.SERVICE_TYPE;

}
