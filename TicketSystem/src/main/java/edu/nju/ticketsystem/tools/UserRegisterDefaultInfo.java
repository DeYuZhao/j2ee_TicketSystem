package edu.nju.ticketsystem.tools;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRegisterDefaultInfo {
    private static UserRegisterDefaultInfo userRegisterDefaultInfo = new UserRegisterDefaultInfo();
    public static UserRegisterDefaultInfo getInstance(){
        return userRegisterDefaultInfo;
    }
    /**
     * 给新注册用户设置初始会员信息
     * @param memberEntity
     */
    public void setMemberDefaultInfo(MemberEntity memberEntity){
        memberEntity.setMemberlevel(0);
        memberEntity.setMembership((byte)1);
        memberEntity.setIntegration(0);
        memberEntity.setBenifitA(0);
        memberEntity.setBenifitB(0);
        memberEntity.setBenifitC(0);
    }

    /**
     * 给新注册用户设置初始基本信息
     * @param userbasicinfoEntity
     */
    public void setBasicDefaultInfo(UserbasicinfoEntity userbasicinfoEntity){
        userbasicinfoEntity.setAddress("");
        userbasicinfoEntity.setSex("");
        Date date = new Date();
        userbasicinfoEntity.setBirthday("");
        userbasicinfoEntity.setMarriage("");
        userbasicinfoEntity.setBalance(9999);
        userbasicinfoEntity.setEducation("");
        userbasicinfoEntity.setBussiness("");
        userbasicinfoEntity.setIncome("");
    }
}
