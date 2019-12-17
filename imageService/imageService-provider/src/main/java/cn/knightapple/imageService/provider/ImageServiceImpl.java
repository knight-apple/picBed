package cn.knightapple.imageService.provider;

import cn.knightapple.dataSource.dao.ImageDao;
import cn.knightapple.dataSource.dao.PhotoDao;
import cn.knightapple.dataSource.entity.TImagesEntitys;
import cn.knightapple.dataSource.entity.TPhotosEntitys;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import cn.knightapple.fileService.service.FileService;
import cn.knightapple.imageService.dto.ImageInfoDto;
import cn.knightapple.imageService.dto.ImageSaveDto;
import cn.knightapple.imageService.service.ImageService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Reference
    FileService fileService;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private PhotoDao photoDao;

    @Override
    public boolean addImage(MultipartFile file, Integer userId, ImageSaveDto imageSaveDto) {
        TImagesEntitys imagesEntitys = imageSaveDto.toEntity(new TImagesEntitys(), false);
        TUsersEntitys usersEntitys = new TUsersEntitys();
        usersEntitys.setId(userId);
        String route;
        do {
            route = DigestUtils.md5DigestAsHex((file.getOriginalFilename() + String.valueOf(userId)).getBytes());
        }
        while (imageDao.countByRouteEquals(route) > 0);
        imagesEntitys.setRoute(route);
        imagesEntitys.setZipRoute(route + "-zip");
        imagesEntitys.setCreateTime(new Timestamp(System.currentTimeMillis()));
        imagesEntitys.setUsersByUserId(usersEntitys);
        Optional<TPhotosEntitys> tImagesEntitysOptional = photoDao.findById(imageSaveDto.getPhotoId());
        if (tImagesEntitysOptional.equals(Optional.empty())) {
            return false;
        }
        TPhotosEntitys tPhotosEntitys = tImagesEntitysOptional.get();
        if (userId.equals(tPhotosEntitys.getUsersByUserId().getId())) {
            fileService.saveImage(file, route, imageSaveDto.getPhotoId(), false);
            fileService.saveImage(file, route + "-zip", imageSaveDto.getPhotoId(), true);
            imageDao.save(imagesEntitys);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteImage(Integer imageId) {
        Optional<TImagesEntitys> tImagesEntitysOptional = imageDao.findById(imageId);
        if (tImagesEntitysOptional.equals(Optional.empty())) {
            return false;
        }
        TImagesEntitys tImagesEntitys = tImagesEntitysOptional.get();
        fileService.deleteImage(tImagesEntitys);
        imageDao.delete(tImagesEntitys);
        return true;
    }

    @Override
    public boolean updateImage(ImageSaveDto imageSaveDto) {
        Optional<TImagesEntitys> tImagesEntitysOptional = imageDao.findById(imageSaveDto.getId());
        if (tImagesEntitysOptional.equals(Optional.empty())) {
            return false;
        }
        TImagesEntitys tImagesEntitys = tImagesEntitysOptional.get();
        tImagesEntitys = imageSaveDto.toEntity(tImagesEntitys, true);
        imageDao.save(tImagesEntitys);
        return true;
    }

    @Override
    public ImageInfoDto getImageInfoById(Integer imageId) {
        Optional<TImagesEntitys> tImagesEntitysOptional = imageDao.findById(imageId);
        if (tImagesEntitysOptional.equals(Optional.empty())) {
            return null;
        }
        TImagesEntitys tImagesEntitys = tImagesEntitysOptional.get();
        ImageInfoDto imageInfoDto = ImageInfoDto.parseDto(tImagesEntitys);
        return imageInfoDto;
    }

    @Override
    public List<ImageInfoDto> getImageListByPhotoId(Integer photoId) {
        List<TImagesEntitys> imagesEntitysList = imageDao.findAllByPhotosByPhotoIdEqualsOrderByCreateTimeDesc(photoId);
        List<ImageInfoDto> imageInfoDtoList = new ArrayList<>();
        imagesEntitysList.forEach(e -> {
            imageInfoDtoList.add(ImageInfoDto.parseDto(e));
        });
        return imageInfoDtoList;
    }

    @Override
    public List<ImageInfoDto> getImageListByUserId(Integer userId) {
        List<TImagesEntitys> imagesEntitysList = imageDao.findByUsersByUserIdEqualsOrderByCreateTimeDesc(userId);
        List<ImageInfoDto> imageInfoDtoList = new ArrayList<>();
        imagesEntitysList.forEach(e -> {
            imageInfoDtoList.add(ImageInfoDto.parseDto(e));
        });
        return imageInfoDtoList;
    }
}
