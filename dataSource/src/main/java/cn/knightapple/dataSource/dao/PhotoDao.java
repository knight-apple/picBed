package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TPhotosEntitys;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoDao extends JpaRepository<TPhotosEntitys,Integer> {
    List<TPhotosEntitys> findByUsersByUserIdEquals(TUsersEntitys tUsersEntitys);

    void deleteByIdEquals(Integer integer);
}
