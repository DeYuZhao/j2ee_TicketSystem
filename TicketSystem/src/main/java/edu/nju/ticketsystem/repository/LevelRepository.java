package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/1/26.
 */
@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Integer>{
    @Query("select l from LevelEntity l where l.lid=:level")
    LevelEntity getLevel(@Param("level")int level);
}
