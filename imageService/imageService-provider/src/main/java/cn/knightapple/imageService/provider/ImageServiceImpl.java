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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.DigestUtils;

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
    public boolean addImage(byte[] imageBytes, String fileName, Integer userId, ImageSaveDto imageSaveDto) {
        TImagesEntitys imagesEntitys = imageSaveDto.toEntity(new TImagesEntitys(), false);
        TUsersEntitys usersEntitys = new TUsersEntitys();
        usersEntitys.setId(userId);
        String route="";
        do {
            route = DigestUtils.md5DigestAsHex((route+fileName + String.valueOf(userId)).getBytes());
        } while (imageDao.countByRouteEquals(route) > 0);
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
            TImagesEntitys tImagesEntitys = imageDao.save(imagesEntitys);
            fileService.saveImage(imageBytes, fileName, route, imageSaveDto.getPhotoId(), tImagesEntitys.getId(), false);
            fileService.saveImage(imageBytes, fileName, route + "-zip", imageSaveDto.getPhotoId(), tImagesEntitys.getId(), true);
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
        fileService.deleteImage(ImageInfoDto.parseDto(tImagesEntitys));
        imageDao.delete(tImagesEntitys);
        return true;
    }

    @Override
    public boolean belongTheUser(Integer imageId, Integer userId) {
        Optional<TImagesEntitys> tImagesEntitysOptional = imageDao.findById(imageId);
        if (!tImagesEntitysOptional.equals(Optional.empty())) {
            TImagesEntitys tImagesEntitys = tImagesEntitysOptional.get();
            if (Integer.compare(tImagesEntitys.getUsersByUserId().getId(), userId) == 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public boolean belongPhoto(Integer imageId, Integer photoId) {
        Optional<TImagesEntitys> tImagesEntitysOptional = imageDao.findById(imageId);
        if (!tImagesEntitysOptional.equals(Optional.empty())) {
            TImagesEntitys tImagesEntitys = tImagesEntitysOptional.get();
            if (Integer.compare(tImagesEntitys.getPhotosByPhotoId().getId(), photoId) == 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
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
    public List<ImageInfoDto> getImageListByPhotoId(Integer photoId, Integer pageNum,Integer pageSize) {
        TPhotosEntitys tPhotosEntitys = new TPhotosEntitys();
        tPhotosEntitys.setId(photoId);
        List<TImagesEntitys> imagesEntitysList = imageDao.findAllByPhotosByPhotoIdEqualsOrderByCreateTimeDesc(tPhotosEntitys,PageRequest.of(pageNum,pageSize));
        List<ImageInfoDto> imageInfoDtoList = new ArrayList<>();
        imagesEntitysList.forEach(e -> {
            imageInfoDtoList.add(ImageInfoDto.parseDto(e));
        });
        return imageInfoDtoList;
    }

    @Override
    public List<ImageInfoDto> getImageListByUserId(Integer userId, Integer pageNum,Integer pageSize) {
        TUsersEntitys tUsersEntitys = new TUsersEntitys();
        tUsersEntitys.setId(userId);
        List<TImagesEntitys> imagesEntitysList = imageDao.findByUsersByUserIdEqualsOrderByCreateTimeDesc(tUsersEntitys,PageRequest.of(pageNum-1,pageSize));
        List<ImageInfoDto> imageInfoDtoList = new ArrayList<>();
        imagesEntitysList.forEach(e -> {
            imageInfoDtoList.add(ImageInfoDto.parseDto(e));
        });
        return imageInfoDtoList;
    }
}