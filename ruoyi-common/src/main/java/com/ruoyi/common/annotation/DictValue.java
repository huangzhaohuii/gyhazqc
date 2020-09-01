

package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 数据字典注解
 *
 * @author Mark sunlightcs@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictValue {
	String value() default "";
}
