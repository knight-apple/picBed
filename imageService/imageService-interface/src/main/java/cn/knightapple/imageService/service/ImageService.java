package cn.knightapple.imageService.service;

import cn.knightapple.imageService.dto.ImageInfoDto;
import cn.knightapple.imageService.dto.ImageSaveDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageService {
    @Transactional
    boolean addImage(byte[] imageBytes, String fileName, Integer userId, ImageSaveDto imageSaveDto);

    @Transactional
    boolean deleteImage(Integer imageId);

    boolean belongTheUser(Integer imageId, Integer userId);

    boolean belongPhoto(Integer imageId, Integer photoId);

    @Transactional
    boolean updateImage(ImageSaveDto imageSaveDto);

    ImageInfoDto getImageInfoById(Integer imageId);

    List<ImageInfoDto> getImageListByPhotoId(Integer photoId, Integer pageNum, Integer pageSize);

    List<ImageInfoDto> getImageListByUserId(Integer userId, Integer pageNum, Integer pageSize);
}
