package com.zza.market.Login.userLogin.admin;

import com.zza.market.Login.userLogin.Bean.User;
import com.zza.market.Login.userLogin.Bean.good;
import com.zza.market.Login.userLogin.Util.util.userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

//处理管理员登录请求

@Controller
public class adminController {
    //管理员登录
    @PostMapping(value = "/admin/login")
    public String adminlogin(@RequestParam("password") String password, HttpSession session){
        if (password.equals("1250232078")){
            session.setAttribute("admin","exist");
            return "redirect:/admin.html";
        }else {
            session.setAttribute("msg","密码错误");
            return "redirect:/adminlogin.html";
        }
    }

    ///adminlogin.html跳转到登录界面
    @RequestMapping("/adminlogin.html")
    public String toadminlogin(){
        return "adminlogin";
    }


    //跳转到管理员界面
    @RequestMapping("/admin.html")
    public String toadmin(HttpSession session) throws IOException {
        if (session.getAttribute("admin").equals("exist")){
            //获取用户信息列表传入session
            List<User> users = new userService().selectall();
            session.setAttribute("users",users);
            //查询所有商品放到session中
            List<good> goods=new userService().selectgoods();
            session.setAttribute("goods",goods);
            return "admin";
        }
        return "redirect:/adminlogin.html";
    }

    //删除用户
    @RequestMapping("/delete/{id}")
    //删除的请求命令中有需要删除的用户id
    public String delete(@PathVariable("id") int id,HttpSession session) throws IOException {
        //登录了才能删除
        if (session.getAttribute("admin").equals("exist")){
            new userService().delete(id);
            return "redirect:/admin.html";
        }
        return "redirect:/adminlogin.html";
    }

    //点击修改按钮跳转到修改界面
    @RequestMapping("/update/{id}")
    public String toUpdate(@PathVariable("id") int id,HttpSession session) throws IOException {
        if (session.getAttribute("admin").equals("exist")) {
            User user = new userService().selectByid(id);
            session.setAttribute("changeuser", user);
            return "updateuser";
        }
        return "redirect:/adminlogin.html";
    }

    //点击确认修改，修改用户信息
    //若不输入数据则该参数不修改
    @RequestMapping("/admin/update")
    public String update(@RequestParam("username") String username, @RequestParam("phn") String phn, @RequestParam("money") BigDecimal money, HttpSession session) throws IOException {
        if (session.getAttribute("admin").equals("exist")) {
            User user = (User) session.getAttribute("changeuser");
            if (username == "") {
                username = user.getUsername();
            }
            if (phn == "") {
                phn = user.getPhn();
            }
            if (money == null) {
                money = user.getMoney();
            }
            new userService().update(user.getId(), username, phn, money);
            return "redirect:/admin.html";
        }
        return "redirect:/adminlogin.html";
    }


    //跳转到上传商品页面
    @RequestMapping("/upload")
    public String touploadpage(HttpSession session){
        //管理员登录了才能访问
        if (session.getAttribute("admin").equals("exist")) {
            return "upload";
        }
        return "redirect:/adminlogin.html";
    }

    //点击确认上传上传商品
    @RequestMapping("/admin/upload")
    public String upload(@RequestParam("name")String name,@RequestParam("describe")String describe,@RequestParam("price")BigDecimal price,@RequestParam("imageurl")String imageurl) throws IOException {
        new userService().insertgood(name,price,describe,imageurl);
        return "redirect:/admin.html";
    }
}
