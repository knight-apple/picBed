package cn.knightapple.restfulApi.consumer.controller;

import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.photo.service.SecurityGroupService;
import cn.knightapple.restfulApi.consumer.annotation.UserLoginToken;
import cn.knightapple.restfulApi.consumer.api.CommonResult;
import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.util.Pair;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Api("安全组管理控制器")
@RestController
@RequestMapping(value = "/api/security")
public class SecurityGroupController {

    @Reference
    SecurityGroupService securityGroupService;

    @UserLoginToken
    @ApiOperation("给安全组添加referer")
    @RequestMapping(value = "/addReferer", method = RequestMethod.POST)
    public CommonResult addSecurityGroup(Integer securityGroupId, @RequestParam List<String> referered, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (!securityGroupService.hasTheSecurityGroup(userInfoDto.getId(),securityGroupId)) {
            return CommonResult.failed("只能给自己的安全组添加referer");
        }
        if (securityGroupService.addSecurityGroup(userInfoDto.getId(), securityGroupId, referered)) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败");
    }
    @UserLoginToken
    @ApiOperation("删除referer")
    @RequestMapping(value = "/deleteReferer", method = RequestMethod.POST)
    public CommonResult deleteReferer(Integer itemId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (securityGroupService.hasTheItem(itemId, userInfoDto.getId())) {
            securityGroupService.deleteRefererById(itemId);
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("不能删除别人的referer");
        }
    }
    @UserLoginToken
    @ApiOperation("根据groupId查找referer")
    @RequestMapping(value = "/findRefererByGroupId", method = RequestMethod.POST)
    public CommonResult getRefererByGroupId(Integer groupId,HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if(securityGroupService.hasTheSecurityGroup(userInfoDto.getId(),groupId))
        {
            List<Pair<Integer, String>> pairList = securityGroupService.findRefererByGroupId(groupId);
            return CommonResult.success(pairList,"获取成功");
        }
        return CommonResult.failed("只能获取自己的referer");
    }
    @UserLoginToken
    @ApiOperation("更新referer")
    @RequestMapping(value = "/updateReferer", method = RequestMethod.POST)
    public CommonResult updateReferer(Integer itemId,String referer,HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        UserInfoDto userInfoDto = JwtUtil.getUserInfo(token);
        if (securityGroupService.hasTheItem(itemId, userInfoDto.getId())) {
            securityGroupService.updateReferer(itemId,referer);
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("不能更新别人的referer");
        }
    }
}