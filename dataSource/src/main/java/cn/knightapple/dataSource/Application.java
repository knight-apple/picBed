package cn.knightapple.dataSource;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableApolloConfig
//@Configuration
//@EnableJpaRepositories(basePackages = {"cn.knightapple.dataSource.dao"})
@SpringBootApplication
//@EntityScan(basePackages = {"cn.knightapple.dataSource.entity"})
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = new SpringApplicationBuilder(Application.class)
                .run(args);
    }
}
