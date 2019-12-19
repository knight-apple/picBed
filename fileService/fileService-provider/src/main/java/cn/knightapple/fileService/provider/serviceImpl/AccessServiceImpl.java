package cn.knightapple.fileService.provider.serviceImpl;

import cn.knightapple.dataSource.dao.RouteMapDao;
import cn.knightapple.fileService.provider.utils.MessageProperties;
import cn.knightapple.fileService.service.AccessService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccessServiceImpl implements AccessService {
    private static final Logger LOOGER = LoggerFactory.getLogger(AccessServiceImpl.class);
    @Autowired
    private RouteMapDao routeMapDao;

    @Autowired
    private MessageProperties config;

    @Override
    public String getImageUrl(String route) {
        String realUrl = routeMapDao.getRealUrl(route);
        realUrl = config.getAccessHost() + realUrl;
        return realUrl;
    }
}
