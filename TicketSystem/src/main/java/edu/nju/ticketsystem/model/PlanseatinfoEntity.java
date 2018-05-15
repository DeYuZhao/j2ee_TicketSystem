package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "planseatinfo", schema = "ticketsystem", catalog = "")
public class PlanseatinfoEntity {
    private int id;
    private String planname;
    private String seatinfo;
    private String soldseat;
    private String checkseat;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "planname", nullable = false, length = 45)
    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    @Basic
    @Column(name = "seatinfo", nullable = false, length = 1000)
    public String getSeatinfo() {
        return seatinfo;
    }

    public void setSeatinfo(String seatinfo) {
        this.seatinfo = seatinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanseatinfoEntity that = (PlanseatinfoEntity) o;
        return id == that.id &&
                Objects.equals(planname, that.planname) &&
                Objects.equals(seatinfo, that.seatinfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, planname, seatinfo);
    }

    @Basic
    @Column(name = "soldseat", nullable = false, length = 4000)
    public String getSoldseat() {
        return soldseat;
    }

    public void setSoldseat(String soldseat) {
        this.soldseat = soldseat;
    }

    @Basic
    @Column(name = "checkseat", nullable = false, length = 4000)
    public String getCheckseat() {
        return checkseat;
    }

    public void setCheckseat(String checkseat) {
        this.checkseat = checkseat;
    }
}
