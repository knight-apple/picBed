package cn.knightapple.imageService.service;

import cn.knightapple.imageService.dto.ImageSaveDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    boolean addImage(MultipartFile file, Integer userId, ImageSaveDto imageSaveDto);
}
