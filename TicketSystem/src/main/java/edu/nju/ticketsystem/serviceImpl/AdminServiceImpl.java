package edu.nju.ticketsystem.serviceImpl;

import com.mchange.util.MEnumeration;
import edu.nju.ticketsystem.model.OrderinfoEntity;
import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.model.VenueEntity;
import edu.nju.ticketsystem.model.WaitdealvenueEntity;
import edu.nju.ticketsystem.pojo.*;
import edu.nju.ticketsystem.repository.*;
import edu.nju.ticketsystem.service.AdminService;
import edu.nju.ticketsystem.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private WaitdealvenueRepository waitdealvenueRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private OrderinfoRepository orderinfoRepository;
    @Autowired
    private BussinessRepository bussinessRepository;

    private int reservedNum = 0;
    private int returnNum = 0;
    private int consumption = 0;

    public boolean judgeAdmin(String username, String password){
        if (adminRepository.findAdmin(username) != null) {
            if (password.equals(adminRepository.findAdmin(username).getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得对应的分页数据
     * @param start 查询起点
     * @param size 一次查询数据量
     * @return
     */
    @Override
    public VenuePage getPageInfo(int start, int size, VenueState venueState) {
        PageRequest pageable = new PageRequest(start,size, Sort.Direction.ASC,"id");
        Page<WaitdealvenueEntity> page = waitdealvenueRepository.getVenueList(String.valueOf(venueState),pageable);
        int total = waitdealvenueRepository.getTotalNumOfVenue(String.valueOf(venueState));
        VenuePage venuePage = new VenuePage();
        venuePage.setTotal(total);
        venuePage.setWaitdealvenueEntityList(page.getContent());
        return venuePage;
    }

    /**
     * 获得对应场馆信息
     * @param address 场馆地址
     * @return
     */
    @Override
    public Venue getVenue(String address,VenueState venueState) {
        WaitdealvenueEntity w = waitdealvenueRepository.getVenueInfo(String.valueOf(venueState),address);
        Venue venue = new Venue();
        venue.setEmail(w.getEmail());
        venue.setAddress(w.getAddress());
        venue.setPassword(w.getPassword());
        venue.setPartitionnum(w.getPartitionnum());
        venue.setRows(w.getRows());
        venue.setColumns(w.getColumns());
        return venue;
    }

    /**
     * 修改注册场馆的状态
     * @param address 场馆地址
     * @param venueState 需要修改的状态
     * @return
     */
    @Override
    public boolean checkRegisterVenue(String address, VenueState venueState) {
            if (venueState == VenueState.PASS) {
                WaitdealvenueEntity w = waitdealvenueRepository.getVenueInfo(String.valueOf(VenueState.REGISTER_WAITING),address);
                waitdealvenueRepository.changeVenueState(String.valueOf(venueState),address,String.valueOf(VenueState.REGISTER_WAITING));
                String loginCode = GenerateLoginCode.getInstance().generateLoginCode(w.getId());
                VenueEntity venueEntity = new VenueEntity();
                venueEntity.setEmail(w.getEmail());
                venueEntity.setAddress(w.getAddress());
                venueEntity.setLogincode(loginCode);
                venueEntity.setPassword(w.getPassword());
                venueEntity.setPartitionnum(w.getPartitionnum());
                venueEntity.setRows(w.getRows());
                venueEntity.setColumns(w.getColumns());
                venueRepository.save(venueEntity);
                String text = "地址为："+w.getAddress()+"\n"+"登录码为:"+loginCode;
                try {
                    MailTools.getInstance().sendMail(w.getEmail(),text);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }else {
                waitdealvenueRepository.changeVenueState(String.valueOf(venueState),address,String.valueOf(VenueState.REGISTER_WAITING));
            }
        return true;
    }

    /**
     * 操作修改场馆申请
     * @param address 地址
     * @param venueState 场馆状态
     * @return
     */
    @Override
    public boolean checkChangeVenue(String address, VenueState venueState) {
            if (venueState == VenueState.PASS) {
                WaitdealvenueEntity w = waitdealvenueRepository.getVenueInfo(String.valueOf(VenueState.CHANGE_WAITING),address);
                waitdealvenueRepository.changeVenueState(String.valueOf(venueState),address,String.valueOf(VenueState.CHANGE_WAITING));
                venueRepository.updateChangeVenueInfo(w.getPartitionnum(),w.getRows(),w.getColumns(),w.getAddress());
                String text = "场馆："+w.getAddress()+"\n"+"修改申请已通过";
                try {
                    MailTools.getInstance().sendMail(w.getEmail(),text);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }else {
                waitdealvenueRepository.changeVenueState(String.valueOf(venueState),address,String.valueOf(VenueState.CHANGE_WAITING));
            }
            return true;
    }

    /**
     * 获取对应的结算信息
     * @param start
     * @param size
     * @return
     */
    @Override
    public ShowinfoPage getSettleAccountPage(int start, int size) {
        PageRequest pageable = new PageRequest(start,size, Sort.Direction.ASC,"id");
        Page<PlanEntity> page = planRepository.getSettlePlanList(DateTools.getInstance().getWantedDate(-1).substring(0,10),pageable);
        int total = planRepository.getTotalSettlePlan(DateTools.getInstance().getWantedDate(-1).substring(0,10));
        List<PlanEntity> planEntities = page.getContent();
        List<Showinfo> showinfoList = new ArrayList<>();
        for (int i=-0;i<planEntities.size();i++){
            PlanEntity planEntity = planEntities.get(i);
            if (bussinessRepository.getBussinessEntityByPlanname(planEntity.getName()).getSettleState() == String.valueOf(SettleState.NOT_SETTLED)) {
                List<OrderinfoEntity> orderinfoEntityList = orderinfoRepository.getOrderList(String.valueOf(OrderState.HAVE_CHECKED), planEntity.getName());
                int businessVolume = 0;
                for (int j = 0; j < orderinfoEntityList.size(); j++) {
                    businessVolume += orderinfoEntityList.get(j).getTotalprice();
                }
                Showinfo showinfo = new Showinfo();
                showinfo.setAddress(planEntity.getAddress());
                showinfo.setName(planEntity.getName());
                showinfo.setPrice(String.valueOf(businessVolume));
                showinfo.setCheckSeat("");
                showinfo.setSoldSeat("");
                showinfo.setSeatInfo("");
                showinfo.setDate("");
                showinfoList.add(showinfo);
            }
        }
        ShowinfoPage showinfoPage = new ShowinfoPage();
        showinfoPage.setTotal(total);
        showinfoPage.setShowinfoList(showinfoList);
        return showinfoPage;
    }

    /**
     * 设置结算信息
     * @param name 演出名称
     * @param profit 利润
     */
    @Override
    public void setBusinessProfit(String name, int profit) {
        bussinessRepository.updateBusinessProfit(name,profit,String.valueOf(SettleState.HAVE_SETTLED));
    }

    /**
     * 获取对应场馆
     * @param start
     * @param size
     * @return
     */
    @Override
    public VenuePage getVenuePage(int start, int size) {
        PageRequest pageable = new PageRequest(start,size, Sort.Direction.ASC,"id");
        Page<VenueEntity> page = venueRepository.getVenuePage(pageable);
        VenuePage venuePage = new VenuePage();
        venuePage.setVenueEntityList(page.getContent());
        venuePage.setTotal(venueRepository.getTotalVenue());
        return venuePage;
    }


    @Override
    public VenueEntity getVenueEntityByAddress(String address) {
        return venueRepository.checkVenueAddress(address);
    }


    /**
     * 获取所有会员统计信息
     * @return
     */
    @Override
    public MemberStatistics getAllMemberStatistics(int type) {
        List<OrderinfoEntity> orderinfoEntityList = orderinfoRepository.findAll();
        List<String> reservedNumList = new ArrayList<>();
        List<String> returnNumList = new ArrayList<>();
        List<String> consumptionList = new ArrayList<>();
        for (int j=1;j<=12;j++) {
            String month = "";
            reservedNum = 0;
            returnNum = 0;
            consumption = 0;
            if (j<=9) month += "0"+j;
            else month += j;
            for (int i = 0; i < orderinfoEntityList.size(); i++) {
                OrderinfoEntity order = orderinfoEntityList.get(i);
                if (!"default".equals(order.getEmail()) && order.getDate().substring(5,7).equals(month)) {
                    if (type == 0) {
                        if (memberRepository.findMember(order.getEmail()).getMembership() == (byte) 1) {
                            matchOrderState(order.getOrderstate(),order);
                        }
                    }else{
                        matchOrderState(order.getOrderstate(),order);
                    }
                }
            }
            reservedNumList.add(String.valueOf(reservedNum));
            returnNumList.add(String.valueOf(returnNum));
            consumptionList.add(String.valueOf(consumption));
        }
        MemberStatistics memberStatistics = new MemberStatistics();
        memberStatistics.setReservedNumList(reservedNumList);
        memberStatistics.setReturnNumList(returnNumList);
        memberStatistics.setConsumptionList(consumptionList);
        return memberStatistics;
    }

    private void matchOrderState(String orderState, OrderinfoEntity order){
        switch (orderState) {
            case "WAIT_ALLOCATE_TICKET":
                reservedNum += order.getSeatinfo().split(",").length;
                consumption += order.getTotalprice();
                break;
            case "WAIT_CHECK_TICKET":
                reservedNum += order.getSeatinfo().split(",").length;
                consumption += order.getTotalprice();
                break;
            case "HAVE_CHECKED":
                reservedNum += order.getSeatinfo().split(",").length;
                consumption += order.getTotalprice();
                break;
            case "OUT_DATE_TICKET":
                reservedNum += order.getSeatinfo().split(",").length;
                consumption += order.getTotalprice();
                break;
            case "RETURN_TICKET":
                returnNum += order.getSeatinfo().split(",").length;
                break;
        }
    }
}
