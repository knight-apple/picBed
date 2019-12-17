package cn.knightapple.restfulApi.consumer;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


@EnableDubbo
@EnableApolloConfig
@SpringBootApplication(scanBasePackages = {
        "cn.knightapple.dataSource",
        "cn.knightapple.restfulApi.consumer"})
@SpringBootConfiguration
@EnableSpringConfigured
//@ComponentScan(basePackages = {
//        "cn.knightapple.dataSource",
//        "cn.knightapple.restfulApi.consumer"})
public class RestfulApiBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(RestfulApiBootstrap.class).start();
    }
}
