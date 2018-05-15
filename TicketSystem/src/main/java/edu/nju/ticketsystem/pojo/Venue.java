package edu.nju.ticketsystem.pojo;

public class Venue {
    private String email;
    private String password;
    private String address;
    private int partitionnum;
    private int rows;
    private int columns;

    public Venue(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getPartitionnum() {
        return partitionnum;
    }

    public void setPartitionnum(int partitionnum) {
        this.partitionnum = partitionnum;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
