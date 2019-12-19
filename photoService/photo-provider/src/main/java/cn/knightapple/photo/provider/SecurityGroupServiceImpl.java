package cn.knightapple.photo.provider;

import cn.knightapple.dataSource.dao.RouteMapDao;
import cn.knightapple.dataSource.dao.SecutityGroupDao;
import cn.knightapple.dataSource.entity.TSecurityGroupEntitys;
import cn.knightapple.photo.provider.utils.SecurityGroupFactory;
import cn.knightapple.photo.service.SecurityGroupService;
import javafx.util.Pair;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean addSecurityGroup(Integer userId, Integer groupId, List<String> refererList) {
        SecurityGroupFactory securityGroupFactory = new SecurityGroupFactory(groupId, userId, userId + ":" + groupId);
        refererList.forEach(e -> {
            TSecurityGroupEntitys entitys = securityGroupFactory.newInstance(e);
            secutityGroupDao.save(entitys);
        });
        return true;
    }

    @Override
    public boolean hasTheSecurityGroup(Integer userId, Integer groupId) {
        List<Integer> userList = secutityGroupDao.findUserByGroupId(groupId);
        if (userList.size() != 1) {
            return false;
        } else {
            if (!userList.get(0).equals(userId)) {
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean updateReferer(Integer itemId, String newReferer) {
        Optional<TSecurityGroupEntitys> entitysOptional = secutityGroupDao.findById(itemId);
        if(entitysOptional.equals(Optional.empty())){
            return false;
        }
        TSecurityGroupEntitys entitys = entitysOptional.get();
        entitys.setRefererDomain(newReferer);
        secutityGroupDao.save(entitys);
        return true;
    }

    @Override
    public boolean deleteRefererById(Integer itemId) {
        secutityGroupDao.deleteById(itemId);
        return true;
    }

    @Override
    public List<Pair<Integer, String>> findRefererByGroupId(Integer gourpId) {
        List<TSecurityGroupEntitys> tSecurityGroupEntitys = secutityGroupDao.findAllByGroupIdEquals(gourpId);
        List<Pair<Integer, String>> refererList = new ArrayList<Pair<Integer, String>>();
        tSecurityGroupEntitys.forEach(entity -> {
            refererList.add(new Pair<>(entity.getItemId(), entity.getRefererDomain()));
        });
        return refererList;
    }

    @Override
    public boolean hasTheItem(Integer itemId, Integer userId)
    {
        Optional<TSecurityGroupEntitys> entitysOptional = secutityGroupDao.findById(itemId);
        if(entitysOptional.equals(Optional.empty()))
        {
            return false;
        }else{
            TSecurityGroupEntitys entitys = entitysOptional.get();
            return entitys.getUsersByUserId().getId().equals(userId);
        }
    }
}
