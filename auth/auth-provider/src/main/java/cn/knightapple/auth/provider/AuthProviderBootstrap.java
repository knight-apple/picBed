package cn.knightapple.auth.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@EnableAutoConfiguration
@EnableApolloConfig
public class AuthProviderBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = new SpringApplicationBuilder(AuthProviderBootstrap.class)
                .run(args);
        ConfigurableEnvironment env = app.getEnvironment();
    }

}