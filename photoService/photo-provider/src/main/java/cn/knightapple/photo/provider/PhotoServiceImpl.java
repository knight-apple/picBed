package cn.knightapple.photo.provider;

import cn.knightapple.dataSource.dao.*;
import cn.knightapple.dataSource.entity.TPhotosEntitys;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import cn.knightapple.photo.dto.PhotoInfoDto;
import cn.knightapple.photo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    GroupDao groupDao;
    @Autowired
    PhotoDao photoDao;
    @Autowired
    SecutityGroupDao secutityGroupDao;
    @Autowired
    ImageDao imageDao;
    @Autowired
    RouteMapDao routeMapDao;

    @Override
    public PhotoInfoDto addPhoto(Integer userId, String intro, String title) {
        Integer securityGroupId = groupDao.maxGroupId() + 1;
        TUsersEntitys tUsersEntitys = new TUsersEntitys();
        tUsersEntitys.setId(userId);
        TPhotosEntitys tPhotosEntitys = new TPhotosEntitys();
        tPhotosEntitys.setIntro(intro);
        tPhotosEntitys.setTitle(title);
        tPhotosEntitys.setUsersByUserId(tUsersEntitys);
        PhotoInfoDto photoInfoDto = PhotoInfoDto.parseDto(photoDao.save(tPhotosEntitys));
        return photoInfoDto;
    }

    @Override
    public boolean deletePhoto(Integer photoId) {
        Optional<TPhotosEntitys> tPhotosEntitysOptional = photoDao.findById(photoId);
        if (tPhotosEntitysOptional.equals(Optional.empty())) {
            return false;
        } else {
            TPhotosEntitys tPhotosEntitys = tPhotosEntitysOptional.get();
            routeMapDao.deleteAllByPhotoId(tPhotosEntitys.getId());
            imageDao.deleteAllByPhotosByPhotoIdEquals(tPhotosEntitys);
            secutityGroupDao.deleteByGroupIdEquals(tPhotosEntitys.getGroupId());
            photoDao.delete(tPhotosEntitys);
            return true;
        }
    }

    @Override
    public boolean updatePhoto(PhotoInfoDto photoInfoDto) {
        Optional<TPhotosEntitys> tPhotosEntitysOptional = photoDao.findById(photoInfoDto.getId());
        TPhotosEntitys tPhotosEntitys = tPhotosEntitysOptional.get();
        tPhotosEntitys = photoInfoDto.toEntity(tPhotosEntitys);
        tPhotosEntitys = photoDao.save(tPhotosEntitys);
        return true;
    }

    @Override
    public List<PhotoInfoDto> getPhotoList(Integer userId) {
        TUsersEntitys tUsersEntitys = new TUsersEntitys();
        tUsersEntitys.setId(userId);
        List<TPhotosEntitys> photosEntityList = photoDao.findByUsersByUserIdEquals(tUsersEntitys);
        List<PhotoInfoDto> photoInfoDtoList = new ArrayList<>();
        photosEntityList.forEach((e) -> photoInfoDtoList.add(PhotoInfoDto.parseDto(e)));
        return photoInfoDtoList;
    }

}
