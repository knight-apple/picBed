package cn.knightapple.restfulApi.consumer.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PhotoRefererList {
    Integer photoId;
    String title;
    String intro;
    String firstPhoto;
    List<String> referer;
    List<Integer> refererIds;
}
