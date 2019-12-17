package cn.knightapple.photo.service;


import cn.knightapple.photo.dto.PhotoInfoDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoService {
    @Transactional
    PhotoInfoDto addPhoto(Integer userId, String intro, String title);
    @Transactional
    boolean deletePhoto(Integer photoId);

    @Transactional
    boolean updatePhoto(PhotoInfoDto photoInfoDto);

    List<PhotoInfoDto> getPhotoList(Integer userId);
}
