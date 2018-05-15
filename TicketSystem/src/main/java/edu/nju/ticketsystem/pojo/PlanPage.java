package edu.nju.ticketsystem.pojo;

import edu.nju.ticketsystem.model.PlanEntity;

import java.util.List;

public class PlanPage {
    private int total;
    private List<PlanEntity> planEntities;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PlanEntity> getPlanEntities() {
        return planEntities;
    }

    public void setPlanEntities(List<PlanEntity> planEntities) {
        this.planEntities = planEntities;
    }

}
