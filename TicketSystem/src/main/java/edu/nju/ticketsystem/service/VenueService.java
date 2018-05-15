package edu.nju.ticketsystem.service;

import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.pojo.MemberStatistics;
import edu.nju.ticketsystem.pojo.Plan;
import edu.nju.ticketsystem.pojo.PlanPage;
import edu.nju.ticketsystem.pojo.Venue;
import edu.nju.ticketsystem.tools.VenueState;

import java.util.List;

public interface VenueService {
    public boolean addRegisterWaitVenue(String email, Venue venue, VenueState venueState);

    public String checkLogin(String email,String loginCode, String password);

    public Venue getVenueInfo(String email,String loginCode);

    public String askToChangeInfo(String email,String loginCode,Venue venue);

    public String publishPlan(String address, Plan plan);

    public PlanPage getVenueAllPlan(int start, int size, String email, String loginCode);

    public void generateSceneOrder(String name, String[] seat, int totalPrice);

    public void checkSecenOrder(String planName, String[] chooseSeat);

    public MemberStatistics getVenueStatistics(String email, String loginCode);
}
