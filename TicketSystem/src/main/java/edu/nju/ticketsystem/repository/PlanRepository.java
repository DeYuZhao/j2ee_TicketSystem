package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.model.WaitdealvenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {
    @Query("select p from PlanEntity p where p.name=:name ")
    PlanEntity findPlan(@Param("name")String name);

    @Query("select p from PlanEntity p where p.address=:address ")
    List<PlanEntity> findPlanByAddress(@Param("address")String address);

    @Query("select p from PlanEntity p")
    Page<PlanEntity> getPlanList(Pageable pageable);

    @Query("select p from PlanEntity p where p.date like ?1% and p.address=?2")
    PlanEntity getPlanEntitiesByDate(String currentDte,String address);

    @Query("select p from PlanEntity p where p.address=:address")
    Page<PlanEntity> getPlanList(@Param("address")String address, Pageable pageable);

    @Query("select count(p) from PlanEntity p where p.address=:address")
    int getPlanListNumber(@Param("address")String address);

    @Query("select p from PlanEntity p where p.date like ?1%")
    Page<PlanEntity> getSettlePlanList(@Param("date")String date, Pageable pageable);

    @Query("select count(p) from PlanEntity p where p.date like ?1%")
    int getTotalSettlePlan(@Param("date")String date);
}
