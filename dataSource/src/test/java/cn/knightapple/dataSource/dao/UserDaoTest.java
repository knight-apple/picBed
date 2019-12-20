package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.Application;
import cn.knightapple.dataSource.entity.TUsersEntitys;
import com.alibaba.fastjson.JSON;
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
        System.out.println(JSON.toJSONString(userDao.findAll()));
        System.out.println(JSON.toJSONString(userDao.findAll()));

        TUsersEntitys tUsersEntitys = userDao.findById(20).get();
        tUsersEntitys = userDao.save(tUsersEntitys);
        System.out.println(JSON.toJSONString(tUsersEntitys));
        System.out.println(JSON.toJSONString(userDao.findById(tUsersEntitys.getId())));
        System.out.println(JSON.toJSONString(userDao.findById(tUsersEntitys.getId())));

        System.out.println(JSON.toJSONString(userDao.findOneByEmailEqualsAndPasswordEquals(tUsersEntitys.getEmail(),tUsersEntitys.getPassword())));
        System.out.println(JSON.toJSONString(userDao.findOneByEmailEqualsAndPasswordEquals(tUsersEntitys.getEmail(),tUsersEntitys.getPassword())));

        System.out.println(JSON.toJSONString(userDao.findByEmailEquals(tUsersEntitys.getEmail())));
        System.out.println(JSON.toJSONString(userDao.findByEmailEquals(tUsersEntitys.getEmail())));
        tUsersEntitys.setPassword("123456789012345678902");
        System.out.println(JSON.toJSONString(userDao.save(tUsersEntitys)));

        System.out.println(JSON.toJSONString(userDao.findAll()));
        System.out.println(JSON.toJSONString(userDao.findAll()));

        userDao.findById(tUsersEntitys.getId());
        userDao.findById(tUsersEntitys.getId());

        userDao.findOneByEmailEqualsAndPasswordEquals(tUsersEntitys.getEmail(),tUsersEntitys.getPassword());
        userDao.findOneByEmailEqualsAndPasswordEquals(tUsersEntitys.getEmail(),tUsersEntitys.getPassword());

        userDao.findByEmailEquals(tUsersEntitys.getEmail());
        userDao.findByEmailEquals(tUsersEntitys.getEmail());
    }
}