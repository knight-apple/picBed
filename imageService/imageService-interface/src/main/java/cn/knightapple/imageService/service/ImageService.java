package cn.knightapple.imageService.service;

import cn.knightapple.imageService.dto.ImageInfoDto;
import cn.knightapple.imageService.dto.ImageSaveDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    @Transactional
    boolean addImage(MultipartFile file, Integer userId, ImageSaveDto imageSaveDto);

    @Transactional
    boolean deleteImage(Integer imageId);

    @Transactional
    boolean updateImage(ImageSaveDto imageSaveDto);

    ImageInfoDto getImageInfoById(Integer imageId);

    List<ImageInfoDto> getImageListByPhotoId(Integer photoId);

    List<ImageInfoDto> getImageListByUserId(Integer userId);
}
