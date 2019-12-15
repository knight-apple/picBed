package cn.knightapple.dataSource.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routeMap", schema = "picBed", catalog = "")
public class TRouteMapEntitys {
    private int id;
    private String realUrl;
    private String route;

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
    @Column(name = "realUrl", nullable = true, length = 100)
    public String getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }

    @Basic
    @Column(name = "route", nullable = true, length = 50)
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRouteMapEntitys that = (TRouteMapEntitys) o;
        return id == that.id &&
                Objects.equals(realUrl, that.realUrl) &&
                Objects.equals(route, that.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realUrl, route);
    }
}
