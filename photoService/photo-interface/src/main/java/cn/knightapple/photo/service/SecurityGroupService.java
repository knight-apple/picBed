package cn.knightapple.photo.service;

public interface SecurityGroupService {
    public boolean isAllowed(String host,String route);
}
