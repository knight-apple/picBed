package cn.knightapple.imageService.dto;

import cn.knightapple.dataSource.entity.TImagesEntitys;
import cn.knightapple.dataSource.entity.TPhotosEntitys;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class ImageSaveDto implements Serializable {
    private Integer id;
    //    private String route;
//    private String zipRoute;
    private String title;
    private String intro;
    private Integer type;
    //    private Timestamp createTime;
    private Integer photoId;

    //    private Integer userId;

    /**
     * 需要添加的数据有
     * 路由,压缩路由,创建时间,用户id
     * @param imagesEntitys 要负载的图片实体实例
     * @param isUpdate 是否为更新模式
     * @return
     */
    public TImagesEntitys toEntity(TImagesEntitys imagesEntitys, boolean isUpdate) {
        if (isUpdate && id != null && !id.equals(0)) {
            imagesEntitys.setId(id);
        }
        if (title != null) {
            imagesEntitys.setTitle(title);
        }
        if (intro != null) {
            imagesEntitys.setIntro(intro);
        }
        if (type != null) {
            imagesEntitys.setType(type);
        } else {
            imagesEntitys.setType(0);
        }
        if (photoId != null) {
            TPhotosEntitys tPhotosEntitys = new TPhotosEntitys();
            tPhotosEntitys.setId(photoId);
            imagesEntitys.setPhotosByPhotoId(tPhotosEntitys);
        }
        return imagesEntitys;
    }
}
