package cn.knightapple.service.provider;

import cn.knightapple.dataSource.dao.UserDao;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import cn.knightapple.dto.UserInfoDto;
import cn.knightapple.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 更新用户信息,不包括密码
     *
     * @param userInfoDto 用户的信息dto
     * @return 更新后的结果
     */
    @Override
    public UserInfoDto updateUser(UserInfoDto userInfoDto) {
        Optional<TUsersEntitys> tUsersEntitysOptional = userDao.findById(userInfoDto.getId());
        if (tUsersEntitysOptional.equals(Optional.empty())) {
            return UserInfoDto.empty();
        }
        TUsersEntitys tUsersEntitys = tUsersEntitysOptional.get();
        tUsersEntitys = userInfoDto.toEntity(tUsersEntitys);
        return UserInfoDto.parseDto(tUsersEntitys);
    }

    @Override
    public boolean updatePassword(Integer userId, String newPassword) {
        Optional<TUsersEntitys> tUsersEntitysOptional = userDao.findById(userId);
        if (tUsersEntitysOptional.equals(Optional.empty())) {
            return false;
        }
        TUsersEntitys tUsersEntitys = tUsersEntitysOptional.get();
        tUsersEntitys.setPassword(newPassword);
        userDao.save(tUsersEntitys);
        return true;
    }

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
    @Override
    public UserInfoDto findUser(String email, String password) {
        if ((email != null || email != "") && (password != null || password != "")) {
            TUsersEntitys usersEntitys = userDao.findOneByEmailEqualsAndPasswordEquals(email, password);
            return UserInfoDto.parseDto(usersEntitys);
        } else {
            return UserInfoDto.empty();
        }
    }

    /**
     * 添加用户信息,如果UserInfoDto中有id信息,将会被屏蔽掉
     * 添加成功返回添加成功的具体结果,会将id更新为添加成功的值
     *
     * @param userInfoDto 用户信息
     * @return 返回添加成功后的用户信息
     */
    @Override
    public UserInfoDto addUser(UserInfoDto userInfoDto, String password) {
        if (userInfoDto != null && userInfoDto.isFull()) {
            TUsersEntitys usersEntitys = new TUsersEntitys();
            usersEntitys = userInfoDto.toEntity(usersEntitys);
            usersEntitys.setPassword(password);
            Optional<TUsersEntitys> tUsersEntitysOptional = userDao.findByEmailEquals(userInfoDto.getEmail());
            if (!tUsersEntitysOptional.equals(Optional.empty())) {
                return UserInfoDto.empty();
            }
            usersEntitys = userDao.save(usersEntitys);
            userInfoDto = UserInfoDto.parseDto(usersEntitys);
            return userInfoDto;
        } else {
            return UserInfoDto.empty();
        }
    }

    @Override
    public UserInfoDto findUserById(Integer userId) {
        Optional<TUsersEntitys> userInfoDtoOptional = userDao.findById(userId);
        if (!userInfoDtoOptional.equals(Optional.empty())) {
            return UserInfoDto.parseDto(userInfoDtoOptional.get());
        }
        return UserInfoDto.empty();
    }

    @Override
    public String getPasswordById(Integer userId){
        Optional<TUsersEntitys> userInfoDtoOptional = userDao.findById(userId);
        if (!userInfoDtoOptional.equals(Optional.empty())) {
            return userInfoDtoOptional.get().getPassword();
        }
        return null;
    }
}
