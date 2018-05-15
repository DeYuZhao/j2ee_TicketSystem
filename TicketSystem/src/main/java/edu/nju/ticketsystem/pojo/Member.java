package edu.nju.ticketsystem.pojo;

public class Member {
    private String email;
    private int membership;
    private int  memberlevel;
    private int integration;
    private int  benifitA;
    private int  benifitB;
    private int benifitC;

    public Member(){

    }

    public Member(String email, int membership, int memberlevel, int integration, int benifitA, int benifitB, int benifitC){
        this.email = email;
        this.membership = membership;
        this.memberlevel = memberlevel;
        this.integration = integration;
        this.benifitA = benifitA;
        this.benifitB = benifitB;
        this.benifitC = benifitC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public int getMemberlevel() {
        return memberlevel;
    }

    public void setMemberlevel(int memberlevel) {
        this.memberlevel = memberlevel;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getBenifitA() {
        return benifitA;
    }

    public void setBenifitA(int benifitA) {
        this.benifitA = benifitA;
    }

    public int getBenifitB() {
        return benifitB;
    }

    public void setBenifitB(int benifitB) {
        this.benifitB = benifitB;
    }

    public int getBenifitC() {
        return benifitC;
    }

    public void setBenifitC(int benifitC) {
        this.benifitC = benifitC;
    }
}
