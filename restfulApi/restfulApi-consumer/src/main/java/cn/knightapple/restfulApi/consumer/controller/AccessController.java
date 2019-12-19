package cn.knightapple.restfulApi.consumer.controller;

import cn.knightapple.fileService.service.AccessService;
import cn.knightapple.restfulApi.consumer.annotation.ImageReferered;
import cn.knightapple.restfulApi.consumer.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;


@Api("图片访问控制器")
@RequestMapping("/access/image")
@RestController
public class AccessController {

    @Reference
    AccessService accessService;

    @RequestMapping(value = "/getImage/{route}", method = RequestMethod.GET)
    @ApiOperation("获取图片")
    @ImageReferered
    public CommonResult getImage(@PathVariable String route, HttpServletRequest request, HttpServletResponse response) {

        String imageUrl = accessService.getImageUrl(route);
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
            response.setContentType("image/png");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.success("success");
    }
}
