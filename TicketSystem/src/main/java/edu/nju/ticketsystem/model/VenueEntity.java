package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "venue", schema = "ticketsystem", catalog = "")
public class VenueEntity {
    private int id;
    private String address;
    private String logincode;
    private String password;
    private int partitionnum;
    private int rows;
    private int columns;
    private String email;

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
    @Column(name = "logincode", nullable = false, length = 45)
    public String getLogincode() {
        return logincode;
    }

    public void setLogincode(String logincode) {
        this.logincode = logincode;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "partitionnum", nullable = false)
    public int getPartitionnum() {
        return partitionnum;
    }

    public void setPartitionnum(int partitionnum) {
        this.partitionnum = partitionnum;
    }

    @Basic
    @Column(name = "rows", nullable = false)
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Basic
    @Column(name = "columns", nullable = false)
    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenueEntity that = (VenueEntity) o;
        return id == that.id &&
                partitionnum == that.partitionnum &&
                rows == that.rows &&
                columns == that.columns &&
                Objects.equals(address, that.address) &&
                Objects.equals(logincode, that.logincode) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, address, logincode, password, partitionnum, rows, columns);
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
