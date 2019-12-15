package cn.knightapple.dataSource.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "images", schema = "picBed", catalog = "")
public class TImagesEntitys {
    private int id;
    private String route;
    private String zipRoute;
    private String title;
    private String intro;
    private Integer type;
    private Timestamp createTime;
    private TPhotosEntitys photosByPhotoId;
    private TUsersEntitys usersByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "route", nullable = false, length = 50)
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Basic
    @Column(name = "zipRoute", nullable = true, length = 50)
    public String getZipRoute() {
        return zipRoute;
    }

    public void setZipRoute(String zipRoute) {
        this.zipRoute = zipRoute;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "intro", nullable = true, length = 100)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TImagesEntitys that = (TImagesEntitys) o;
        return id == that.id &&
                Objects.equals(route, that.route) &&
                Objects.equals(zipRoute, that.zipRoute) &&
                Objects.equals(title, that.title) &&
                Objects.equals(intro, that.intro) &&
                Objects.equals(type, that.type) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, route, zipRoute, title, intro, type, createTime);
    }

    @ManyToOne
    @JoinColumn(name = "photoId", referencedColumnName = "id", nullable = false)
    public TPhotosEntitys getPhotosByPhotoId() {
        return photosByPhotoId;
    }

    public void setPhotosByPhotoId(TPhotosEntitys photosByPhotoId) {
        this.photosByPhotoId = photosByPhotoId;
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
