package cn.knightapple.service;

import cn.knightapple.dto.UserInfoDto;

public interface UserService {
    /**
     * 使用email和password来查找用户,
     * 如果用户存在,则返回该用户的信息,
     * 如果该用户不存在则返回UserInfoDto的empty()
     * 调用方需要使用isEmpty来检查是否成功
     *
     * @param email    用户的邮箱,相当于账号
     * @param password 用户的密码
     * @return UserInfoDto类型, 是查找出来的结果
     */
    public UserInfoDto findUser(String email, String password);

    /**
     * 添加用户信息,如果UserInfoDto中有id信息,将会被屏蔽掉
     * 添加成功返回添加成功的具体结果,会将id更新为添加成功的值
     *
     * @param userInfoDto 用户信息
     * @return 返回添加成功后的用户信息
     */
    public UserInfoDto addUser(UserInfoDto userInfoDto);

    public UserInfoDto findUserById(Integer userId);
}
