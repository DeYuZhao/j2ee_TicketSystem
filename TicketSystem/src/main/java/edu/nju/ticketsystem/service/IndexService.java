package edu.nju.ticketsystem.service;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;

public interface IndexService {
    public boolean findEmail(String email);

    public void addRegisterUser(MemberEntity memberEntity, UserbasicinfoEntity userbasicinfoEntity);

    public String login(String email, String password);
}
