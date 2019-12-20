package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TUsersEntitys;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserDao extends JpaRepository<TUsersEntitys, Integer> {
    //登录
    @Cacheable(value = "userInfo_check", key = "#email+#password")
    TUsersEntitys findOneByEmailEqualsAndPasswordEquals(String email, String password);

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userInfo_email", key = "#entity.email"),
            @CacheEvict(value = "userInfo_id", key = "#entity.id"),
            @CacheEvict(value = "userInfo_check", key = "#entity.email+#entity.password"),
            @CacheEvict(value = "userInfo_all",allEntries = true)
    })
    <S extends TUsersEntitys> S save(S entity);

    @Override
    @Cacheable(value = "userInfo_all",key = "#root.method")
    List<TUsersEntitys> findAll();

    @Cacheable(value = "userInfo_email", key = "#email")
    Optional<TUsersEntitys> findByEmailEquals(String email);

    @Override
    @Cacheable(value = "userInfo_id", key = "#integer")
    Optional<TUsersEntitys> findById(Integer integer);
}
