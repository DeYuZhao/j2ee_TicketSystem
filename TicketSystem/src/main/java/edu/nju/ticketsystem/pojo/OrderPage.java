package edu.nju.ticketsystem.pojo;

import edu.nju.ticketsystem.model.OrderinfoEntity;

import java.util.HashMap;
import java.util.List;

public class OrderPage {
    private int total;//总数据条数
    private List<OrderinfoEntity> orderEntityList;
    private HashMap<String,String> planDateMap;
    private HashMap<String,String> planAddressMap;

    public OrderPage(){

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderinfoEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderinfoEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public HashMap<String, String> getPlanDateMap() {
        return planDateMap;
    }

    public void setPlanDateMap(HashMap<String, String> planDateMap) {
        this.planDateMap = planDateMap;
    }

    public HashMap<String, String> getPlanAddressMap() {
        return planAddressMap;
    }

    public void setPlanAddressMap(HashMap<String, String> planAddressMap) {
        this.planAddressMap = planAddressMap;
    }
}
