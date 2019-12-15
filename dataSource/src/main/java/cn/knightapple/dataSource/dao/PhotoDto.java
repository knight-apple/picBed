package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.entity.TPhotosEntitys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDto extends JpaRepository<TPhotosEntitys,Integer> {
}
