package edu.nju.ticketsystem.pojo;

public class BasicInfo {
    private String nickname;
    private String sex;
    private String birthday;
    private String marriage;
    private String education;
    private String bussiness;
    private String income;
    private String address;
    private int balance;
    public BasicInfo(){

    }
    public BasicInfo(String nickname, String sex, String birthday, String marriage, String education,
             String bussiness, String income, String address, int balance){
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.marriage = marriage;
        this.education = education;
        this.bussiness = bussiness;
        this.income = income;
        this.address = address;
        this.balance = balance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBussiness() {
        return bussiness;
    }

    public void setBussiness(String bussiness) {
        this.bussiness = bussiness;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
