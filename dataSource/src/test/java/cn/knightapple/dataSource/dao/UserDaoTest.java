package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.Application;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findAll() {
        TUsersEntitys tUsersEntitys = new TUsersEntitys();
        tUsersEntitys.setUserName("111");
        tUsersEntitys.setPassword("222");
        tUsersEntitys.setEmail("222");
        tUsersEntitys.setType(0);
        userDao.findAll();
        tUsersEntitys = userDao.save(tUsersEntitys);
        userDao.findOneByEmailEqualsAndPasswordEquals(tUsersEntitys.getEmail(),tUsersEntitys.getPassword());
        userDao.delete(tUsersEntitys);
        userDao.findAll();
    }
}