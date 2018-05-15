package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "level", schema = "ticketsystem", catalog = "")
public class LevelEntity {
    private int lid;
    private double benifit;

    @Id
    @Column(name = "lid", nullable = false)
    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    @Basic
    @Column(name = "benifit", nullable = false, precision = 0)
    public double getBenifit() {
        return benifit;
    }

    public void setBenifit(double benifit) {
        this.benifit = benifit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelEntity that = (LevelEntity) o;
        return lid == that.lid &&
                Double.compare(that.benifit, benifit) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(lid, benifit);
    }
}
