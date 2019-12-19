package cn.knightapple.photo.dto;

import cn.knightapple.dataSource.entity.TPhotosEntitys;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PhotoInfoDto implements Serializable {
    private Integer id;
    private String intro;
    private String title;
    private Integer groupId;

    public static PhotoInfoDto parseDto(TPhotosEntitys tPhotosEntitys) {
        PhotoInfoDto photoInfoDto = new PhotoInfoDto();
        photoInfoDto.title = tPhotosEntitys.getTitle();
        photoInfoDto.intro = tPhotosEntitys.getIntro();
        photoInfoDto.id = tPhotosEntitys.getId();
        photoInfoDto.groupId = tPhotosEntitys.getSecurityGroupId();
        return photoInfoDto;
    }

    public TPhotosEntitys toEntity(TPhotosEntitys tPhotosEntitys) {
        if (this.intro != null) {
            tPhotosEntitys.setIntro(intro);
        }
        if (this.title != null) {
            tPhotosEntitys.setTitle(title);
        }
        if(this.groupId != null){
            tPhotosEntitys.setSecurityGroupId(groupId);
        }
        return tPhotosEntitys;
    }
}
