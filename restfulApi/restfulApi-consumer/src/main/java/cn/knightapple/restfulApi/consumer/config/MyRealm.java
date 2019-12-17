//package cn.knightapple.restfulApi.consumer.config;
//
//import cn.knightapple.dataSource.dao.UserDao;
//import cn.knightapple.dataSource.entity.TUsersEntitys;
//import cn.knightapple.dto.UserInfoDto;
//import cn.knightapple.restfulApi.consumer.utils.JwtToken;
//import cn.knightapple.restfulApi.consumer.utils.JwtUtil;
//import cn.knightapple.service.UserService;
//import org.apache.dubbo.config.annotation.Reference;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
///**
// * @author Mr.Li
// * @create 2018-07-12 15:23
// * @desc
// **/
//@Component
//public class MyRealm extends AuthorizingRealm {
//    @Autowired
//    UserDao userDao;
//
//    @Reference
//    private UserService userService;
//
//    /**
//     * 必须重写此方法，不然Shiro会报错
//     */
//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JwtToken;
//    }
//
//    /**
//     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//        UserInfoDto userInfoDto = JwtUtil.getUserInfo(principals.toString());
//        UserInfoDto user = userService.findUserById(userInfoDto.getId());
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRole(String.valueOf(user.getType()));
////        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
////        simpleAuthorizationInfo.addStringPermissions(permission);
//        return simpleAuthorizationInfo;
//    }
//
//    /**
//     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
//        String token = (String) auth.getCredentials();
//        UserInfoDto userInfo = JwtUtil.getUserInfo(token);
//
//        if (UserInfoDto.isEmpty(userInfo)) {
//            throw new AuthenticationException("token无效");
//        }
//
//        Optional<TUsersEntitys> userBean = userDao.findById(userInfo.getId());
//        if (userBean.equals(Optional.empty())) {
//            throw new AuthenticationException("用户不存在!");
//        }
//
//        if (!JwtUtil.verify(token, userInfo.getEmail(), userBean.get().getPassword())) {
//            throw new AuthenticationException("用户名或密码错误");
//        }
//
//        return new SimpleAuthenticationInfo(token, token, "my_realm");
//    }
//}