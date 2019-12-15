package cn.knightapple.photo.service;


import cn.knightapple.photo.dto.PhotoInfoDto;

import java.util.List;

public interface PhotoService {
    PhotoInfoDto addPhoto(Integer userId, String intro, String title);

    boolean deletePhoto(Integer photoId);

    boolean updatePhoto(PhotoInfoDto photoInfoDto);

    List<PhotoInfoDto> getPhotoList(Integer userId);
}
