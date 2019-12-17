package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TSecurityGroupEntitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SecutityGroupDao extends JpaRepository<TSecurityGroupEntitys, Integer> {
    //根据图片id查找存在的reference的数量,计划做缓存
    @Query(value = "select count(*) " +
            "from securityGroup " +
            "where groupId in " +
            "(select securityGroupId " +
            "from images " +
            "join photos p " +
            "on images.photoId = p.id " +
            "where images.id=?2) " +
            "and refererDomain=?1", nativeQuery = true)
    public Integer countReferenceByImageId(String reference, Integer imageId);


    public void deleteByGroupIdEquals(Integer groupId);
}
