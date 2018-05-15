package edu.nju.ticketsystem.pojo;

import java.util.List;

public class MemberStatistics {
    private List<String> reservedNumList;
    private List<String> returnNumList;
    private List<String> consumptionList;

    public List<String> getReservedNumList() {
        return reservedNumList;
    }

    public void setReservedNumList(List<String> reservedNumList) {
        this.reservedNumList = reservedNumList;
    }

    public List<String> getReturnNumList() {
        return returnNumList;
    }

    public void setReturnNumList(List<String> returnNumList) {
        this.returnNumList = returnNumList;
    }

    public List<String> getConsumptionList() {
        return consumptionList;
    }

    public void setConsumptionList(List<String> consumptionList) {
        this.consumptionList = consumptionList;
    }
}
