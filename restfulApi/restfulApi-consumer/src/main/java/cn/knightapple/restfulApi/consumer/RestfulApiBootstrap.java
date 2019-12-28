package cn.knightapple.restfulApi.consumer;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


@EnableDubbo
@EnableApolloConfig
@SpringBootApplication(scanBasePackages = {
        "cn.knightapple.dataSource",
        "cn.knightapple.restfulApi.consumer"}
//        , exclude = {MultipartAutoConfiguration.class}
        )
//        , exclude = {MultipartAutoConfiguration.class})
@SpringBootConfiguration
@EnableSpringConfigured
//@EnableSpringDataWebSupport
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
//@ComponentScan(basePackages = {
//        "cn.knightapple.dataSource",
//        "cn.knightapple.restfulApi.consumer"})
public class RestfulApiBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(RestfulApiBootstrap.class).start();
    }
}
