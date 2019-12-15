package cn.knightapple.dataSource.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos", schema = "picBed", catalog = "")
public class TPhotosEntitys {
    private int id;
    private String intro;
    private String title;
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
    @Column(name = "intro", nullable = true, length = 200)
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPhotosEntitys that = (TPhotosEntitys) o;
        return id == that.id &&
                Objects.equals(intro, that.intro) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, intro, title);
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
