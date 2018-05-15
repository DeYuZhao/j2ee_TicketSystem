package edu.nju.ticketsystem.service;

import edu.nju.ticketsystem.model.VenueEntity;
import edu.nju.ticketsystem.pojo.*;
import edu.nju.ticketsystem.tools.VenueState;

import java.util.List;

public interface AdminService {
    public boolean judgeAdmin(String username, String password);

    public VenuePage getPageInfo(int start, int size,VenueState venueState);

    public Venue getVenue(String address,VenueState venueState);

    public boolean checkRegisterVenue(String address, VenueState venueState);

    public boolean checkChangeVenue(String address, VenueState venueState);

    public ShowinfoPage getSettleAccountPage(int start, int size);

    public void setBusinessProfit(String name, int profit);

    public VenuePage getVenuePage(int start, int size);

    public VenueEntity getVenueEntityByAddress(String address);

    public MemberStatistics getAllMemberStatistics(int type);
}
