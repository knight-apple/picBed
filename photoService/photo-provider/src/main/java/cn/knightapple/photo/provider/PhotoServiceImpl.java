package cn.knightapple.photo.provider;

import cn.knightapple.dataSource.dao.*;
import cn.knightapple.dataSource.entity.TImagesEntitys;
import cn.knightapple.dataSource.entity.TPhotosEntitys;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import cn.knightapple.fileService.service.FileService;
import cn.knightapple.photo.dto.PhotoInfoDto;
import cn.knightapple.photo.service.PhotoService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    @Reference
    FileService fileService;

    @Override
    public PhotoInfoDto addPhoto(Integer userId, String intro, String title) {
        Integer securityGroupId = groupDao.maxGroupId() + 1;
        TUsersEntitys tUsersEntitys = new TUsersEntitys();
        tUsersEntitys.setId(userId);
        TPhotosEntitys tPhotosEntitys = new TPhotosEntitys();
        tPhotosEntitys.setIntro(intro);
        tPhotosEntitys.setTitle(title);
        tPhotosEntitys.setUsersByUserId(tUsersEntitys);
        tPhotosEntitys.setSecurityGroupId(securityGroupId);

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
            List<TImagesEntitys> imagesEntitysList = imageDao.findAllByPhotosByPhotoIdEqualsOrderByCreateTimeDesc(tPhotosEntitys);
            List<String> routeList = new ArrayList<>();
            imagesEntitysList.forEach((e) -> {
                routeList.add(e.getRoute());
                routeList.add(e.getZipRoute());
            });
            fileService.deleteImageByPhotoId(routeList);
            routeMapDao.deleteAllByPhotoId(tPhotosEntitys.getId());
            imageDao.deleteAllByPhotosByPhotoIdEquals(tPhotosEntitys);
            secutityGroupDao.deleteByGroupIdEquals(tPhotosEntitys.getSecurityGroupId());
            photoDao.deleteByIdEquals(tPhotosEntitys.getId());
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

    @Override
    public PhotoInfoDto getPhotoByPhotoId(Integer photoId) {
        if (photoId != null) {
            Optional<TPhotosEntitys> tPhotosEntitysOptional = photoDao.findById(photoId);
            if (tPhotosEntitysOptional.equals(Optional.empty())) {
                throw new IllegalArgumentException("该相册不存在");
            }
            return PhotoInfoDto.parseDto(tPhotosEntitysOptional.get());
        }
        throw new IllegalArgumentException("相册ID不能为空");
    }

    @Override
    public boolean hasThePhoto(Integer photoId, Integer userId) {
        Optional<TPhotosEntitys> tPhotosEntitysOptional = photoDao.findById(photoId);
        if (!tPhotosEntitysOptional.equals(Optional.empty())) {
            return Integer.compare(tPhotosEntitysOptional.get().getUsersByUserId().getId(),userId)==0;
        }
        throw new IllegalArgumentException("该相册不存在");
    }

}
