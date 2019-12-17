package cn.knightapple.restfulApi.consumer.controller;

import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.restfulApi.consumer.annotation.PassToken;
import cn.knightapple.restfulApi.consumer.api.CommonResult;
import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
import cn.knightapple.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Api("不需要登录的")
@RequestMapping("/api/common")
@RestController
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Reference
    UserService userService;

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PassToken
    public CommonResult register(String email, String username, String password) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setType(1);
        userInfoDto.setUserName(username);
        userInfoDto.setEmail(email);
        if (UserInfoDto.isEmpty(userService.addUser(userInfoDto, password))) {
            return CommonResult.failed("添加失败");
        } else {
            return CommonResult.success("成功");
        }
    }


    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @PassToken
    public CommonResult login(String email, String password, HttpServletResponse response) {
        UserInfoDto userInfoDto = userService.findUser(email, password);
        if (UserInfoDto.isEmpty(userInfoDto)) {
            return CommonResult.failed();
        } else {
            Cookie token = new Cookie("Authorization", JwtUtil.sign(userInfoDto, password));
            response.addCookie(token);
            logger.info(token.getValue());
            return CommonResult.success("登录成功");
        }
    }
}
