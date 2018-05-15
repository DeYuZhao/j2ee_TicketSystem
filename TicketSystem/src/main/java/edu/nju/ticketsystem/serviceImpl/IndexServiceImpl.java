package edu.nju.ticketsystem.serviceImpl;

import edu.nju.ticketsystem.model.MemberEntity;
import edu.nju.ticketsystem.model.UserbasicinfoEntity;
import edu.nju.ticketsystem.repository.MemberRepository;
import edu.nju.ticketsystem.repository.UserbasicinfoRepository;
import edu.nju.ticketsystem.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserbasicinfoRepository userbasicinfoRepository;
    /**
     * 判断邮箱是否已经存在
     * @param email 注册时填写的邮箱
     * @return TRUE 存在 or FALSE 不存在
     */
    @Override
    public boolean findEmail(String email) {
        boolean result = true;
        if (memberRepository.findMember(email) == null){
            result = false;
        }
        return result;
    }

    /**
     * 将注册用户信息保存到数据库
     * @param memberEntity
     */
    @Override
    public void addRegisterUser(MemberEntity memberEntity, UserbasicinfoEntity userbasicinfoEntity) {
        memberRepository.save(memberEntity);
        userbasicinfoRepository.save(userbasicinfoEntity);
    }

    /**
     * 登录时判断邮箱和密码
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    @Override
    public String login(String email, String password) {
        String result = "";
        if (memberRepository.findMember(email) == null){
            result = "该用户不存在";
        }else{
            if (password.equals(memberRepository.findMember(email).getPassword())){
                result = "登录成功";
            }else {
                result = "密码错误";
            }
        }
        return result;
    }
}
