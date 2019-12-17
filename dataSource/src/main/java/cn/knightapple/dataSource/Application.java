package cn.knightapple.dataSource;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableApolloConfig
@Configuration
@SpringBootApplication
//@EntityScan(basePackages = {"cn.knightapple.dataSource.entity"})
//@EnableJpaRepositories(basePackages = {"cn.knightapple.dataSource.dao"})
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = new SpringApplicationBuilder(Application.class)
                .run(args);
    }
}
