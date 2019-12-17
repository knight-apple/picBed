package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TRouteMapEntitys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteMapDao extends JpaRepository<TRouteMapEntitys, Integer> {
    @Query(value = "select realUrl from routeMap where route=?1", nativeQuery = true)
    public String getRealUrl(String route);

    @Query(value = "select count(*) from `securityGroup`" +
            "where groupId = (" +
            "select securityGroupId from `photos`" +
            "where photos.id = (" +
            "select distinct `images`.photoId from `routeMap`" +
            "join `images` on `images`.id=`routeMap`.imageId where `routeMap`.route= ?1 ))" +
            " and `securityGroup`.refererDomain= ?2"
            , nativeQuery = true)
    public Integer countRouteByReferer(String route, String referer);

    @Modifying
    @Query(value = " delete from `routeMap` " +
            " where `routeMap`.imageId in " +
            " ( select `images`.id from `images` " +
            " where photoId = ?1 ) "
            , nativeQuery = true)
    public void deleteAllByPhotoId(Integer photoId);

    public void deleteAllByImageIdEquals(Integer imageId);
    public void deleteByRouteEquals(String route);
}
