package edu.nju.ticketsystem.controller;

import edu.nju.ticketsystem.pojo.*;
import edu.nju.ticketsystem.service.AdminService;
import edu.nju.ticketsystem.tools.VenueState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"user","address","venue"})
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * admin登录界面
     * @return
     */
    @RequestMapping("/adminlogin")
    public String adminLoginView(){
        return "/admin/adminlogin";
    }

    /**
     * 判断管理员账号密码
     * @param username 管理员账号
     * @param password 密码
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Admin adminLogin(@RequestParam("username")String username, @RequestParam("password")String password){
        Admin admin = new Admin();
        admin.setLoginState(adminService.judgeAdmin(username,password));
        return admin;
    }

    /**
     * 管理员审查注册申请界面
     * @return
     */
    @RequestMapping("/admincheckregister")
    public String adminCheckView(){
        return "/admin/admincheckregister";
    }

    /**
     * 保存当前查询场馆地址
     * @param address
     * @param model
     * @return
     */
    @RequestMapping("/get_venueinfo")
    @ResponseBody
    public Venue getVenuInfo(@RequestParam("address")String address,@RequestParam("state")int state, Model model){
        Venue venue = new Venue();
        model.addAttribute("address",address);
//        if (state == 0){
//            venue = adminService.getVenue(address,VenueState.REGISTER_WAITING);
//        }else {
//            venue = adminService.getVenue(address,VenueState.CHANGE_WAITING);
//        }
        return venue;
    }

    /**
     * 显示场馆信息
     * @return
     */
    @RequestMapping("/show_venueinfo")
    @ResponseBody
    public Venue showVenuInfo(@ModelAttribute("address")String address,@RequestParam("state")int state){
        Venue venue = new Venue();
        if (state == 0){
            venue = adminService.getVenue(address,VenueState.REGISTER_WAITING);
        }else{
            venue = adminService.getVenue(address,VenueState.CHANGE_WAITING);
        }
        return venue;
    }

    /**
     * 通过场馆注册
     * @param address 场馆地址
     * @return
     */
    @RequestMapping("/pass_venue_register")
    @ResponseBody
    public Index passVenueRegister(@RequestParam("address")String address){
        Index index = new Index();
        if (adminService.checkRegisterVenue(address, VenueState.PASS)){
            index.setInformation("已通过该场馆申请");
        }else {
            index.setInformation("通过失败");
        }
        return index;
    }

    /**
     * 不通过场馆注册
     * @param address 场馆地址
     * @return
     */
    @RequestMapping("/notpass_venue_register")
    @ResponseBody
    public Index notPassVenueRegister(@RequestParam("address")String address){
        Index index = new Index();
        if (adminService.checkRegisterVenue(address, VenueState.NOT_PASS)){
            index.setInformation("未通过该场馆申请");
        }else {
            index.setInformation("未通过失败");
        }
        return index;
    }

    /**
     * 管理员审查修改申请界面
     * @return
     */
    @RequestMapping("/admincheckchange")
    public String adminCheckChangeView(){
        return "/admin/admincheckchange";
    }

    @RequestMapping("/do_venue_change")
    @ResponseBody
    public Index doChangeVenueInfo(@RequestParam("address")String address,@RequestParam("pass")int pass){
        Index index = new Index();
        if (pass == 1){
            adminService.checkChangeVenue(address,VenueState.PASS);
            index.setInformation("已通过");
        }else {
            adminService.checkChangeVenue(address,VenueState.NOT_PASS);
            index.setInformation("未通过该申请");
        }
        return index;
    }

    /**
     * 场馆注册申请列表界面
     * @return
     */
    @RequestMapping("/adminregisterlist")
    public String adminRegisterListView(){
        return "/admin/adminregisterlist";
    }

    /**
     * 获取对应页面的数据
     * @param start 查询起始
     * @param size 页面数据条数
     * @return
     */
    @RequestMapping("/get_correspond_pageinfo")
    @ResponseBody
    public VenuePage getCorrespondPageInfo(@RequestParam("start")int start,@RequestParam("size")int size,@RequestParam("state")int state){
        VenuePage venuePage = new VenuePage();
        if (state == 0){
            venuePage = adminService.getPageInfo(start,size,VenueState.REGISTER_WAITING);
        }else {
            venuePage = adminService.getPageInfo(start,size,VenueState.CHANGE_WAITING);
        }
        return venuePage;
    }


    /**
     * 场馆修改申请列表界面
     * @return
     */
    @RequestMapping("/adminchangelist")
    public String adminChangeListView(){
        return "/admin/adminchangelist";
    }

    @RequestMapping("/adminsettleaccount")
    public String adminSettleAccountsView(){
        return "/admin/adminsettleaccount";
    }

    /**
     * 获取结算页面信息
     * @param start
     * @param size
     * @return
     */
    @RequestMapping("/get_settle_accountpage")
    @ResponseBody
    public ShowinfoPage adminGetSettleAccountInfo(@RequestParam("start")int start,@RequestParam("size")int size){
        return adminService.getSettleAccountPage(start,size);
    }

    /**
     * 结算利润
     * @param planName 演出名称
     * @param profit 利润
     * @return
     */
    @RequestMapping("/settle_account")
    @ResponseBody
    public Index settleAccount(@RequestParam("name")String planName,@RequestParam("price")int profit){
        adminService.setBusinessProfit(planName,profit);
        Index index = new Index();
        index.setInformation("结算成功");
        return index;
    }

    /**
     * 统计信息界面
     * @return
     */
    @RequestMapping("/ticketstatistics")
    public String ticketStatisticsView(){
        return "/admin/ticketstatistics";
    }

    /**
     * 获取所有场馆
     * @param start
     * @param size
     * @return
     */
    @RequestMapping("/get_venue_page")
    @ResponseBody
    public VenuePage getVenuePage(@RequestParam("start")int start,@RequestParam("size")int size){
        return adminService.getVenuePage(start,size);
    }

    /**
     * 设置当前查询场馆
     * @param address
     * @return
     */
    @RequestMapping("/set_venue_address")
    @ResponseBody
    public Index setVenueAddress(Model model, @RequestParam("address")String address){
        model.addAttribute("venue",adminService.getVenueEntityByAddress(address).getLogincode());
        model.addAttribute("user",adminService.getVenueEntityByAddress(address).getEmail());
        model.addAttribute("address",address);
        return new Index();
    }

    /**
     * 某一场馆的统计界面
     * @return
     */
    @RequestMapping("/onevenuestatistics")
    public String oneVenueStatisticsView(){
        return "/admin/onevenuestatistics";
    }

    /**
     * 会员统计界面
     * @return
     */
    @RequestMapping("/memberstatistics")
    public String memberStatisticsView(){
        return "/admin/memberstatistics";
    }

    /**
     * 获得会员统计信息
     * @return
     */
    @RequestMapping("/get_admin_memberstatistic")
    @ResponseBody
    public MemberStatistics getAdminMemberStatisticInfo(){
        return adminService.getAllMemberStatistics(0);//0表示会员，1表示Tickets
    }

    /**
     * Tickets统计界面
     * @return
     */
    @RequestMapping("/adminstatistic")
    public String adminStatisticView(){
        return "/admin/adminstatistic";
    }

    /**
     * 获取Tickets统计信息
     * @return
     */
    @RequestMapping("/get_tickets_adminstatistic")
    @ResponseBody
    public MemberStatistics getTicketsStatistics(){
        return adminService.getAllMemberStatistics(1);//0表示会员，1表示Tickets
    }
}
