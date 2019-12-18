package cn.knightapple.imageService.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import javax.xml.ws.WebServiceProvider;


@EnableDubboConfig
@EnableDubbo
@EnableSpringConfigured
//@SpringBootApplication
//@EnableSpringDataWebSupport
@ComponentScan(basePackages = {"cn.knightapple.dataSource.config"})
public class ImageProviderBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ImageProviderBootstrap.class)
                .run(args);
    }
}