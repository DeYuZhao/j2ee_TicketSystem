package edu.nju.ticketsystem.controller;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;
import edu.nju.ticketsystem.pojo.Index;
import edu.nju.ticketsystem.service.IndexService;
import edu.nju.ticketsystem.tools.MailTools;
import edu.nju.ticketsystem.tools.UserRegisterDefaultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/28.
 * 用户控制初始界面和、户登录注册和管理员登录
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"user"})
public class IndexController {
    @Autowired
    private IndexService indexService;
    private Index index;
    private Index loginIndex;
    /**
     * 首页
     * @return jsp所在目录
     */
    @RequestMapping(value = "")
    public String indexView(){
        return "/index/index";
    }
    /**
     * 用户登录界面
     * @return jsp所在目录
     */
    @RequestMapping(value = "user_login")
    public String userLoginView(){
        return "/index/userLogin";
    }
    /**
     * 用户注册界面
     * @return jsp所在目录
     */
    @RequestMapping(value = "user_register")
    public String userRegisterView(){
        return "/index/userRegister";
    }

    /**
     * 发送注册邮箱验证码
     * @param email 收件人邮箱
     * @return
     * @throws MessagingException
     */
    @RequestMapping(value = "sendMail", method = RequestMethod.POST)
    @ResponseBody
    public Index sendVerificationCode(@RequestParam("email") String email)throws MessagingException {
        index = new Index();
        String regex1 = "\\w+\\x40\\w+\\x2e\\w+";
        String regex2 = "\\w+\\x40\\w+\\x2e\\w+\\x2e\\w+";
        String regex3 = "\\w+\\x40\\w+\\x2e\\w+\\x2e\\w+\\x2e\\w+";
        if (email.matches(regex1) || email.matches(regex2) || email.matches(regex3)){
            //此处需要判断输入邮箱是否已经存在
            if (!indexService.findEmail(email)) {
                MailTools mailTools = MailTools.getInstance();
                String code = String.valueOf((int) (Math.random() + 9) * 99999);
                mailTools.sendMail(email, code);
                index.setEmail(email);
                index.setInformation("验证码已发送");
                index.setCode(code);
            }else {
                index.setInformation("邮箱已存在");
            }
        }else {
            index.setInformation("邮箱格式不正确");
        }
        return index;
    }

    /**
     * 注册功能
     * @param email 邮箱
     * @param password 密码
     * @param confirmpassword 确认密码
     * @param code 验证码
     * @return
     */
    @RequestMapping(value = "executeRegister", method = RequestMethod.POST)
    @ResponseBody
    public Index executeRegister(Model model, @RequestParam("email") String email, @RequestParam("password") String password,
                                 @RequestParam("confirmpassword") String confirmpassword, @RequestParam("code") String code){
        if (password.length()<6){
            index.setInformation("密码不得少于6位");
        }else if (!password.equals(confirmpassword)){
            index.setInformation("两次密码输入不一致");
        }else if (!code.equals(index.getCode())){
            index.setInformation("验证码错误");
        }else{
            index.setInformation("注册成功");
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setEmail(email);
            memberEntity.setPassword(password);
            memberEntity.setNickname(email);
            UserRegisterDefaultInfo.getInstance().setMemberDefaultInfo(memberEntity);
            UserbasicinfoEntity userbasicinfoEntity = new UserbasicinfoEntity();
            userbasicinfoEntity.setEmail(email);
            userbasicinfoEntity.setNickname(email);
            UserRegisterDefaultInfo.getInstance().setBasicDefaultInfo(userbasicinfoEntity);
            indexService.addRegisterUser(memberEntity,userbasicinfoEntity);
            model.addAttribute("user",email);
            index.setHref("/display/firstpage");
        }
        return index;
    }

    /**
     * 发送登录邮箱验证码
     * @param email 收件人邮箱
     * @return
     * @throws MessagingException
     */
    @RequestMapping(value = "sendLoginMail", method = RequestMethod.POST)
    @ResponseBody
    public Index sendLoginVerificationCode(@RequestParam("email") String email)throws MessagingException {
        loginIndex = new Index();
        String regex1 = "\\w+\\x40\\w+\\x2e\\w+";
        String regex2 = "\\w+\\x40\\w+\\x2e\\w+\\x2e\\w+";
        String regex3 = "\\w+\\x40\\w+\\x2e\\w+\\x2e\\w+\\x2e\\w+";
        if (email.matches(regex1) || email.matches(regex2) || email.matches(regex3)){
            //此处需要判断输入邮箱是否已经存在
            if (indexService.findEmail(email)) {
                MailTools mailTools = MailTools.getInstance();
                String code = String.valueOf((int) (Math.random()+10) * 99999);
                mailTools.sendMail(email, code);
                loginIndex.setEmail(email);
                loginIndex.setInformation("验证码已发送");
                loginIndex.setCode(code);
            }else {
                loginIndex.setInformation("邮箱不存在");
            }
        }else {
            loginIndex.setInformation("邮箱格式不正确");
        }
        return loginIndex;
    }

    /**
     * 登录功能
     * @param email 邮箱
     * @param password 密码
     * @param code 验证码
     * @return
     */
    @RequestMapping(value = "executeLogin",method = RequestMethod.POST)
    @ResponseBody
    public Index executeLogin(Model model, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("code") String code){
        String result = indexService.login(email,password);
        loginIndex.setInformation(result);
        if ("登录成功".equals(result)){
            if (code.equals(loginIndex.getCode())) {
                loginIndex.setHref("/display/firstpage");
            }else {
                loginIndex.setInformation("验证码错误");
            }
        }
        model.addAttribute("user",email);
        return loginIndex;
    }

}
