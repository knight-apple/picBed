package cn.knightapple.imageService.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class OtherConfig {
    @Bean(name = "multipartResolver")
    CommonsMultipartResolver getResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(50*1024*1024);//50M
        commonsMultipartResolver.setMaxInMemorySize(4096);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}
