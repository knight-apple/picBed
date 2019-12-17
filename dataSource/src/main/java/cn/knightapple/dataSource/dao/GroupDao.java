package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TGroupViewEntitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupDao extends JpaRepository<TGroupViewEntitys, Integer> {
    //根据用户查找所有的安全组id和组名
    List<TGroupViewEntitys> findAllByUserIdEquals(Integer userId);

    //获得目前最大的组ID
    @Query(value = "select max(groupId) from groupView",nativeQuery = true)
    Integer maxGroupId();
}
