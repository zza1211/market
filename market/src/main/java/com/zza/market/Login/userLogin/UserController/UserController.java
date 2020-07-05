package com.zza.market.Login.userLogin.UserController;

import com.zza.market.Login.userLogin.Bean.User;
import com.zza.market.Login.userLogin.Bean.good;
import com.zza.market.Login.userLogin.Util.util.userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


/*
* 将html文件放在templates文件夹中无法直接通过地址栏访问，需要用return方法
* 地址栏直接访问的默认是static文件夹下的文件
* */
@Controller
public class UserController {
    //跳转登录页面
    @RequestMapping("/")
    public String toologin(){
        return "login";
    }
    //跳转登录页面
    @RequestMapping("/login.html")
    public String toLogin(){
        return "login";
    }

    //注册完成之后清空msg内容，返回登录页面
    @RequestMapping("/elogin.html")
    public String etoLogin(HttpSession session){
        session.setAttribute("msg",null);
        return "login";
    }

    //在密码错误时点击去注册先清理msg
    @RequestMapping("/eregist.html")
    public String etoRegister(HttpSession session){
        session.setAttribute("msg",null);
        return "regist";
    }

    //跳转到注册界面
    @RequestMapping("/regist.html")
    public String toRegister(){
        return "regist";
    }

    //登录成功
    //避免了未登录用户直接访问除登录注册页面外的其他资源
    @RequestMapping("/successful.html")
    public String toSuccess(HttpSession session) throws IOException {
        User user= (User) session.getAttribute("user");
        if(user==null){
            session.setAttribute("msg","您尚未登录，请先登录");
            return "redirect:/login.html";
        }
        //查询所有商品放到session中
        List<good> goods=new userService().selectgoods();
        session.setAttribute("goods",goods);
        //查询数据库中用户余额
        User user1=new userService().selectByid(user.getId());
        session.setAttribute("money",user1.getMoney());
        return "successful";
    }

    //退出
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        //退出时清空session
        session.setAttribute("msg",null);
        session.setAttribute("user",null);
        return "redirect:/login.html";
    }

    //登录
    @PostMapping(value="/user/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session) throws IOException {
        User user = new userService().select(username);
        if (session.getAttribute("user")!=null){
            session.setAttribute("msg","当前客户端已有登录用户,请先退出");
            return "redirect:/login.html";
        }
        else if(user==null){
            session.setAttribute("msg","你尚未注册,请先注册");
            return "redirect:/regist.html";
        }else if(!user.getPassword().equals(password)){
            session.setAttribute("msg","密码错误");
            return "redirect:/login.html";
        }else{
            session.setAttribute("user",user);
            return "redirect:/successful.html";
        }
    }

    //注册
    @PostMapping(value = "/user/regist")
    public String regist(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phn") String phn, HttpSession session) throws IOException {
        int result =new userService().insert(username,password,phn);
        //注册完成之后清除msg
        session.setAttribute("msg",null);
        return "redirect:/login.html";
    }


    //购买商品
    @RequestMapping("/buy/{id}")
    public String buy(@PathVariable("id")int id,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return "redirect:/login.html";
        }
        List<good> goods=(List<good>) session.getAttribute("goods");

        for (good good:goods){
            if (good.getId()==id){
                //将现有余额和购买后余额存入session
                BigDecimal money= (BigDecimal) session.getAttribute("money");
                BigDecimal newmoney = user.getMoney();
                newmoney = newmoney.subtract(good.getPrice());
                session.setAttribute("newmoney",newmoney);
                user.setMoney(newmoney);
                session.setAttribute("user",user);
            }
        }
        return "redirect:/successful.html";
    }


    //点击确认购买后跟新数据和session中的user
    @RequestMapping("/surebuy")
    public String sure(HttpSession session) throws IOException {
        User user=(User)session.getAttribute("user");
        if (user==null){
            return "redirect:/login.html";
        }
        else {
            new userService().updatemoney(user.getId(),(BigDecimal) session.getAttribute("newmoney"));
            User newuser = new userService().selectByid(user.getId());
            session.setAttribute("user",newuser);
            return "redirect:/successful.html";
        }
    }
}
