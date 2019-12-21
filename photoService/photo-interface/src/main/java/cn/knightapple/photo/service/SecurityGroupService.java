package cn.knightapple.photo.service;

import javafx.util.Pair;

import java.util.List;

public interface SecurityGroupService {
    public boolean isAllowed(String host,String route);

    boolean addSecurityGroup(Integer userId, Integer groupId, List<String> refererList);



    boolean hasTheSecurityGroup(Integer userId, Integer groupId);

    boolean updateReferer(Integer itemId, String newReferer);

    boolean deleteRefererById(Integer itemId);

    List<Pair<Integer, String>> findRefererByGroupId(Integer gourpId);

    List<Pair<Integer, String>> findRefererByUserId(Integer userId);

    boolean hasTheItem(Integer itemId, Integer userId);
}
