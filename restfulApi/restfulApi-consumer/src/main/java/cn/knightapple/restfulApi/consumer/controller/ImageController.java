package cn.knightapple.restfulApi.consumer.controller;

import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.imageService.dto.ImageInfoDto;
import cn.knightapple.imageService.dto.ImageSaveDto;
import cn.knightapple.imageService.service.ImageService;
import cn.knightapple.photo.service.PhotoService;
import cn.knightapple.restfulApi.consumer.annotation.UserLoginToken;
import cn.knightapple.restfulApi.consumer.api.CommonResult;
import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Api("图片管理控制器")
@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

    @Reference
    ImageService imageService;

    @Reference
    PhotoService photoService;

    @ApiOperation("上传添加图片")
    @UserLoginToken
    @RequestMapping(value = "addImage", method = RequestMethod.POST)
    public CommonResult addImage(MultipartFile file, String title, String intro, Integer photoId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        ImageSaveDto imageSaveDto = new ImageSaveDto();
        imageSaveDto.setIntro(intro);
        imageSaveDto.setTitle(title);
        imageSaveDto.setType(1);
        imageSaveDto.setPhotoId(photoId);
        byte[] imageBytes = null;
        try {
            imageBytes = file.getBytes();
        } catch (IOException e) {
            return CommonResult.failed("图片错误");
        }
        if (imageService.addImage(imageBytes, file.getOriginalFilename(), userInfoDto.getId(), imageSaveDto)) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("通过地址添加图片")
    @UserLoginToken
    @RequestMapping(value = "addImageByUrl", method = RequestMethod.POST)
    public CommonResult addImageByUrl(MultipartFile file, String title, String intro, Integer photoId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        ImageSaveDto imageSaveDto = new ImageSaveDto();
        imageSaveDto.setIntro(intro);
        imageSaveDto.setTitle(title);
        imageSaveDto.setType(1);
        imageSaveDto.setPhotoId(photoId);
        byte[] imageBytes = null;
        try {
            imageBytes = file.getBytes();
        } catch (IOException e) {
            return CommonResult.failed("图片错误");
        }
        if (imageService.addImage(imageBytes, file.getOriginalFilename(), userInfoDto.getId(), imageSaveDto)) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败");
    }

    @ApiOperation("更新图片信息")
    @UserLoginToken
    @RequestMapping(value = "updateImageInfo", method = RequestMethod.POST)
    public CommonResult updateImage(ImageSaveDto imageSaveDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (imageService.belongTheUser(imageSaveDto.getId(), userInfoDto.getId())) {
            if (imageService.updateImage(imageSaveDto)) {
                return CommonResult.success("更新成功");
            } else {
                return CommonResult.failed("更新失败");
            }
        } else {
            return CommonResult.failed("只能更新自己的图片");
        }
    }

    @ApiOperation("移动图片到另一个相册")
    @UserLoginToken
    @RequestMapping(value = "moveToAnatherPhoto", method = RequestMethod.POST)
    public CommonResult moveImage(Integer imageId, Integer photoTo, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (imageService.belongTheUser(imageId, userInfoDto.getId()) && imageService.belongPhoto(imageId, photoTo)) {
            return CommonResult.failed("目标相册不能是所在相册");
        }

        ImageSaveDto imageSaveDto = new ImageSaveDto();
        imageSaveDto.setPhotoId(photoTo);
        imageSaveDto.setId(imageId);
        if (imageService.updateImage(imageSaveDto)) {
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }

    @ApiOperation("获取相册的图片列表")
    @UserLoginToken
    @RequestMapping(value = "getImageListByPhoto", method = RequestMethod.POST)
    public CommonResult getImageListByPhotoId(Integer photoId,
                                              @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                              HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (!photoService.hasThePhoto(photoId, userInfoDto.getId())) {
            return CommonResult.failed("只能获取自己的图片");
        }
        List<ImageInfoDto> imageInfoDtoList = imageService.getImageListByPhotoId(photoId, pageNum - 1, pageSize);
        return CommonResult.success(imageInfoDtoList, "获取成功");
    }

    @ApiOperation("获取用户的所有图片列表")
    @UserLoginToken
    @RequestMapping(value = "getImageListByUser", method = RequestMethod.POST)
    public CommonResult getImageByUser(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        List<ImageInfoDto> imageInfoDtoList = imageService.getImageListByUserId(userInfoDto.getId(), pageNum , pageSize);
        return CommonResult.success(imageInfoDtoList, "获取成功");
    }

    @ApiOperation("通过照片Id获取图片信息")
    @UserLoginToken
    @RequestMapping(value = "getImagerInfoById", method = RequestMethod.POST)
    public CommonResult getImageByImageId(Integer imageId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (!imageService.belongTheUser(imageId, userInfoDto.getId())) {
            return CommonResult.validateFailed("只能获取自己的图片");
        } else {
            return CommonResult.success(imageService.getImageInfoById(imageId), "获取成功");
        }
    }

    @ApiOperation("通过照片ID删除照片")
    @UserLoginToken
    @RequestMapping(value = "deleteImageById", method = RequestMethod.POST)
    public CommonResult deleteImageByImageId(Integer imageId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (!imageService.belongTheUser(imageId, userInfoDto.getId())) {
            return CommonResult.validateFailed("只能删除自己的图片");
        } else {
            return CommonResult.success(imageService.deleteImage(imageId), "获取成功");
        }
    }
}
