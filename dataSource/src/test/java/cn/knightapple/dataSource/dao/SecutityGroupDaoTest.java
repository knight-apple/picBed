package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.Application;
import org.hibernate.annotations.ColumnTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class SecutityGroupDaoTest {

    @Autowired
    SecutityGroupDao secutityGroupDao;
    @Autowired
    GroupDao groupDao;

    @Test
    public void findAllByUsersByUserIdEquals() {
        List list = groupDao.findAllByUserIdEquals(1);
        System.out.println(list);
    }
}