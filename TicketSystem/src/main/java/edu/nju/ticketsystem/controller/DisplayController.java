package edu.nju.ticketsystem.controller;

import edu.nju.ticketsystem.pojo.Index;
import edu.nju.ticketsystem.pojo.Plan;
import edu.nju.ticketsystem.pojo.Showinfo;
import edu.nju.ticketsystem.service.DisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/display")
@SessionAttributes({"title"})
public class DisplayController {
    @Autowired
    private DisplayService displayService;
    /**
     * 用户登录后界面
     * @return jsp所在目录
     */
    @RequestMapping(value = "/firstpage")
    public String firstPageView(){
        return "/display/firstpage";
    }

    /**
     * 初始化首页信息
     * @return
     */
    @RequestMapping("/init_firstpage")
    @ResponseBody
    public List<Plan> initFirstPage(){
        return displayService.getInitialInfo();
    }

    /**
     * 查看票务详情
     * @return
     */
    @RequestMapping(value = "/showinfo")
    public String showinfoView(){
        return "/display/showinfo";
    }

    /**
     * 保存当前访问的演出名称
     * @param model 模型类
     * @param title 演出名称
     * @return
     */
    @RequestMapping("/set_visit_title")
    @ResponseBody
    public Index setVisitTitle(Model model, @RequestParam("title")String title){
        model.addAttribute("title",title);
        return new Index();
    }

    /**
     * 获取演出信息
     * @param title 演出名称
     * @return
     */
    @RequestMapping("/get_showinfo")
    @ResponseBody
    public Showinfo getShowInfo(@ModelAttribute("title")String title){
        return displayService.getShowInfo(title);
    }
}
