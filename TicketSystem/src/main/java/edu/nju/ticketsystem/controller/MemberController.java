package edu.nju.ticketsystem.controller;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;
import edu.nju.ticketsystem.pojo.*;
import edu.nju.ticketsystem.service.MemberService;
import edu.nju.ticketsystem.tools.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
@RequestMapping("/member")
@SessionAttributes({"user","title","seat","price","seatnum","orderType"})
public class MemberController {
    @Autowired
    private MemberService memberService;
    /**
     * 进入会员基本信息修改界面
     * @return
     */
    @RequestMapping("/basicinfo")
    public String memberBasicInfoView(){
        return "/member/basicinfo";
    }

    /**
     * 获取当前用户
     * @param email session值
     * @return
     */
    @RequestMapping("/get_current_user")
    @ResponseBody
    public Index getCurrentUser(@ModelAttribute("user")String email){
        Index index = new Index();
        index.setEmail(email);
        return index;
    }

    /**
     * 页面加载后的初始信息
     * @param email
     * @return
     */
    @RequestMapping("/load_basic_info")
    @ResponseBody
    public BasicInfo loadBasicInfo(@ModelAttribute("user")String email){
        UserbasicinfoEntity u = memberService.getUserBasicInfo(email);
        BasicInfo basicInfo = new BasicInfo(u.getNickname(),u.getSex(),u.getBirthday(),u.getMarriage(),
                u.getEducation(), u.getBussiness(),u.getIncome(),u.getAddress(),u.getBalance());
        return basicInfo;
    }

    /**
     * 保存会员修改的基本信息
     * @param basicInfo 前台传递的修改信息对象
     * @return
     */
    @RequestMapping(value = "/save_basic_info",method = RequestMethod.POST)
    @ResponseBody
    public Index saveBasicInfo(@RequestBody BasicInfo basicInfo,@ModelAttribute("user") String email){
        Index index = new Index();
        if (memberService.saveBasicInfo(basicInfo,email)){
            index.setInformation("修改成功");
        }else {
            index.setInformation("昵称已存在");
        }
        return index;
    }

    /**
     * 进入会员信息查看界面
     * @return
     */
    @RequestMapping("/memberinfo")
    public String memberInfoView(){
        return "/member/memberinfo";
    }

    /**
     * 加载会员信息
     * @param email 当前用户
     * @return 会员信息对象
     */
    @RequestMapping("/load_member_info")
    @ResponseBody
    public Member loadMemberInfo(@ModelAttribute("user")String email){
        MemberEntity memberEntity = memberService.getCurrentMemberInfo(email);
        Member member = new Member(memberEntity.getEmail(),memberEntity.getMembership(),memberEntity.getMemberlevel(),
                memberEntity.getIntegration(),memberEntity.getBenifitA(),memberEntity.getBenifitB(),memberEntity.getBenifitC());
        return member;
    }

    /**
     * 撤销会员资格
     * @param email 当前用户
     * @return
     */
    @RequestMapping("/cancel_membership")
    @ResponseBody
    public Index cancelMembership(@ModelAttribute("user")String email){
        Index index = new Index();
        if (memberService.cancelMembership(email)){
            index.setInformation("撤销成功");
        }else {
            index.setInformation("撤销失败");
        }
        return index;
    }

    /**
     * 用积分兑换优惠券,每次请求兑换一张
     * @param email 当前用户
     * @param level 兑换的优惠券档位
     * @return
     */
    @RequestMapping("/convert_benifits")
    @ResponseBody
    public Index convertBenifits(@ModelAttribute("user")String email, @RequestParam("number")int level){
        Index index = new Index();
        if (memberService.getCurrentMemberInfo(email).getMembership() == (byte)1) {
            if (memberService.convertBenifits(email, level)) {
                index.setInformation("兑换成功");
            } else {
                index.setInformation("积分不够");
            }
        }else{
            index.setInformation("您目前不是会员，无法兑换");
        }
        return index;
    }

    /**
     * 进入修改密码界面
     * @return
     */
    @RequestMapping("/changepassword")
    public String changePasswordView(){
        return "/member/changepassword";
    }

    /**
     * 判断用户修改的密码是否符合要求
     * @param email 当前用户
     * @return 判断信息（密码长度，是否准确）
     */
    @RequestMapping("/judge_password")
    @ResponseBody
    public Index judgePassword(@ModelAttribute("user")String email,@RequestParam("oldpwd") String oldpwd,
                               @RequestParam("newpwd")String newpwd,@RequestParam("cfmpwd")String cfmpwd){
        Index index = new Index();
        if (memberService.checkPassword(email,oldpwd)){
            if (newpwd.length()<6){
                index.setInformation("新密码长度至少6位");
            }else if (!newpwd.equals(cfmpwd)){
                index.setInformation("新密码确认错误");
            }else{
                if (memberService.updatePassword(email,newpwd)){
                    index.setInformation("修改完成");
                }else{
                    index.setInformation("修改失败");
                }
            }
        }else {
            index.setInformation("密码错误");
        }
        return index;
    }

    /**
     * 用户查看订单状态界面
     * @return
     */
    @RequestMapping("/orderinfo")
    public String orderInfoView(){
        return "/member/orderinfo";
    }

    /**
     * 处理待分配订单和过期订单
     * @return
     */
    @RequestMapping("/deal_order")
    @ResponseBody
    public Index dealOrder(){
        memberService.dealOrder();
        return new Index();
    }

