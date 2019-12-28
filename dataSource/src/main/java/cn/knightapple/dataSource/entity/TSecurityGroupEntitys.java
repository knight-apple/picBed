package cn.knightapple.dataSource.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "securityGroup", schema = "picBed", catalog = "")
public class TSecurityGroupEntitys implements Serializable {
    private int itemId;
    private Integer groupId;
    private TUsersEntitys usersByUserId;
    private String refererDomain;
    private String groupName;

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "groupId", nullable = false)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "refererDomain", nullable = false, length = 200)
    public String getRefererDomain() {
        return refererDomain;
    }

    public void setRefererDomain(String refererDomain) {
        this.refererDomain = refererDomain;
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
        TSecurityGroupEntitys that = (TSecurityGroupEntitys) o;
        return itemId == that.itemId &&
                groupId.equals(that.groupId) &&
                Objects.equals(refererDomain, that.refererDomain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, groupId, refererDomain);
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public TUsersEntitys getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(TUsersEntitys usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
