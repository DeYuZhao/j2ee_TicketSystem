package edu.nju.ticketsystem.service;

import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.pojo.Plan;
import edu.nju.ticketsystem.pojo.Showinfo;

import java.util.List;

public interface DisplayService {
    public List<Plan> getInitialInfo();

    public Showinfo getShowInfo(String title);
}
