package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.VenueEntity;
import edu.nju.ticketsystem.model.WaitdealvenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/1/26.
 */
@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Integer>{
    @Query("select v from VenueEntity v where v.email=:email and v.logincode=:logincode ")
    VenueEntity getVenueInfo(@Param("email")String email, @Param("logincode")String logincode);

    @Modifying
    @Transactional
    @Query("update VenueEntity w set w.partitionnum=:partitionnum,w.rows=:rows,w.columns=:columns where w.address=:address")
    int updateChangeVenueInfo(@Param("partitionnum")int partitionnum,@Param("rows")int rows,
                              @Param("columns")int columns,@Param("address")String address);

    @Query("select v from VenueEntity v where v.address=:address ")
    VenueEntity checkVenueAddress(@Param("address")String address);

    @Query("select v from VenueEntity v")
    Page<VenueEntity> getVenuePage(Pageable pageable);

    @Query("select count(v) from VenueEntity v")
    int getTotalVenue();

}
