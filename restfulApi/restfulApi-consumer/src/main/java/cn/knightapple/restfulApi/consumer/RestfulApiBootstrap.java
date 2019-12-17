package cn.knightapple.restfulApi.consumer;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableDubbo
@EnableApolloConfig
@SpringBootApplication(scanBasePackages = {
        "cn.knightapple.dataSource",
        "cn.knightapple.restfulApi.consumer"})
@SpringBootConfiguration
@EnableSpringConfigured
@EnableSpringDataWebSupport
//@EnableSwaggerBootstrapUI
//@EnableSwagger2
//@ComponentScan(basePackages = {
//        "cn.knightapple.dataSource",
//        "cn.knightapple.restfulApi.consumer"})
public class RestfulApiBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(RestfulApiBootstrap.class).start();
    }
}
