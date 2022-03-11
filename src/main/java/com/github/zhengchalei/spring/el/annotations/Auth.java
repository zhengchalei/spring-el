package com.github.zhengchalei.spring.el.annotations;

import java.lang.annotation.*;

/**
 * 鉴权注解
 *
 * @author 郑查磊
 * @date 2022/03/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    String checkRole() default "";

    boolean checkIsAdmin() default false;
}
