package cn.knightapple.restfulApi.consumer.controller;

import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.photo.dto.PhotoInfoDto;
import cn.knightapple.photo.service.PhotoService;
import cn.knightapple.photo.service.SecurityGroupService;
import cn.knightapple.restfulApi.consumer.annotation.UserLoginToken;
import cn.knightapple.restfulApi.consumer.api.CommonResult;
import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api("相册管理控制器")
@RestController
@RequestMapping(value = "/api/photo")
public class PhotoController {

    @Reference
    PhotoService photoService;

    @Reference
    SecurityGroupService securityGroupService;


    @ApiOperation("添加照册")
    @RequestMapping(value = "/addPhoto", method = RequestMethod.POST)
    @UserLoginToken
    public CommonResult addPhoto(String intro, String title, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        PhotoInfoDto photoInfoDto = photoService.addPhoto(userInfoDto.getId(), intro, title);
        return CommonResult.success(photoInfoDto, "添加成功");
    }

    @RequestMapping(value = "/updatePhoto", method = RequestMethod.POST)
    @ApiOperation("更新相册")
    @UserLoginToken
    public CommonResult updatePhoto(PhotoInfoDto photoInfoDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (photoService.hasThePhoto(photoInfoDto.getId(), userInfoDto.getId())) {
            photoService.updatePhoto(photoInfoDto);
            return CommonResult.success(photoInfoDto, "更新成功");
        } else {
            return CommonResult.validateFailed("只能修改自己的相册");
        }
    }

    @RequestMapping(value = "/deletePhoto", method = RequestMethod.POST)
    @ApiOperation("删除照册")
    @UserLoginToken
    public CommonResult deletePhoto(Integer photoId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (photoService.hasThePhoto(photoId, userInfoDto.getId())) {
            photoService.deletePhoto(photoId);
            return CommonResult.success(photoId, "删除成功");
        } else {
            return CommonResult.validateFailed("只能删除自己的相册");
        }
    }

    @UserLoginToken
    @RequestMapping(value = "/getOnePhoto", method = RequestMethod.POST)
    @ApiOperation("获取某个相册的信息")
    public CommonResult getOnePhotoInfo(Integer phothId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (photoService.hasThePhoto(phothId, userInfoDto.getId())) {
            PhotoInfoDto photoInfoDto = photoService.getPhotoByPhotoId(phothId);
            return CommonResult.success(photoInfoDto, "获取成功");
        } else {
            return CommonResult.validateFailed("这不是你的相册");
        }
    }

    @UserLoginToken
    @ApiOperation("获取用户的相册列表")
    @RequestMapping(value = "/getPhotoList", method = RequestMethod.POST)
    public CommonResult getPhotoInfoList(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        List<PhotoInfoDto> photoInfoDtoList = photoService.getPhotoList(userInfoDto.getId());
        return CommonResult.success(photoInfoDtoList, "获取成功");
    }


}
