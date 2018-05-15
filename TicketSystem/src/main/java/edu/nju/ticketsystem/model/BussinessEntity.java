package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bussiness", schema = "ticketsystem", catalog = "")
public class BussinessEntity {
    private int id;
    private String address;
    private String planname;
    private int nowprofit;
    private String settleState;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    @Column(name = "nowprofit", nullable = false)
    public int getNowprofit() {
        return nowprofit;
    }

    public void setNowprofit(int nowprofit) {
        this.nowprofit = nowprofit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BussinessEntity that = (BussinessEntity) o;
        return id == that.id &&
                nowprofit == that.nowprofit &&
                Objects.equals(address, that.address) &&
                Objects.equals(planname, that.planname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, address, planname, nowprofit);
    }

    @Basic
    @Column(name = "settleState", nullable = false, length = 45)
    public String getSettleState() {
        return settleState;
    }

    public void setSettleState(String settleState) {
        this.settleState = settleState;
    }
}
