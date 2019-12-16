package cn.knightapple.fileService.provider.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@PropertySource(value = {"classpath:file-upload.properties"})
public class MessageProperties {
    @Value("${message.fileSize}")
    private long fileSize;  //压缩大小
    @Value("${message.scaleRatio}")
    private double scaleRatio; //压缩比例
    @Value("${message.upPath}")
    private String upPath; //保存路径
    @Value("${message.imageType}")
    private String imageType; //图片类型
}