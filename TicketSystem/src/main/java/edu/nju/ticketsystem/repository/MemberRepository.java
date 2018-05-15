package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.MemberEntity;
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
public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{

    @Query("select m from MemberEntity m where m.email=:email")
    MemberEntity findMember(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.password=:password where m.email=:email")
    int updatePassword(@Param("email")String email,@Param("password")String password);

    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.membership=:membership where m.email=:email")
    int updateMembership(@Param("email")String email,@Param("membership")byte membership);

    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.integration=:integration where m.email=:email")
    int updateIntergration(@Param("email")String email,@Param("integration")int integration);

    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.totalpoint=:totalpoint where m.email=:email")
    int updateTotalPoint(@Param("email")String email,@Param("totalpoint")int totalpoint);

    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.benifitA=:benifitA,m.benifitB=:benifitB,m.benifitC=:benifitC where m.email=:email")
    int updateBenifits(@Param("email")String email,@Param("benifitA")int benifitA,@Param("benifitB")int benifitB,@Param("benifitC")int benifitC);

    @Modifying
    @Transactional
    @Query("update MemberEntity m set m.memberlevel=:memberlevel,m.integration=:integration,m.benifitA=:benifitA," +
            "m.benifitB=:benifitB,m.benifitC=:benifitC,m.totalpoint=:totalpoint where m.email=:email")
    int updateMemberInfo(@Param("memberlevel")int memberlevel,@Param("integration")int integration,@Param("benifitA")int benifitA,
                         @Param("benifitB")int benifitB,@Param("benifitC")int benifitC,@Param("totalpoint")int totalpoint,
                         @Param("email")String email);
}
