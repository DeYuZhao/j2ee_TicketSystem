package edu.nju.ticketsystem.pojo;

import edu.nju.ticketsystem.model.VenueEntity;
import edu.nju.ticketsystem.model.WaitdealvenueEntity;
import edu.nju.ticketsystem.repository.WaitdealvenueRepository;

import java.util.List;

public class VenuePage {
    private int total;//总数据条数
    private List<WaitdealvenueEntity> waitdealvenueEntityList;
    private List<VenueEntity> venueEntityList;

    public VenuePage(){

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<WaitdealvenueEntity> getWaitdealvenueEntityList() {
        return waitdealvenueEntityList;
    }

    public void setWaitdealvenueEntityList(List<WaitdealvenueEntity> waitdealvenueEntityList) {
        this.waitdealvenueEntityList = waitdealvenueEntityList;
    }

    public List<VenueEntity> getVenueEntityList() {
        return venueEntityList;
    }

    public void setVenueEntityList(List<VenueEntity> venueEntityList) {
        this.venueEntityList = venueEntityList;
    }
}
