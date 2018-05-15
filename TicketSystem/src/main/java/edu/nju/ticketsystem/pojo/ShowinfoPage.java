package edu.nju.ticketsystem.pojo;

import java.util.List;

public class ShowinfoPage {
    private int total;
    private List<Showinfo> showinfoList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Showinfo> getShowinfoList() {
        return showinfoList;
    }

    public void setShowinfoList(List<Showinfo> showinfoList) {
        this.showinfoList = showinfoList;
    }
}
