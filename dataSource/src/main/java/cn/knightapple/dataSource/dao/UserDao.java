package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TUsersEntitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserDao extends JpaRepository<TUsersEntitys, Integer> {
    //登录
    TUsersEntitys findOneByEmailEqualsAndPasswordEquals(String email, String password);
    Optional<TUsersEntitys> findByEmailEquals(String email);
}
