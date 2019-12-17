package cn.knightapple.photo.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDubbo
@EnableApolloConfig
@SpringBootApplication
@SpringBootConfiguration
//@EnableJpaRepositories(basePackages = {"cn.knightapple.dataSource.dao"})
//@ComponentScan(basePackages = {"cn.knightapple.dataSource"})
//@EntityScan(basePackages = {"cn.knightapple.dataSource.entity"})
public class PhotoProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PhotoProviderBootstrap.class)
                .run(args);
    }
}