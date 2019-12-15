package cn.knightapple.dataSource.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "groupView", schema = "picBed", catalog = "")
public class TGroupViewEntitys {
    private Integer groupId;
    private Integer userId;
    private String groupName;

    @Id
    @Column(name = "groupId", nullable = false)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "userId", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "groupName", nullable = true, length = 45)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TGroupViewEntitys that = (TGroupViewEntitys) o;
        return Objects.equals(groupId, that.groupId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId, groupName);
    }

}
