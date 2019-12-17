package cn.knightapple.dto;

import cn.knightapple.dataSource.entity.TUsersEntitys;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
public class UserInfoDto implements Serializable {
    private static final UserInfoDto Empty = new UserInfoDto();
    private Integer id;
    private String email;
    private String userName;
    private Integer type;

    public static boolean isEmpty(UserInfoDto userInfoDto) {
        if (userInfoDto.equals(Empty)) {
            return true;
        }
        return false;
    }

    public static UserInfoDto empty() {
        return Empty;
    }

    public static UserInfoDto parseDto(TUsersEntitys entitys) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.email = entitys.getEmail();
        userInfoDto.id = entitys.getId();
        userInfoDto.userName = entitys.getUserName();
        userInfoDto.type = entitys.getType();
        return userInfoDto;
    }

    public boolean isFull() {
        if (email == null
                || userName == null
                || type == null) {
            return false;
        } else {
            return true;
        }
    }

    public TUsersEntitys toEntity(TUsersEntitys entitys) {
        if (this.type != null) {
            entitys.setType(type);
        }
//        if (this.id != null) {
//            entitys.setId(id);
//        }
        if (this.userName != null) {
            entitys.setUserName(userName);
        }
        if (this.email != null) {
            entitys.setEmail(email);
        }
        return entitys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoDto that = (UserInfoDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userName, that.userName);
    }

}
