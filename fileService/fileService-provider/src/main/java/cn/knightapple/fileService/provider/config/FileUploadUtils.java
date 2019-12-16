package cn.knightapple.fileService.provider.config;

import cn.knightapple.fileService.provider.utils.MessageProperties;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUploadUtils {

    @Autowired
    private MessageProperties config;

    public static String getRealName(String filename) {
        int index = filename.lastIndexOf("\\") + 1;
        return filename.substring(index);

    }

    public static boolean delete(String path) {
        path = path.replaceAll("/", File.separator);
        File target = new File(path);
        if (target.exists() && target.isFile()) {
            target.delete();
            return true;
        }

        return false;
    }

    public static String getUUIDFileName(String filename) {
        return UUID.randomUUID().toString();
    }

    public static String getEnds(String fileName) {
        return StringUtils.getFilenameExtension(fileName);
    }

    public String save(String groupId, MultipartFile file ,boolean isZip) {
        String path = config.getUpPath() + File.separator + groupId + File.separator;
        String fileName = getUUIDFileName(file.getOriginalFilename());
        File targetPath = new File(path);
        File target = new File(path + fileName);

        //检查目录是否存在
        if (!targetPath.exists()) {
            targetPath.mkdirs();
        }
        //检查文件名是否已存在
        while (target.exists()) {
            fileName = getUUIDFileName(target.getName() + 1);
            target = new File(path + File.separator + fileName);
        }

        //检查文件后缀是否正确
        boolean flag = false;
        String[] IMAGE_TYPE = config.getImageType().split(",");
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                flag = true;
                break;
            }
        }
        //文件类型正确时
        if (flag) {
            try {
                //检查文件大小
                if (isZip&&file.getSize() > config.getFileSize()) {
                    Thumbnails.of(file.getInputStream()).scale(config.getScaleRatio()).toFile(target);
                } else {
                    //存储该文件
                    file.transferTo(target);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("文件类型错误");
        }
        return path + "/" + groupId + "/" + fileName;
    }
}