package cn.knightapple.restfulApi.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * 新建Token拦截器
 * @Title: InterceptorConfig.java
 * @author MRC
 * @date 2019年5月27日 下午5:33:28
 * @version V1.0
 */
@Configuration
//@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("添加拦截器");
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/api/**");
        registry.addInterceptor(imageRefererInterceptor())
                .addPathPatterns("/access/**");
//                .excludePathPatterns("/swagger-ui.html","/doc.html");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/swagger-ui.html","/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();// 自己写的拦截器
    }

    @Bean
    ImageRefererInterceptor imageRefererInterceptor(){return new ImageRefererInterceptor();}

}