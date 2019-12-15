package cn.knightapple.dataSource.dao;

import cn.knightapple.dataSource.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RouteMapDaoTest {

    @Autowired
    RouteMapDao routeMapDao;

    @Test
    public void getRealUrl() {
        Integer count = routeMapDao.countRouteByReferer("1","1");
        String result = routeMapDao.getRealUrl("1");
        result = routeMapDao.getRealUrl("2");
    }
}