package edu.nju.ticketsystem.controller;

import edu.nju.ticketsystem.model.PlanEntity;
import edu.nju.ticketsystem.pojo.*;
import edu.nju.ticketsystem.service.MemberService;
import edu.nju.ticketsystem.service.VenueService;
import edu.nju.ticketsystem.tools.VenueState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
@RequestMapping("/venue")
@SessionAttributes({"user","venue","title","address"})
public class VenueController {
    @Autowired
    private VenueService venueService;
    @Autowired
    private MemberService memberService;
    /**
     * 场馆注册界面
     * @return
     */
    @RequestMapping("/venueregister")
    public String venueRegisterView(){
        return "/venue/venueregister";
    }

    /**
     * 获取当前场馆
     * @return
     */
    @RequestMapping("/get_now_venue")
    @ResponseBody
    public Index getNowVenue(@ModelAttribute("address")String address){
        Index index = new Index();
        index.setInformation(address);
        return index;
    }

    /**
     * 场馆注册
     * @param email 当前用户
     * @param venue 注册信息对象
     * @return
     */
    @RequestMapping("/do_venue_register")
    @ResponseBody
    public Index venueRegister(@ModelAttribute("user")String email, @RequestBody Venue venue){
        Index index = new Index();
        if (venueService.addRegisterWaitVenue(email, venue, VenueState.REGISTER_WAITING)) {
            index.setInformation("提交成功，等待管理员审核通过，注意邮件查收登陆码");
        }else {
            index.setInformation("场馆地点已存在，重新输入");
        }
        return index;
    }

    /**
     * 场馆登录界面
     * @return
     */
    @RequestMapping("/venuelogin")
    public String venueLoginView(){
        return "/venue/venuelogin";
    }

    @RequestMapping("/do_venue_login")
    @ResponseBody
    public Index venueLogin(Model model, @ModelAttribute("user")String email, @RequestParam("logincode")String loginCode, @RequestParam("password")String password){
        Index index = new Index();
        index.setInformation(venueService.checkLogin(email,loginCode,password));
        if ("登录成功".equals(venueService.checkLogin(email,loginCode,password))){
            model.addAttribute("venue",loginCode);
        }
        return index;
    }

    /**
     * 场馆信息界面
     * @return
     */
    @RequestMapping("/venueinfo")
    public String venueInfoView(){
        return "/venue/venueinfo";
    }

    /**
     * 获取场馆信息
     * @param email 当前用户
     * @param loginCode 登录码
     * @return
     */
    @RequestMapping("/get_venueinfo")
    @ResponseBody
    public Venue initVenueInfo(@ModelAttribute("user")String email,@ModelAttribute("venue")String loginCode){
        return venueService.getVenueInfo(email,loginCode);
    }

    /**
     * 请求修改场馆信息
     * @param email 当前用户
     * @param loginCode 登陆码
     * @param venue 场馆信息
     * @return
     */
    @RequestMapping("/ask_to_changeinfo")
    @ResponseBody
    public Index askToChangeInfo(@ModelAttribute("user")String email,@ModelAttribute("venue")String loginCode,@RequestBody Venue venue){
        Index index = new Index();
        index.setInformation(venueService.askToChangeInfo(email,loginCode,venue));
        return index;
    }

    /**
     * 场馆发布计划界面
     * @return
     */
    @RequestMapping("/venuepublish")
    public String venuePublishView(){
        return "/venue/venue_publish";
    }

    @RequestMapping("/publish_venue_plan")
    @ResponseBody
    public Index publishVenuePlan(@ModelAttribute("user")String email,@ModelAttribute("venue")String loginCode, @RequestBody Plan plan){
        Index index = new Index();
        String address = venueService.getVenueInfo(email,loginCode).getAddress();
        index.setInformation(venueService.publishPlan(address,plan));
        return index;
    }

    /**
     * 现场购票检票界面
     * @return
     */
    @RequestMapping("/venueticketlist")
    public String venueTicketsListView(){
        return "/venue/venueticketlist";
    }

    /**
     * 获取当前场馆的所有演出计划
     * @param email 当前用户
     * @param loginCode 登录码
     * @return
     */
    @RequestMapping("/get_planinfo_byaddress")
    @ResponseBody
    public PlanPage getPlanInfoByAddress(@RequestParam("start")int start, @RequestParam("size")int size,
                                         @ModelAttribute("user")String email, @ModelAttribute("venue")String loginCode){
        return venueService.getVenueAllPlan(start,size,email, loginCode);
    }

//    @RequestMapping("/get_plan_nowseatinfo")
//    @ResponseBody
//    public Showinfo getPlanNowSeatInfo(@ModelAttribute("title") String name){
//        System.out.println(name);
//        if (name != null){
//            return memberService.setInitSeatInfo(name);
//        }
//        return new Showinfo();
//    }

    @RequestMapping("/set_planname")
    @ResponseBody
    public Index setPlanName(Model model,@RequestParam("planName")String planNmae){
        model.addAttribute("title",planNmae);
        return new Index();
    }

    /**
     * 现场售票界面
     * @return
     */
    @RequestMapping("/venuesellticket")
    public String venueSellTicketsView(){
        return "/venue/venuesellticket";
    }

    /**
     * 现场检票界面
     * @return
     */
    @RequestMapping("/venuecheckticket")
    public String venueCheckTicketsView(){
        return "/venue/venuecheckticket";
    }

    @RequestMapping("/deal_scene_sell")
    @ResponseBody
    public Index dealSceneSellTicket(@ModelAttribute("title")String name,@RequestParam("chooseSeat")String[] chooseSeat,
                                        @RequestParam("totalPrice")int totalPrice,@RequestParam("type")int type){
        Index index = new Index();
        if (type == 1) {
            venueService.generateSceneOrder(name, chooseSeat, totalPrice);
            memberService.updateSoldSeat(name, chooseSeat);
            index.setInformation("购买成功");
        }else {
            venueService.checkSecenOrder(name,chooseSeat);
            memberService.updateCheckSeat(name,chooseSeat);
            index.setInformation("检票成功");
        }
        return index;
    }


    /**
     * 场馆统计信息界面
     * @return
     */
    @RequestMapping("/venuestatistics")
    public String venueStatisticsView(){
        return "/venue/venuestatistics";
    }

    @RequestMapping("/get_venue_statistics")
    @ResponseBody
    public MemberStatistics getVenueStatistics(@ModelAttribute("user")String email,@ModelAttribute("venue")String loginCode){
        return venueService.getVenueStatistics(email,loginCode);
    }
}
