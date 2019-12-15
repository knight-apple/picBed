package cn.knightapple.photo.provider;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@EnableAutoConfiguration
public class PhotoProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PhotoProviderBootstrap.class)
                .run(args);
    }
}