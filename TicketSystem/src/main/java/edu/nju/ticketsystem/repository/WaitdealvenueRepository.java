package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.WaitdealvenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WaitdealvenueRepository extends JpaRepository<WaitdealvenueEntity,Integer> {
    @Query("select w from WaitdealvenueEntity w where w.state=:state ")
    Page<WaitdealvenueEntity> getVenueList(@Param("state")String state, Pageable pageable);

    @Query("select count(w) from WaitdealvenueEntity w where w.state=:state ")
    int getTotalNumOfVenue(@Param("state")String state);

    @Query("select w from WaitdealvenueEntity w where w.state=:state and w.address=:address ")
    WaitdealvenueEntity getVenueInfo(@Param("state")String state, @Param("address")String address);

    @Query("select w from WaitdealvenueEntity w where w.address=:address ")
    WaitdealvenueEntity getVenueInfo(@Param("address")String address);

    @Modifying
    @Transactional
    @Query("update WaitdealvenueEntity w set w.state=:state where w.address=:address and w.state=:nowstate")
    int changeVenueState(@Param("state")String state,@Param("address")String address,@Param("nowstate")String nowState);


}
