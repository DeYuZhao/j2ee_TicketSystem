package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderinfo", schema = "ticketsystem", catalog = "")
public class OrderinfoEntity {
    private int id;
    private String email;
    private int totalprice;
    private String name;
    private String date;
    private String orderstate;
    private String seatinfo;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "totalprice", nullable = false)
    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = false, length = 45)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "orderstate", nullable = false, length = 45)
    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
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
        OrderinfoEntity that = (OrderinfoEntity) o;
        return id == that.id &&
                totalprice == that.totalprice &&
                Objects.equals(email, that.email) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(orderstate, that.orderstate) &&
                Objects.equals(seatinfo, that.seatinfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, totalprice, name, date, orderstate, seatinfo);
    }
}
