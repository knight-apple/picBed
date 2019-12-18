package cn.knightapple.fileService.service;

import cn.knightapple.dataSource.entity.TImagesEntitys;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.List;

public interface FileService {

    @Transactional
    void saveImage(byte[] file, String fileName, String route, Integer photoId, Integer ImageId ,boolean isZip);

    /**
     * 只删除对应的文件映射,不删除对应的映射表
     *
     * @param routeList
     */
    @Transactional
    void deleteImageByPhotoId(List<String> routeList);

    /**
     * 同时删除这个文件和对应的映射
     *
     * @param tImagesEntitys
     */
    @Transactional
    void deleteImage(TImagesEntitys tImagesEntitys);
}
