package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.BussinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BussinessRepository extends JpaRepository<BussinessEntity,Integer> {
    @Query("select count(p) from BussinessEntity p where p.planname=:name")
    BussinessEntity getBussinessEntityByPlanname(@Param("name")String name);

    @Modifying
    @Transactional
    @Query("update BussinessEntity m set m.nowprofit=:profit,m.settleState=:settleState where m.planname=:name")
    int updateBusinessProfit(@Param("name")String name, @Param("profit")int profit,@Param("settleState")String settleState);
}
