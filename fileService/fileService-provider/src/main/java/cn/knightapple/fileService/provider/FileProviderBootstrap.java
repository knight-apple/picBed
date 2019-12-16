package cn.knightapple.fileService.provider;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@EnableAutoConfiguration
@EnableApolloConfig
public class FileProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FileProviderBootstrap.class)
                .run(args);
    }
}