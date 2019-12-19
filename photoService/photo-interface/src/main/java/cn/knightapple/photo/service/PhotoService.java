package cn.knightapple.photo.service;


import cn.knightapple.photo.dto.PhotoInfoDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoService {
    /**
     * 添加照片
     * @param userId 用户的id 相册介绍 相册的标题
     * @param intro
     * @param title
     * @return
     */
    @Transactional
    PhotoInfoDto addPhoto(Integer userId, String intro, String title);

    /**
     * 删除某个相册
     * @param photoId 相册的id
     * @return
     */
    @Transactional
    boolean deletePhoto(Integer photoId);

    /**
     * 要更新的相册的信息
     * @param photoInfoDto
     * @return
     */
    @Transactional
    boolean updatePhoto(PhotoInfoDto photoInfoDto);

    /**
     * 获取相册的列表
     * @param userId
     * @return
     */
    List<PhotoInfoDto> getPhotoList(Integer userId);

    PhotoInfoDto getPhotoByPhotoId(Integer photoId);

    boolean hasThePhoto(Integer photoId, Integer userId);

    Integer getSecurityGroupId(Integer photoId);
}
