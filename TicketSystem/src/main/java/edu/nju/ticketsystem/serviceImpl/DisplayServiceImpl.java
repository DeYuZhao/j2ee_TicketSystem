package edu.nju.ticketsystem.serviceImpl;

import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.pojo.Plan;
import edu.nju.ticketsystem.pojo.Showinfo;
import edu.nju.ticketsystem.repository.PlanRepository;
import edu.nju.ticketsystem.service.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisplayServiceImpl implements DisplayService {
    @Autowired
    private PlanRepository planRepository;
    /**
     * 获得首页的演出信息
     * @return
     */
    @Override
    public List<Plan> getInitialInfo() {
        PageRequest pageRequest = new PageRequest(0,6, Sort.Direction.DESC,"id");
        Page<PlanEntity> page = planRepository.getPlanList(pageRequest);
        List<Plan> planList = new ArrayList<Plan>();
        for (int i=0;i<6;i++){
            Plan plan = new Plan();
            plan.setName(page.getContent().get(i).getName());
            plan.setPrice(page.getContent().get(i).getPrice());
            planList.add(plan);
        }
        return planList;
    }

    /**
     * 获取演出信息
     * @param title 演出名称
     * @return
     */
    @Override
    public Showinfo getShowInfo(String title) {
        PlanEntity planEntity = planRepository.findPlan(title);
        Showinfo showinfo = new Showinfo();
        showinfo.setName(planEntity.getName());
        showinfo.setDate(planEntity.getDate());
        showinfo.setPrice(planEntity.getPrice());
        showinfo.setAddress(planEntity.getAddress());
        return showinfo;
    }
}
