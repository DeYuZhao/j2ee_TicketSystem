package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.model.PlanseatinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlanseatinfoRepository extends JpaRepository<PlanseatinfoEntity,Integer> {
    @Query("select p from PlanseatinfoEntity p where p.planname=:name ")
    PlanseatinfoEntity getPlanseatinfoEntity(@Param("name")String name);

    @Modifying
    @Transactional
    @Query("update PlanseatinfoEntity p set p.soldseat=:soldseat where p.planname=:planname")
    int updataSoldSeat(@Param("soldseat")String soldseat,@Param("planname")String planname);

    @Modifying
    @Transactional
    @Query("update PlanseatinfoEntity p set p.checkseat=:checkSeat where p.planname=:planname")
    int updataCheckSeat(@Param("checkSeat")String checkSeat,@Param("planname")String planname);
}
