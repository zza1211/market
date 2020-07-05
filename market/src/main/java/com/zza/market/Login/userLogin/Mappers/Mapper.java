package com.zza.market.Login.userLogin.Mappers;

import com.zza.market.Login.userLogin.Bean.User;
import com.zza.market.Login.userLogin.Bean.good;

import java.math.BigDecimal;
import java.util.List;

//将sql语句写入接口
public interface Mapper {
    User select(String username);
    User selectByid(int id);
    int insert(String username,String password,String phn);
    List<User> selectall();
    int deleteByid(int id);
    int update(int id,String username, String phn, BigDecimal money);
    int updatemoney(int id, BigDecimal money);
    List<good> selectgoods();
    int insertgood(String name,BigDecimal price,String describe,String imageurl);
}
