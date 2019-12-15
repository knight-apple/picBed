package cn.knightapple.service.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@EnableAutoConfiguration
@EnableDubbo
public class UserServiceProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserServiceProviderBootstrap.class)
                .run(args);
    }
}