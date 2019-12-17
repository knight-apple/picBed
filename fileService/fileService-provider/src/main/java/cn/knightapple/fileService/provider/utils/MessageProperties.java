package cn.knightapple.fileService.provider.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


//@Getter
//@Setter
@Component
@PropertySource(value = "")
public class MessageProperties {
    @Autowired
    Environment environment;

    public double getScaleRatio() {
        return Double.parseDouble(environment.getProperty("message.scaleRatio"));
    }

    public long getFileSize() {
        return Long.parseLong(environment.getProperty("message.fileSize"));
    }

    public String getImageType() {
        return environment.getProperty("message.imageType");
    }

    public String getUpPath() {
        return environment.getProperty("message.upPath");
    }
}