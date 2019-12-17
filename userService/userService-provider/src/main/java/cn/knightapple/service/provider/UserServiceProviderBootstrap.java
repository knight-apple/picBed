package cn.knightapple.service.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableDubbo
@EnableApolloConfig
@SpringBootApplication
@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {"cn.knightapple.dataSource.dao"})
@ComponentScan(basePackages = {"cn.knightapple.dataSource"})
@EntityScan(basePackages = {"cn.knightapple.dataSource.entity"})
public class UserServiceProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserServiceProviderBootstrap.class)
                .run(args);
    }
}