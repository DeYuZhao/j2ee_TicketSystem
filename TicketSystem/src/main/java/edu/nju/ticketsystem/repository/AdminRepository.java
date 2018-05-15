package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.AdminEntity;
import edu.nju.ticketsystem.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity,Integer> {
    @Query("select a from AdminEntity a where a.adminname=:admin")
    AdminEntity findAdmin(@Param("admin") String admin);
}
