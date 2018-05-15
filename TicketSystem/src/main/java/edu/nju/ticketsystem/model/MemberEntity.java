package edu.nju.ticketsystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "member", schema = "ticketsystem", catalog = "")
public class MemberEntity {
    private int mid;
    private String email;
    private String password;
    private String nickname;
    private byte membership;
    private int memberlevel;
    private int integration;
    private int benifitA;
    private int benifitB;
    private int benifitC;
    private int totalpoint;

    @Id
    @Column(name = "mid", nullable = false)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "membership", nullable = false)
    public byte getMembership() {
        return membership;
    }

    public void setMembership(byte membership) {
        this.membership = membership;
    }

    @Basic
    @Column(name = "memberlevel", nullable = false)
    public int getMemberlevel() {
        return memberlevel;
    }

    public void setMemberlevel(int memberlevel) {
        this.memberlevel = memberlevel;
    }

    @Basic
    @Column(name = "integration", nullable = false)
    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    @Basic
    @Column(name = "benifitA", nullable = false)
    public int getBenifitA() {
        return benifitA;
    }

    public void setBenifitA(int benifitA) {
        this.benifitA = benifitA;
    }

    @Basic
    @Column(name = "benifitB", nullable = false)
    public int getBenifitB() {
        return benifitB;
    }

    public void setBenifitB(int benifitB) {
        this.benifitB = benifitB;
    }

    @Basic
    @Column(name = "benifitC", nullable = false)
    public int getBenifitC() {
        return benifitC;
    }

    public void setBenifitC(int benifitC) {
        this.benifitC = benifitC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return mid == that.mid &&
                membership == that.membership &&
                memberlevel == that.memberlevel &&
                integration == that.integration &&
                benifitA == that.benifitA &&
                benifitB == that.benifitB &&
                benifitC == that.benifitC &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mid, email, password, nickname, membership, memberlevel, integration, benifitA, benifitB, benifitC);
    }

    @Basic
    @Column(name = "totalpoint", nullable = false)
    public int getTotalpoint() {
        return totalpoint;
    }

    public void setTotalpoint(int totalpoint) {
        this.totalpoint = totalpoint;
    }
}
