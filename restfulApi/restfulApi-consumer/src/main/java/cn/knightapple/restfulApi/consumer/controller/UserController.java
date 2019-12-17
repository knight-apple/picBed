package cn.knightapple.restfulApi.consumer.controller;

import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.restfulApi.consumer.annotation.UserLoginToken;
import cn.knightapple.restfulApi.consumer.api.CommonResult;
import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
import cn.knightapple.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("用户管理控制器")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Reference
    UserService userService;

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.PATCH)
    @UserLoginToken
    public CommonResult updateUser(String username, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (UserInfoDto.isEmpty(userInfoDto)) {
            return CommonResult.failed();
        }
        userInfoDto.setUserName(username);
        userInfoDto = userService.updateUser(userInfoDto);
        if (UserInfoDto.isEmpty(userInfoDto)) {
            return CommonResult.failed("更新失败");
        }
        return CommonResult.success(userInfoDto, "更新成功");
    }

    @ApiOperation("修改用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PATCH)
    @UserLoginToken
    public CommonResult updatePassword(String password, HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (UserInfoDto.isEmpty(userInfoDto)) {
            return CommonResult.failed();
        }
        if (userService.updatePassword(userInfoDto.getId(), password)) {
            Cookie cookietoken = new Cookie("token",JwtUtil.sign(userInfoDto, password));
            response.addCookie(cookietoken);
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("更新失败");
        }
    }


}

