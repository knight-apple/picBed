package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TImagesEntitys;
import cn.knightapple.dataSource.entity.TPhotosEntitys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageDao extends JpaRepository<TImagesEntitys,Integer> {

    //通过用户id查找照片
    List<TImagesEntitys> findByUsersByUserIdEqualsOrderByCreateTimeDesc(Integer userId);
    List<TImagesEntitys> findAllByPhotosByPhotoIdEqualsOrderByCreateTimeDesc(Integer photoId);

    void deleteAllByPhotosByPhotoIdEquals(TPhotosEntitys tPhotosEntitys);

    long countByRouteEquals(String route);
}
