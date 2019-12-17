package cn.knightapple.photo.provider;

import cn.knightapple.dataSource.dao.RouteMapDao;
import cn.knightapple.dataSource.dao.SecutityGroupDao;
import cn.knightapple.photo.service.SecurityGroupService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class SecurityGroupServiceImpl implements SecurityGroupService {
    @Autowired
    private SecutityGroupDao secutityGroupDao;
    @Autowired
    private RouteMapDao routeMapDao;

    @Override
    public boolean isAllowed(String host, String route) {
        //TODO 要做路由映射管理,但是效率特别低
        // 之后再想办法,现在让所有请求都通过
        int count = routeMapDao.countRouteByReferer(route, host);
        return count > 0;
    }
}
