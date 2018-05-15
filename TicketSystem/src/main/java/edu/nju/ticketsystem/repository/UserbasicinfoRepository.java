package edu.nju.ticketsystem.repository;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface UserbasicinfoRepository extends JpaRepository<UserbasicinfoEntity,Integer> {
    @Query("select u from UserbasicinfoEntity u where u.email=:email")
    UserbasicinfoEntity getBasicInfo(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("update UserbasicinfoEntity u set u.nickname=:nickname,u.sex=:sex,u.birthday=:birthday,u.marriage=:marriage,u.education=:education," +
            "u.bussiness=:bussiness,u.income=:income,u.address=:address,u.balance=:balance where u.email=:email")
    int updateUserBasicInfo(@Param("nickname")String nickname,@Param("sex")String sex,@Param("birthday")String birthday,@Param("marriage")String marriage,
                            @Param("education")String education,@Param("bussiness")String bussiness,@Param("income")String income,
                            @Param("address")String address,@Param("balance")int balance,@Param("email")String email);
    @Modifying
    @Transactional
    @Query("update UserbasicinfoEntity u set u.balance=:balance where u.email=:email")
    int updateBalance(@Param("balance")int balance,@Param("email")String email);
}
