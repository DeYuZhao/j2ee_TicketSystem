package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.LevelEntity;
import edu.nju.ticketsystem.model.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/1/26.
 */
@Repository
public interface PointRepository extends JpaRepository<PointEntity, Integer>{
    @Query("select p from PointEntity p where p.pid=:level")
    PointEntity getLevel(@Param("level")int level);
}
