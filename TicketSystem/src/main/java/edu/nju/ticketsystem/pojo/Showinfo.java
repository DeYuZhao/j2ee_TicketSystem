package edu.nju.ticketsystem.pojo;

public class Showinfo {
    private String name;
    private String date;
    private String price;
    private String address;
    private String seatInfo;
    private String soldSeat;
    private String checkSeat;

    public String getSoldSeat() {
        return soldSeat;
    }

    public void setSoldSeat(String soldSeat) {
        this.soldSeat = soldSeat;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckSeat() {
        return checkSeat;
    }

    public void setCheckSeat(String checkSeat) {
        this.checkSeat = checkSeat;
    }
}
