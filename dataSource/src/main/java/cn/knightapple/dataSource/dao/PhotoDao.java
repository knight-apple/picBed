package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TPhotosEntitys;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoDao extends JpaRepository<TPhotosEntitys,Integer> {
    List<TPhotosEntitys> findByUsersByUserIdEquals(TUsersEntitys tUsersEntitys);

}
