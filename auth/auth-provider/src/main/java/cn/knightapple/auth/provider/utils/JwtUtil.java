package cn.knightapple.auth.provider.utils;

import cn.knightapple.dto.UserInfoDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author Mr.Li
 * @create 2018-07-12 14:23
 * @desc JWT工具类
 **/
public class JwtUtil {

    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param email 用户登录邮箱
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String email, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("email", email)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的用户信息无需secret解密也能获得
     * @Param token 传入的token
     * @return token中包含的用户信息
     */
    public static UserInfoDto getUserInfo(String token) {
        try {
            UserInfoDto userInfoDto = new UserInfoDto();
            DecodedJWT jwt = JWT.decode(token);
            userInfoDto.setId(jwt.getClaim("userId").asInt());
            userInfoDto.setEmail(jwt.getClaim("email").asString());
            userInfoDto.setUserName(jwt.getClaim("userName").asString());
            userInfoDto.setType(jwt.getClaim("type").asInt());
            return userInfoDto;
        } catch (JWTDecodeException e) {
            return UserInfoDto.empty();
        }
    }

    /**
     * 生成签名,5min后过期
     * 如果传入的用户信息是错误的就返回空字符串
     *
     * @param userInfoDto 登录时检查出来的用户信息
     * @param secret      用户的密码
     * @return 加密的token
     */
    public static String sign(UserInfoDto userInfoDto, String secret) {
        if (UserInfoDto.isEmpty(userInfoDto)) {
            return "";
        }
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带用户信息
        return JWT.create()
                .withClaim("email", userInfoDto.getEmail())
                .withClaim("userId", userInfoDto.getId())
                .withClaim("userType", userInfoDto.getUserName())
                .withClaim("userName", userInfoDto.getUserName())
                .withExpiresAt(date)
                .sign(algorithm);
    }
}