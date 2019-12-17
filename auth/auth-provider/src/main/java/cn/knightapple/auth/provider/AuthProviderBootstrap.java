package cn.knightapple.auth.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableDubbo
@EnableApolloConfig
@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackages = {"cn.knightapple.dataSource.dao"})
public class AuthProviderBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = new SpringApplicationBuilder(AuthProviderBootstrap.class)
                .run(args);
        ConfigurableEnvironment env = app.getEnvironment();
    }

}