    /**
     * 退订
     * @param orderId 订单编号
     * @return
     */
    @RequestMapping("/return_ticket")
    @ResponseBody
    public Index returnTicket(@RequestParam("orderId")int orderId){
        Index index = new Index();
        memberService.returnTicket(orderId);
        index.setInformation("退订成功");
        return index;
    }

    /**
     * 获取对应页面的数据
     * @param start 查询起始
     * @param size 页面数据条数
     * @param state 订单类型 0为等待检票，1为等待配票，2为已经检票，3为失效订单，4为退订
     * @return
     */
    @RequestMapping("/get_checked_tickets")
    @ResponseBody
    public OrderPage getCheckedTicketsInfo(@ModelAttribute("user")String email, @RequestParam("start")int start,@RequestParam("size")int size,@RequestParam("state")int state){
        OrderPage orderPage = new OrderPage();
        if (state == 0){
            orderPage = memberService.getOrderPageInfo(email,start,size,OrderState.WAIT_CHECK_TICKET);
        }else if (state == 1){
            orderPage = memberService.getOrderPageInfo(email,start,size,OrderState.WAIT_ALLOCATE_TICKET);
        }else if (state == 2){
            orderPage = memberService.getOrderPageInfo(email,start,size,OrderState.HAVE_CHECKED);
        }else if (state == 3){
            orderPage = memberService.getOrderPageInfo(email,start,size,OrderState.OUT_DATE_TICKET);
        }else if (state == 4){
            orderPage = memberService.getOrderPageInfo(email,start,size,OrderState.RETURN_TICKET);
        }
        return orderPage;
    }

    /**
     * 选座购买界面
     * @return
     */
    @RequestMapping("/chooseseat")
    public String chooseSeatView(){
        return "/member/chooseseat";
    }

    /**
     * 初始化选座界面
     * @param name 当前演出名称
     * @return
     */
    @RequestMapping("/init_choose_seatinfo")
    @ResponseBody
    public Showinfo initChooseSeatInfo(@ModelAttribute("title")String name){
        return memberService.setInitSeatInfo(name);
    }

    /**
     * 提交选座购买信息
     * @return
     */
    @RequestMapping("/post_choose_seat")
    @ResponseBody
    public Index postChooseSeat(Model model,@RequestParam("chooseSeat")String[] chooseSeat,@RequestParam("seatnum")int seatNum,
                                @RequestParam("totalPrice")String totalPrice,@RequestParam("orderType")int orderType){
        Index index = new Index();
        model.addAttribute("seat",chooseSeat);
        model.addAttribute("seatnum",seatNum);
        model.addAttribute("price",totalPrice);
        model.addAttribute("orderType",orderType);
        return index;
    }

    /**
     * 初始化支付信息
     * @param email 当前用户
     * @param price 支付金额
     * @return
     */
    @RequestMapping("/init_payinfo")
    @ResponseBody
    public Trade initPayInfo(@ModelAttribute("user")String email,@ModelAttribute("price")String price){
        Trade trade = new Trade();
        trade.setEmail(email);
        trade.setPrice(price);
        return trade;
    }

    /**
     * 支付界面
     * @return
     */
    @RequestMapping("/payforticket")
    public String payForTicketView(){
        return "/member/payforticket";
    }

    /**
     * 执行选座购买
     * @param email 当前用户
     * @param price 原价
     * @param chooseSeat 选择座位
     * @param name 演出名称
     * @param password 密码
     * @param benifit 优惠金额
     * @return
     */
    @RequestMapping("/do_payfor_tickets")
    @ResponseBody
    public Index doPayForTickets(@ModelAttribute("user")String email,@ModelAttribute("price")String price,@ModelAttribute("seatnum")int seatnum,
                                 @ModelAttribute("seat")String[] chooseSeat,@ModelAttribute("title")String name,@ModelAttribute("orderType")int orderType,
                                 @RequestParam("password")String password,@RequestParam("benifit")int benifit){
        Index index = new Index();
        if (!memberService.checkPassword(email,password)){
            index.setInformation("密码不正确");
        }else{
            if (!memberService.checkBalance(email,benifit)){
                index.setInformation("您尚未兑换所选优惠券");
            }else if (!memberService.checkBalance(email,price,benifit)){
                index.setInformation("余额不足");
            }else {
                if (orderType == 1) {
                    memberService.generateOrder(email, price, name, benifit, chooseSeat);
                    memberService.updateMemberInfo(email, price, benifit);
                    memberService.updateUserBasicInfo(email, price, benifit);
                    memberService.updateSoldSeat(name, chooseSeat);
                    index.setInformation("支付成功");
                }else {
                    if (memberService.generateOrder(email, price, name, benifit, seatnum)){
                        memberService.updateMemberInfo(email, price, benifit);
                        memberService.updateUserBasicInfo(email, price, benifit);
                        index.setInformation("支付成功，请查看订单状态关注座位分配信息");
                    }else {
                        index.setInformation("当前剩余座位少于您所订购数量");
                    }
                }
            }
        }
        return index;
    }


    /**
     * 统计信息界面
     * @return
     */
    @RequestMapping("/statistics")
    public String statisticsView(){
        return "/member/statistics";
    }

    @RequestMapping("/get_statisticsinfo")
    @ResponseBody
    public MemberStatistics getStatisticsInfo(@ModelAttribute("user")String email){
        return memberService.getUserStatisticsInfo(email);
    }

}
