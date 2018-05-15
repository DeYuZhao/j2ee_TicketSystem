package edu.nju.ticketsystem.serviceImpl;

import edu.nju.ticketsystem.model.*;
import edu.nju.ticketsystem.pojo.MemberStatistics;
import edu.nju.ticketsystem.pojo.Plan;
import edu.nju.ticketsystem.pojo.PlanPage;
import edu.nju.ticketsystem.pojo.Venue;
import edu.nju.ticketsystem.repository.*;
import edu.nju.ticketsystem.service.VenueService;
import edu.nju.ticketsystem.tools.OrderState;
import edu.nju.ticketsystem.tools.SettleState;
import edu.nju.ticketsystem.tools.VenueState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private WaitdealvenueRepository waitdealvenueRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanseatinfoRepository planseatinfoRepository;
    @Autowired
    private OrderinfoRepository orderinfoRepository;
    @Autowired
    private BussinessRepository bussinessRepository;
    /**
     * 添加新注册的场馆
     * @param email 当前用户
     * @param venue 场馆信息
     * @param venueState 场馆信息类型（注册或修改）
     */
    @Override
    public boolean addRegisterWaitVenue(String email, Venue venue, VenueState venueState) {
        if (venueRepository.checkVenueAddress(venue.getAddress())==null
                && waitdealvenueRepository.getVenueInfo(venue.getAddress())==null) {
            WaitdealvenueEntity waitdealvenueEntity = new WaitdealvenueEntity();
            waitdealvenueEntity.setEmail(email);
            waitdealvenueEntity.setAddress(venue.getAddress());
            waitdealvenueEntity.setPartitionnum(venue.getPartitionnum());
            waitdealvenueEntity.setColumns(venue.getColumns());
            waitdealvenueEntity.setRows(venue.getRows());
            waitdealvenueEntity.setState(String.valueOf(venueState));
            waitdealvenueEntity.setPassword(venue.getPassword());
            waitdealvenueRepository.save(waitdealvenueEntity);
            return true;
        }
        return false;
    }

    /**
     * 场馆登录检查
     * @param email 当前用户
     * @param loginCode 登陆码
     * @param password 密码
     * @return
     */
    @Override
    public String checkLogin(String email, String loginCode, String password) {
        VenueEntity venueEntity = venueRepository.getVenueInfo(email,loginCode);
        String result = "";
        if (venueEntity == null){
            result += "登陆码错误";
        }else{
            if (password.equals(venueEntity.getPassword())){
                result += "登录成功";
            }else{
                result += "密码错误";
            }
        }
        return result;
    }

    /**
     * 获取场馆信息
     * @param email 当前用户
     * @param loginCode 登录码
     * @return
     */
    @Override
    public Venue getVenueInfo(String email, String loginCode) {
        VenueEntity venueEntity = venueRepository.getVenueInfo(email,loginCode);
        Venue venue = new Venue();
        venue.setAddress(venueEntity.getAddress());
        venue.setPartitionnum(venueEntity.getPartitionnum());
        venue.setRows(venueEntity.getRows());
        venue.setColumns(venueEntity.getColumns());
        return venue;
    }

    /**
     * 场馆请求修改信息
     * @param email 当前用户
     * @param loginCode 登录码
     * @param venue 场馆当前信息
     * @return
     */
    @Override
    public String askToChangeInfo(String email, String loginCode, Venue venue) {
        WaitdealvenueEntity w = new WaitdealvenueEntity();
        w.setAddress(venue.getAddress());
        w.setPartitionnum(venue.getPartitionnum());
        w.setRows(venue.getRows());
        w.setColumns(venue.getColumns());
        w.setEmail(email);
        w.setState(String.valueOf(VenueState.CHANGE_WAITING));
        w.setPassword(venueRepository.getVenueInfo(email,loginCode).getPassword());
        if (waitdealvenueRepository.getVenueInfo(String.valueOf(VenueState.CHANGE_WAITING),venue.getAddress())==null) {
            waitdealvenueRepository.save(w);
            return "申请已提交等待管理员通过";
        }else if (planRepository.findPlanByAddress(venue.getAddress())==null){
            return "近期有演出计划，不得修改";
        }
        return "已提交过申请，请耐心等待";
    }

    /**
     * 发布计划
     * @param address 地址
     * @param plan 计划内容
     */
    @Override
    public String publishPlan(String address, Plan plan) {
        PlanEntity planEntity = new PlanEntity();
        planEntity.setAddress(address);
        planEntity.setName(plan.getName());
        planEntity.setDate(plan.getDate());
        planEntity.setPrice(plan.getPrice());
        planEntity.setType(plan.getType());
        planEntity.setDescription(plan.getDescription());
        String checkDate = plan.getDate().substring(0,10);
        if (planRepository.findPlan(plan.getName())!=null){
            return "名称已存在";
        }else if (planRepository.getPlanEntitiesByDate(checkDate,address)!=null){
            return "演出时间冲突，请重新选择";
        }else if (plan.getPrice().split(",").length != venueRepository.checkVenueAddress(address).getPartitionnum()){
            return "价格应按分区数依次设置并以逗号隔开";
        }else{
            planRepository.save(planEntity);
            PlanseatinfoEntity planseatinfoEntity = new PlanseatinfoEntity();
            planseatinfoEntity.setPlanname(plan.getName());
            VenueEntity venueEntity = venueRepository.checkVenueAddress(address);
            int partitionnum = venueEntity.getPartitionnum();
            int rows = venueEntity.getRows();
            int columns = venueEntity.getColumns();
            String seatInfo = drawSeatInfo(partitionnum,rows,columns);
            planseatinfoEntity.setSeatinfo(seatInfo);
            planseatinfoEntity.setSoldseat("");
            planseatinfoEntity.setCheckseat("");
            planseatinfoRepository.save(planseatinfoEntity);
            BussinessEntity bussinessEntity = new BussinessEntity();
            bussinessEntity.setAddress(address);
            bussinessEntity.setPlanname(plan.getName());
            bussinessEntity.setNowprofit(0);
            bussinessEntity.setSettleState(String.valueOf(SettleState.NOT_SETTLED));
            bussinessRepository.save(bussinessEntity);
        }
        return "发布成功";
    }

    /**
     * 获得场馆的所有计划信息
     * @param loginCode 登录码
     * @param email 当前用户
     * @return
     */
    @Override
    public PlanPage getVenueAllPlan(int start, int size, String email, String loginCode) {
        PageRequest pageRequest = new PageRequest(start,size, Sort.Direction.ASC,"id");
        Page<PlanEntity> planEntityPage = planRepository.getPlanList(venueRepository.getVenueInfo(email,loginCode).getAddress(),pageRequest);
        int total = planRepository.getPlanListNumber(venueRepository.getVenueInfo(email,loginCode).getAddress());
        PlanPage planPage = new PlanPage();
        planPage.setTotal(total);
        planPage.setPlanEntities(planEntityPage.getContent());
        return planPage;
    }

    /**
     * 生成现场购票订单
     * @param name 演出名称
     * @param seat 选择座位
     * @param totalPrice 总价
     */
    @Override
    public void generateSceneOrder(String name, String[] seat, int totalPrice) {
        OrderinfoEntity orderinfoEntity = new OrderinfoEntity();
        orderinfoEntity.setEmail("default");
        orderinfoEntity.setTotalprice(totalPrice);
        orderinfoEntity.setName(name);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderinfoEntity.setDate(df.format(new Date()));
        orderinfoEntity.setOrderstate(String.valueOf(OrderState.WAIT_CHECK_TICKET));
        String seatInfo = "";
        for (int i = 0; i < seat.length; i++) {
            seatInfo += seat[i] + ",";
        }
        orderinfoEntity.setSeatinfo(seatInfo);
        orderinfoRepository.save(orderinfoEntity);
    }

    /**
     * 现场检票更新订单状态
     */
    @Override
    public void checkSecenOrder(String planName, String[] chooseSeat) {
        String seatinfo = "";
        for (int i=0;i<chooseSeat.length;i++){
            seatinfo += chooseSeat[i] + ",";
        }
        orderinfoRepository.updateOrderAfterCheck(String.valueOf(OrderState.HAVE_CHECKED), seatinfo, planName);
    }

    @Override
    public MemberStatistics getVenueStatistics(String email, String loginCode) {
        String address = venueRepository.getVenueInfo(email,loginCode).getAddress();
        List<PlanEntity> planList = planRepository.findPlanByAddress(address);
        List<String> reservedList = new ArrayList<>();
        List<String> returnList = new ArrayList<>();
        List<String> consumptionList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        for (int i=1;i<=12;i++){
            String targetDate = "";
            if (i>=1 && i<=9) targetDate += date.substring(0, 4) + "-0" + i;
            else targetDate += date.substring(0, 4) + "-" + i;
            int totalNum = 0;
            int reservedNum = 0;
            int returnNum = 0;
            int consumption = 0;
            for (int j=0;j<planList.size();j++){
                String planName = planList.get(j).getName();
                List<OrderinfoEntity> orderList = orderinfoRepository.getPlanOrderList(planName,targetDate);
                for (int k=0;k<orderList.size();k++){
                    if (!"default".equals(orderList.get(k).getEmail()))
                        totalNum += orderList.get(k).getSeatinfo().split(",").length;
                }
                orderList = orderinfoRepository.getPlanOrderList(planName,targetDate,String.valueOf(OrderState.RETURN_TICKET));
                for (int k=0;k<orderList.size();k++){
                    returnNum += orderList.get(k).getSeatinfo().split(",").length;
                }
                reservedNum = totalNum-returnNum;
                orderList = orderinfoRepository.getPlanOrderList(planName,targetDate,String.valueOf(OrderState.WAIT_CHECK_TICKET));
                for (int k=0;k<orderList.size();k++){
                    consumption += orderList.get(k).getTotalprice();
                }
                orderList = orderinfoRepository.getPlanOrderList(planName,targetDate,String.valueOf(OrderState.HAVE_CHECKED));
                for (int k=0;k<orderList.size();k++){
                    consumption += orderList.get(k).getTotalprice();
                }
            }
            reservedList.add(String.valueOf(reservedNum));
            returnList.add(String.valueOf(returnNum));
            consumptionList.add(String.valueOf(consumption));
        }
        MemberStatistics memberStatistics = new MemberStatistics();
        memberStatistics.setReservedNumList(reservedList);
        memberStatistics.setReturnNumList(returnList);
        memberStatistics.setConsumptionList(consumptionList);
        return memberStatistics;
    }

    /**
     * 绘制座位信息
     * @param partitionnum 分区数
     * @param rows 排数/区
     * @param columns 列数/区
     * @return
     */
    private String drawSeatInfo(int partitionnum,int rows,int columns){
        String seatInfo = "";
        for (int i=0;i<partitionnum;i++){
            String pass = "";
            for (int j=0;j<rows;j++){
                String seat = "";
                for (int k=0;k<columns;k++){
                    seat += "c";//c为一个空位置，_为过道
                }
                seat += ",";
                seatInfo += seat;
            }
            for (int k=0;k<columns;k++){
                pass += "_";//c为一个空位置，_为过道
            }
            pass += ",";
            seatInfo += pass;
        }
        return seatInfo;
    }
}
