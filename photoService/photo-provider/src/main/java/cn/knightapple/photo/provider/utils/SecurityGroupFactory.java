package cn.knightapple.photo.provider.utils;

import cn.knightapple.dataSource.entity.TSecurityGroupEntitys;
import cn.knightapple.dataSource.entity.TUsersEntitys;

public class SecurityGroupFactory {
    private Integer groupId;
    private Integer userId;
    private String groupName;

    public SecurityGroupFactory(Integer groupId, Integer userId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userId = userId;
    }

    public TSecurityGroupEntitys newInstance(String referer) {
        TSecurityGroupEntitys tSecurityGroupEntitys = new TSecurityGroupEntitys();
        TUsersEntitys tUsersEntitys = new TUsersEntitys();
        tUsersEntitys.setId(userId);
        tSecurityGroupEntitys.setUsersByUserId(tUsersEntitys);
        tSecurityGroupEntitys.setGroupId(groupId);
        tSecurityGroupEntitys.setGroupName(groupName);
        tSecurityGroupEntitys.setRefererDomain(referer);
        return tSecurityGroupEntitys;
    }
}
