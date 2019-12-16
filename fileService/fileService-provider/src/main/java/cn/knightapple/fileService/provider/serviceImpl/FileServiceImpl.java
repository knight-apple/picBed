package cn.knightapple.fileService.provider.serviceImpl;

import cn.knightapple.dataSource.dao.RouteMapDao;
import cn.knightapple.dataSource.entity.TImagesEntitys;
import cn.knightapple.dataSource.entity.TRouteMapEntitys;
import cn.knightapple.fileService.provider.config.FileUploadUtils;
import cn.knightapple.fileService.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileServiceImpl implements FileService {
    @Autowired
    RouteMapDao routeMapDao;
    @Autowired
    FileUploadUtils fileUploadUtils;

    @Override
    public void saveImage(MultipartFile file, String route, Integer photoId, boolean isZip) {
        String path = fileUploadUtils.save(String.valueOf(photoId), file, isZip);
        TRouteMapEntitys tRouteMapEntitys = new TRouteMapEntitys();
        tRouteMapEntitys.setRoute(route);
        tRouteMapEntitys.setRealUrl(path);
        routeMapDao.save(tRouteMapEntitys);
    }

    @Override
    public void deleteImageByPhotoId(List<String> routeList) {
        routeList.forEach(e -> {
            FileUploadUtils.delete(routeMapDao.getRealUrl(e));
//            routeMapDao.deleteByRouteEquals(e);
        });
    }

    @Override
    public void deleteImage(TImagesEntitys tImagesEntitys) {
        FileUploadUtils.delete(routeMapDao.getRealUrl(tImagesEntitys.getRoute()));
        FileUploadUtils.delete(routeMapDao.getRealUrl(tImagesEntitys.getZipRoute()));
        routeMapDao.deleteAllByImageIdEquals(tImagesEntitys.getId());
    }
}
