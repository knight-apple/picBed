package cn.knightapple.restfulApi.consumer.config;

import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.photo.service.SecurityGroupService;
import cn.knightapple.restfulApi.consumer.annotation.ImageReferered;
import cn.knightapple.restfulApi.consumer.utils.CookieUtils;
import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
import cn.knightapple.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URL;

public class ImageRefererInterceptor implements HandlerInterceptor {
    @Reference
    UserService userService;
    @Reference
    SecurityGroupService securityGroupService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");// 从 http 请求头中取出 token
        String referer = request.getHeader("referer");
        if(token==null)
        {
            token = CookieUtils.getCookie("Authorization",request);
        }
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(ImageReferered.class)) {
            //是否有token,如果有则进一步判断,没有就进行referer判断
            if (token!=null&&token != "") {
                // 获取 token 中的 user id
                UserInfoDto userInfoDtoForToken;
                try {
                    userInfoDtoForToken = JwtUtil.getUserInfo(token);
                    if (UserInfoDto.isEmpty(userInfoDtoForToken)) {
                        throw new RuntimeException("token错误,请重新登录");
                    }
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                UserInfoDto user = userService.findUserById(userInfoDtoForToken.getId());
                if (UserInfoDto.isEmpty(user)) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                String password = userService.getPasswordById(user.getId());
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            } else {
                if(referer!=null) {
                    URL refererUrl = new URL(referer);
                    String host = refererUrl.getHost() + ":" + refererUrl.getPort();
                    String[] pathInfo = request.getRequestURI().split("/");
                    String route = pathInfo[pathInfo.length - 1];
                    if (securityGroupService.isAllowed(host, route)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }
}
