package cn.knightapple.imageService.dto;

import cn.knightapple.dataSource.entity.TImagesEntitys;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ImageInfoDto {
    private int id;
    private String route;
    private String title;
    private String intro;
    public static ImageInfoDto parseDto(TImagesEntitys tImagesEntitys){
        ImageInfoDto imageInfoDto = new ImageInfoDto();
        imageInfoDto.id = tImagesEntitys.getId();
        imageInfoDto.intro = tImagesEntitys.getIntro();
        imageInfoDto.route = tImagesEntitys.getRoute();
        imageInfoDto.title = tImagesEntitys.getTitle();
        return imageInfoDto;
    }

}
