package cn.knightapple.fileService.service;

import cn.knightapple.imageService.dto.ImageInfoDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileService {

    @Transactional
    void saveImage(byte[] file, String fileName, String route, Integer photoId, Integer ImageId, boolean isZip);

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
     * @param imageInfoDto
     */
    @Transactional
    void deleteImage(ImageInfoDto imageInfoDto);
}
