package cn.knightapple.imageService.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;


@EnableDubboConfig
@EnableDubbo
@EnableSpringConfigured
@EnableApolloConfig
@Configuration
@SpringBootApplication
//@SpringBootApplication
//@EnableSpringDataWebSupport
@ComponentScan(basePackages = {"cn.knightapple.dataSource.config"})
public class ImageProviderBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ImageProviderBootstrap.class)
                .run(args);
    }
}