package com.zza.market.Login.userLogin.Bean;

import java.math.BigDecimal;

public class User {
    private int id;//用户id
    private String username;//用户名
    private String password;//密码
    private String phonenumber;//电话号码
    private BigDecimal money; // 余额(每个人初始有5000余额)

    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhn() {
        return phonenumber;
    }

    public void setPhn(String phn) {
        this.phonenumber = phn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phn='" + phonenumber + '\'' +
                ", money=" + money +
                '}';
    }
}
