package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "point", schema = "ticketsystem", catalog = "")
public class PointEntity {
    private int pid;
    private int point;

    @Id
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "point", nullable = false)
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointEntity that = (PointEntity) o;
        return pid == that.pid &&
                point == that.point;
    }

    @Override
    public int hashCode() {

        return Objects.hash(pid, point);
    }
}
