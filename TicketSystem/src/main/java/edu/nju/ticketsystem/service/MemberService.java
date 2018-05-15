package edu.nju.ticketsystem.service;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;
import edu.nju.ticketsystem.pojo.BasicInfo;
import edu.nju.ticketsystem.pojo.MemberStatistics;
import edu.nju.ticketsystem.pojo.OrderPage;
import edu.nju.ticketsystem.pojo.Showinfo;
import edu.nju.ticketsystem.tools.OrderState;

public interface MemberService {
    public UserbasicinfoEntity getUserBasicInfo(String email);

    public boolean saveBasicInfo(BasicInfo basicInfo, String email);

    public boolean checkPassword(String email, String password);

    public boolean updatePassword(String email, String password);

    public MemberEntity getCurrentMemberInfo(String email);

    public boolean cancelMembership(String email);

    public boolean convertBenifits(String email, int level);

    public Showinfo setInitSeatInfo(String name);

    public boolean checkBalance(String email, String price, int benifit);

    public boolean checkBalance(String email, int benifit);

    public void generateOrder(String email, String totalprice, String name, int benefit, String[] seat);

    public boolean generateOrder(String email, String totalprice, String name, int benefit, int seatNum);

    public void updateMemberInfo(String email,String totalprice,int benefit);

    public void updateUserBasicInfo(String email,String totalprice,int benefit);

    public void updateSoldSeat(String name,String[] chooseSeat);

    public void updateCheckSeat(String name, String[] checkSeat);

    public OrderPage getOrderPageInfo(String email, int start, int size, OrderState orderState);

    public void dealOrder();

    public void returnTicket(int orderId);

    public MemberStatistics getUserStatisticsInfo(String email);
}
