package cn.knightapple.restfulApi.consumer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 用于进行图片访问验证的
 * 如果已经登录,则直接可以访问
 * 如果未登录,则需要referer认证
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageReferered {
    boolean required() default true;
}
