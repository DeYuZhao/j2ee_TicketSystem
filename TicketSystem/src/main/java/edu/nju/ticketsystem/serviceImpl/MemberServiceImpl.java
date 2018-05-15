package edu.nju.ticketsystem.serviceImpl;

import edu.nju.ticketsystem.model.*;
import edu.nju.ticketsystem.pojo.BasicInfo;
import edu.nju.ticketsystem.pojo.MemberStatistics;
import edu.nju.ticketsystem.pojo.OrderPage;
import edu.nju.ticketsystem.pojo.Showinfo;
import edu.nju.ticketsystem.repository.*;
import edu.nju.ticketsystem.service.MemberService;
import edu.nju.ticketsystem.tools.DateTools;
import edu.nju.ticketsystem.tools.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private UserbasicinfoRepository userbasicinfoRepository;
    @Autowired
    private  MemberRepository memberRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanseatinfoRepository planseatinfoRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private OrderinfoRepository orderinfoRepository;
    /**
     * 获取用户当前基本信息
     * @param email 用户email
     * @return
     */
    @Override
    public UserbasicinfoEntity getUserBasicInfo(String email) {
        return userbasicinfoRepository.getBasicInfo(email);
    }

    /**
     * 修改用户基本信息
     * @param basicInfo 基本信息对象
     * @param email 当前用户
     * @return
     */
    @Override
    public boolean saveBasicInfo(BasicInfo basicInfo, String email) {
        List<UserbasicinfoEntity> list = userbasicinfoRepository.findAll();
        for (int i=0;i<list.size();i++){
            if (basicInfo.getNickname().equals(list.get(i).getNickname()) && !email.equals(list.get(i).getEmail())){
                return false;
            }
        }
        int result =  userbasicinfoRepository.updateUserBasicInfo(basicInfo.getNickname(),basicInfo.getSex(),basicInfo.getBirthday(),basicInfo.getMarriage(),
                                                                  basicInfo.getEducation(),basicInfo.getBussiness(),basicInfo.getIncome(),
                                                                  basicInfo.getAddress(),basicInfo.getBalance(),email);
        if (result!=0)
            return true;
        return false;
    }

    /**
     * 修改密码时检查密码正确性
     * @param email 当前用户
     * @param password 输入密码
     * @return
     */
    @Override
    public boolean checkPassword(String email, String password) {
        String true_password =  memberRepository.findMember(email).getPassword();
        if (true_password.equals(password)) return true;
        return false;
    }

    /**
     * 更新密码
     * @param email 当前用户
     * @param password 新密码
     * @return
     */
    @Override
    public boolean updatePassword(String email, String password) {
        int result = memberRepository.updatePassword(email,password);
        if (result!=0) return true;
        return false;
    }

    /**
     * 获取当前用户的会员信息
     * @param email 当前用户
     * @return
     */
    @Override
    public MemberEntity getCurrentMemberInfo(String email) {
        return memberRepository.findMember(email);
    }

    /**
     * 撤销会员资格
     * @param email 当前用户
     * @return
     */
    @Override
    public boolean cancelMembership(String email) {
        int result = memberRepository.updateMembership(email, (byte)0);
        if (result!=0) return true;
        return false;
    }

    /**
     * 兑换优惠券
     * @param email 当前用户
     * @param level 兑换的优惠券档位
     * @return
     */
    @Override
    public boolean convertBenifits(String email, int level) {
        MemberEntity memberEntity = memberRepository.findMember(email);
        if (memberEntity.getIntegration()>level){
            int nowIntegration = memberEntity.getIntegration()-level;
            int nowbenifitA = memberEntity.getBenifitA();
            int nowbenifitB = memberEntity.getBenifitB();
            int nowbenifitC = memberEntity.getBenifitC();
            memberRepository.updateIntergration(email,nowIntegration);
            switch (level){
                case 2000:memberRepository.updateBenifits(email,++nowbenifitA,nowbenifitB,nowbenifitC);break;
                case 5000:memberRepository.updateBenifits(email,nowbenifitA,++nowbenifitB,nowbenifitC);break;
                case 8000:memberRepository.updateBenifits(email,nowbenifitA,nowbenifitB,++nowbenifitC);break;
            }
            return true;
        }
        return false;
    }

    /**
     * 设置选择座位界面信息
     * @param name 演出名称
     * @return
     */
    @Override
    public Showinfo setInitSeatInfo(String name) {
        PlanEntity planEntity = planRepository.findPlan(name);
        PlanseatinfoEntity planseatinfoEntity = planseatinfoRepository.getPlanseatinfoEntity(name);
        Showinfo showinfo = new Showinfo();
        showinfo.setName(name);
        showinfo.setDate(planEntity.getDate());
        showinfo.setPrice(planEntity.getPrice());
        showinfo.setAddress(planEntity.getAddress());
        showinfo.setSeatInfo(planseatinfoEntity.getSeatinfo());
        showinfo.setSoldSeat(planseatinfoEntity.getSoldseat());
        showinfo.setCheckSeat(planseatinfoEntity.getCheckseat());
        return showinfo;
    }

    /**
     * 检查账户余额
     * @param email 当前用户
     * @param price 需要支付金额
     * @return
     */
    @Override
    public boolean checkBalance(String email, String price, int benifit) {
        MemberEntity memberEntity = memberRepository.findMember(email);
        int memberlevel = memberEntity.getMemberlevel();
        LevelEntity levelEntity = levelRepository.getLevel(memberlevel);
        double ratio = levelEntity.getBenifit();
        UserbasicinfoEntity u = userbasicinfoRepository.getBasicInfo(email);
        if (u.getBalance()>=((Integer.parseInt(price)-benifit))*ratio){
            return true;
        }
        return false;
    }

    /**
     * 检查优惠券
     * @param email 当前用户
     * @param benifit 选择的优惠券
     * @return
     */
    @Override
    public boolean checkBalance(String email, int benifit) {
        MemberEntity memberEntity = memberRepository.findMember(email);
        int twenty = memberEntity.getBenifitA();
        int sixty = memberEntity.getBenifitB();
        int ninety = memberEntity.getBenifitC();
        if (benifit == 0){
            return true;
        }else if (benifit == 20){
            if (twenty > 0) return true;
        }else if (benifit == 60){
            if (sixty > 0) return true;
        }else if (benifit == 90){
            if (ninety > 0) return true;
        }
        return false;
    }

    /**
     * 生成选座购买订单
     * @param email 当前用户
     * @param totalprice 原价
     * @param name 演出名称
     * @param benefit 优惠金额
     * @param seat 座位信息
     */
    @Override
    public void generateOrder(String email, String totalprice, String name, int benefit, String[] seat) {
        MemberEntity memberEntity = memberRepository.findMember(email);
        int memberlevel = memberEntity.getMemberlevel();
        LevelEntity levelEntity = levelRepository.getLevel(memberlevel);
        double ratio = levelEntity.getBenifit();
        OrderinfoEntity orderEntity = new OrderinfoEntity();
        orderEntity.setEmail(email);
        orderEntity.setTotalprice((int)((Integer.parseInt(totalprice)-benefit)*ratio));
        orderEntity.setName(name);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderEntity.setDate(df.format(new Date()));
        String seatInfo = "";
        orderEntity.setOrderstate(String.valueOf(OrderState.WAIT_CHECK_TICKET));
        for (int i = 0; i < seat.length; i++) {
            seatInfo += seat[i] + ",";
        }
        orderEntity.setSeatinfo(seatInfo);
        orderinfoRepository.save(orderEntity);
    }

    /**
     * 生成立即购买订单
     * @param email 当前用户
     * @param totalprice 总交易金额
     * @param name 演出名称
     * @param benefit 优惠金额
     * @param seatNum 购买座位数
     */
    @Override
    public boolean generateOrder(String email, String totalprice, String name, int benefit, int seatNum) {
        boolean result = true;
        MemberEntity memberEntity = memberRepository.findMember(email);
        int memberlevel = memberEntity.getMemberlevel();
        LevelEntity levelEntity = levelRepository.getLevel(memberlevel);
        double ratio = levelEntity.getBenifit();
        OrderinfoEntity orderEntity = new OrderinfoEntity();
        orderEntity.setEmail(email);
        orderEntity.setName(name);
        orderEntity.setTotalprice((int)((Integer.parseInt(totalprice)-benefit)*ratio));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderEntity.setDate(df.format(new Date()));
        String endDate = planRepository.findPlan(name).getDate().substring(0,10);
        String startDate = df.format(new Date()).substring(0,10);
        int minusDay = DateTools.getInstance().getDateMinus(startDate,endDate);
        if (minusDay > 14){
            orderEntity.setOrderstate(String.valueOf(OrderState.WAIT_ALLOCATE_TICKET));
            orderEntity.setSeatinfo(String.valueOf(totalprice)+"_"+String.valueOf(seatNum));
            orderinfoRepository.save(orderEntity);
        }else {
            String seatInfo = "";
            String[] allocatedSeat = new String[seatNum];
            String[] nowSeatInfo = getNowSeatInfo(name);
            int hasAllocatedNum = 0;
            PlanEntity planEntity = planRepository.findPlan(name);
            String[] priceArray = planEntity.getPrice().split(",");
            int index = 0;
            for (int i=0;i<priceArray.length;i++){
               if (Integer.parseInt(totalprice)/seatNum == Integer.parseInt(priceArray[i])){
                   index = priceArray.length-i-1;
                   break;
               }
            }
            for (int i=nowSeatInfo.length/priceArray.length*index;i<nowSeatInfo.length/priceArray.length*(index+1);i++){//此处i应为根据票价确定的分区，需要修改
                for (int j=0;j<(nowSeatInfo[0]).length();j++){
                    if ("c".equals(nowSeatInfo[i].substring(j,j+1)) && hasAllocatedNum<seatNum){
                        seatInfo += i+1+"_"+(j+1)+",";
                        allocatedSeat[hasAllocatedNum] = i+1+"_"+(j+1);
                        hasAllocatedNum++;
                    }else if (hasAllocatedNum>=seatNum) break;
                }
            }
            if (hasAllocatedNum == seatNum) {
                orderEntity.setOrderstate(String.valueOf(OrderState.WAIT_CHECK_TICKET));
                orderEntity.setSeatinfo(seatInfo);
                orderinfoRepository.save(orderEntity);
                updateSoldSeat(name, allocatedSeat);
            }else {
                result = false;
            }
        }
        return result;
    }

    /**
     * 更新会员信息
     */
    @Override
    public void updateMemberInfo(String email,String totalprice,int benefit) {
        MemberEntity memberEntity = memberRepository.findMember(email);
        int memberlevel = memberEntity.getMemberlevel();
        int benefitA = memberEntity.getBenifitA();
        int benefitB = memberEntity.getBenifitB();
        int benefitC = memberEntity.getBenifitC();
        LevelEntity levelEntity = levelRepository.getLevel(memberlevel);
        double ratio = levelEntity.getBenifit();
        int integration = (int)(memberEntity.getIntegration() + (Integer.parseInt(totalprice) - benefit)*ratio);
        int point = (int)(memberEntity.getTotalpoint() + (Integer.parseInt(totalprice) - benefit)*ratio);
        if (point>=1000 && point<3000){
            memberlevel = 1;
        }else if (point>=3000 && point<6000){
            memberlevel = 2;
        }else if (point>=6000 && point<10000){
            memberlevel = 3;
        }else if (point>=10000 && point<15000){
            memberlevel = 4;
        }else if (point>=15000){
            memberlevel = 5;
        }
        switch (benefit){
            case 20:benefitA++;break;
            case 60:benefitB++;break;
            case 90:benefitC++;break;
        }
        memberRepository.updateMemberInfo(memberlevel,integration,benefitA,benefitB,benefitC,point,email);
    }

    /**
     * 更新用户基本信息
     */
    @Override
    public void updateUserBasicInfo(String email,String totalprice,int benefit) {
        MemberEntity memberEntity = memberRepository.findMember(email);
        int memberlevel = memberEntity.getMemberlevel();
        LevelEntity levelEntity = levelRepository.getLevel(memberlevel);
        double ratio = levelEntity.getBenifit();
        UserbasicinfoEntity userbasicinfoEntity = userbasicinfoRepository.getBasicInfo(email);
        int balance = userbasicinfoEntity.getBalance();
        balance = (int)(balance - (Integer.parseInt(totalprice)-benefit)*ratio);
        userbasicinfoRepository.updateBalance(balance,email);
    }

    /**
     * 更新卖出座位
     */
    @Override
    public void updateSoldSeat(String name,String[] chooseSeat ) {
        String soldSeat = planseatinfoRepository.getPlanseatinfoEntity(name).getSoldseat();
        for (int i=0;i<chooseSeat.length;i++){
            soldSeat += chooseSeat[i]+",";
        }
        planseatinfoRepository.updataSoldSeat(soldSeat,name);
    }

    /**
     * 更新检票座位
     * @param name
     * @param checkSeat
     */
    @Override
    public void updateCheckSeat(String name, String[] checkSeat) {
        String seat = planseatinfoRepository.getPlanseatinfoEntity(name).getCheckseat();
        for (int i=0;i<checkSeat.length;i++){
            seat += checkSeat[i]+",";
        }
        planseatinfoRepository.updataCheckSeat(seat,name);
    }

    /**
     * 获取订单页面信息
     * @param start 开始编号
     * @param size 长度
     * @param orderState 订单状态
     * @return
     */
    @Override
    public OrderPage getOrderPageInfo(String email, int start, int size, OrderState orderState) {
        PageRequest pageable = new PageRequest(start,size, Sort.Direction.ASC,"id");
        Page<OrderinfoEntity> order= orderinfoRepository.getOrderList(String.valueOf(orderState),email,pageable);
        int total = orderinfoRepository.getTotalNumOfOrder(String.valueOf(orderState),email);
        OrderPage orderPage = new OrderPage();
        orderPage.setTotal(total);
        orderPage.setOrderEntityList(order.getContent());
        HashMap<String,String> planDateMap = new HashMap<String,String>();
        HashMap<String,String> planAddressMap = new HashMap<String,String>();
        for (int i=0;i<order.getContent().size();i++){
            PlanEntity planEntity = planRepository.findPlan(order.getContent().get(i).getName());
            if (!planDateMap.containsKey(planEntity.getName())){
                planDateMap.put(planEntity.getName(),planEntity.getDate());
            }
            if (!planAddressMap.containsKey(planEntity.getName())){
                planAddressMap.put(planEntity.getName(),planEntity.getAddress());
            }
        }
        orderPage.setPlanDateMap(planDateMap);
        orderPage.setPlanAddressMap(planAddressMap);
        return orderPage;
    }

    /**
     * 处理待配票订单和过期订单
     */
    @Override
    public void dealOrder() {
        List<OrderinfoEntity> waitAllocateList = orderinfoRepository.getDealOrderList(String.valueOf(OrderState.WAIT_ALLOCATE_TICKET));
        List<OrderinfoEntity> waitCheckList = orderinfoRepository.getDealOrderList(String.valueOf(OrderState.WAIT_CHECK_TICKET));
        for (int m=0;m<waitAllocateList.size();m++){
            String seatInfo = "";
            int totalprice = Integer.parseInt(waitAllocateList.get(m).getSeatinfo().split("_")[0]);
            int seatNum = Integer.parseInt(waitAllocateList.get(m).getSeatinfo().split("_")[1]);
            String name = waitAllocateList.get(m).getName();
            String[] allocatedSeat = new String[seatNum];
            String[] nowSeatInfo = getNowSeatInfo(name);
            int hasAllocatedNum = 0;
            PlanEntity planEntity = planRepository.findPlan(name);
            String[] priceArray = planEntity.getPrice().split(",");
            int index = 0;
            for (int i=0;i<priceArray.length;i++){
                if (totalprice/seatNum == Integer.parseInt(priceArray[i])){
                    index = priceArray.length-i-1;
                    break;
                }
            }
            for (int i=nowSeatInfo.length/priceArray.length*index;i<nowSeatInfo.length/priceArray.length*(index+1);i++){//此处i应为根据票价确定的分区，需要修改
                for (int j=0;j<(nowSeatInfo[0]).length();j++){
                    if ("c".equals(nowSeatInfo[i].substring(j,j+1)) && hasAllocatedNum<seatNum){
                        seatInfo += i+1+"_"+(j+1)+",";
                        allocatedSeat[hasAllocatedNum] = i+1+"_"+(j+1);
                        hasAllocatedNum++;
                    }else if (hasAllocatedNum>=seatNum) break;
                }
            }
            if (hasAllocatedNum == seatNum) {
                orderinfoRepository.updateOrder(String.valueOf(OrderState.WAIT_CHECK_TICKET),seatInfo,waitAllocateList.get(m).getId());
                updateSoldSeat(name, allocatedSeat);
            }else {
                OrderinfoEntity orderInfo = waitAllocateList.get(m);
                int integration = memberRepository.findMember(orderInfo.getEmail()).getIntegration();
                int totalIntegration = memberRepository.findMember(orderInfo.getEmail()).getTotalpoint();
                int balance = userbasicinfoRepository.getBasicInfo(orderInfo.getEmail()).getBalance();
                int orderPrice = orderInfo.getTotalprice();
                orderinfoRepository.updateOrder(String.valueOf(OrderState.OUT_DATE_TICKET),"",waitAllocateList.get(m).getId());
                memberRepository.updateIntergration(orderInfo.getEmail(),integration-orderPrice);
                memberRepository.updateTotalPoint(orderInfo.getEmail(),totalIntegration-orderPrice);
                userbasicinfoRepository.updateBalance(balance+orderPrice,orderInfo.getEmail());
            }
        }
        for (int m=0;m<waitCheckList.size();m++){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDate = df.format(new Date());
            String endDate = planRepository.findPlan(waitCheckList.get(m).getName()).getDate();
            if (!DateTools.getInstance().judgeOutDate(startDate,endDate)){
                orderinfoRepository.updateOrder(String.valueOf(OrderState.OUT_DATE_TICKET),waitCheckList.get(m).getSeatinfo(),waitCheckList.get(m).getId());
            }
        }
    }

    /**
     * 退订
     * @param orderId 订单编号
     */
    @Override
    public void returnTicket(int orderId) {
        OrderinfoEntity orderInfo = orderinfoRepository.getOrderInfo(orderId);
        String OrderDate = planRepository.findPlan(orderInfo.getName()).getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = df.format(new Date());
        int integration = memberRepository.findMember(orderInfo.getEmail()).getIntegration();
        int totalIntegration = memberRepository.findMember(orderInfo.getEmail()).getTotalpoint();
        int balance = userbasicinfoRepository.getBasicInfo(orderInfo.getEmail()).getBalance();
        int orderPrice = orderInfo.getTotalprice();
        int dayMinus = DateTools.getInstance().getDateMinus(nowDate,OrderDate);
        if (dayMinus>=7){
            memberRepository.updateIntergration(orderInfo.getEmail(),integration-orderPrice);
            memberRepository.updateTotalPoint(orderInfo.getEmail(),totalIntegration-orderPrice);
            userbasicinfoRepository.updateBalance(balance+orderPrice,orderInfo.getEmail());
        }else if (dayMinus<7){
            memberRepository.updateIntergration(orderInfo.getEmail(),integration-orderPrice);
            memberRepository.updateTotalPoint(orderInfo.getEmail(),totalIntegration-orderPrice);
            userbasicinfoRepository.updateBalance(balance+orderPrice/7*dayMinus,orderInfo.getEmail());
        }
        orderinfoRepository.updateOrder(String.valueOf(OrderState.RETURN_TICKET),orderInfo.getSeatinfo(),orderId);
        String[] seatInfo = orderInfo.getSeatinfo().split(",");
        String[] soldSeat = planseatinfoRepository.getPlanseatinfoEntity(orderInfo.getName()).getSoldseat().split(",");
        String newSeatInfo = "";
        for (int i=0;i<soldSeat.length;i++){
            boolean found = false;
            for (int j=0;j<seatInfo.length;j++){
                if (soldSeat[i].equals(seatInfo[j])){
                    found = true;
                }
            }
            if (!found){
                newSeatInfo += soldSeat[i] + ",";
            }
        }
        planseatinfoRepository.updataSoldSeat(newSeatInfo,orderInfo.getName());
    }

    @Override
    public MemberStatistics getUserStatisticsInfo(String email) {
        List<String> reservedList = new ArrayList<>();
        List<String> returnList = new ArrayList<>();
        List<String> consumptionList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        for (int i=1;i<=12;i++){
            String targetDate = "";
            if (i>=1 && i<=9) targetDate += date.substring(0, 4) + "-0" + i;
            else targetDate += date.substring(0, 4) + "-" + i;
            int reservedByMonth = orderinfoRepository.getOrderNumber(email,targetDate,String.valueOf(OrderState.WAIT_CHECK_TICKET))
                                 +orderinfoRepository.getOrderNumber(email,targetDate,String.valueOf(OrderState.HAVE_CHECKED))
                                 +orderinfoRepository.getOrderNumber(email,targetDate,String.valueOf(OrderState.WAIT_ALLOCATE_TICKET));
            int returnByMonth = orderinfoRepository.getOrderNumber(email,targetDate,String.valueOf(OrderState.RETURN_TICKET));
            int consumptionNumber = 0;
            List<OrderinfoEntity> orderinfoEntityList = orderinfoRepository.getUserOrderList(email,targetDate);
            for (int j=0;j<orderinfoEntityList.size();j++){
                consumptionNumber += orderinfoEntityList.get(j).getTotalprice();
            }
            reservedList.add(String.valueOf(reservedByMonth));
            returnList.add(String.valueOf(returnByMonth));
            consumptionList.add(String.valueOf(consumptionNumber));
        }
        MemberStatistics memberStatistics = new MemberStatistics();
        memberStatistics.setReservedNumList(reservedList);
        memberStatistics.setReturnNumList(returnList);
        memberStatistics.setConsumptionList(consumptionList);
        return memberStatistics;
    }

    /**
     * 获取当前计划的售座信息
     * @param name 计划名称
     * @return
     */
    private String[] getNowSeatInfo(String name){
        String[] temp = planseatinfoRepository.getPlanseatinfoEntity(name).getSeatinfo().split(",");
        String[] soldOutSeat = planseatinfoRepository.getPlanseatinfoEntity(name).getSoldseat().split(",");
        for (int i=0;i<soldOutSeat.length;i++){
            int row = Integer.parseInt(soldOutSeat[i].split("_")[0])-1;
            int column = Integer.parseInt(soldOutSeat[i].split("_")[1])-1;
            StringBuffer sb = new StringBuffer(temp[row]);
            sb.replace(column,column+1,"s");//设置为已售出
            temp[row] = sb.toString();
        }
        return temp;
    }
}
