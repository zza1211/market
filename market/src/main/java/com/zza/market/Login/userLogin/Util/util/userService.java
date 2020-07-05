package com.zza.market.Login.userLogin.Util.util;

import com.zza.market.Login.userLogin.Bean.User;
import com.zza.market.Login.userLogin.Bean.good;
import com.zza.market.Login.userLogin.Mappers.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

//实现mybatis的接口
@Service
public class userService extends util {
    //根据用户名查找用户
    public User select(String username) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        User user = usermapper.select(username);
        return user;
    }
    //根据id查找用户
    public User selectByid(int id) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        User user = usermapper.selectByid(id);
        return user;
    }
    //插入用户
    public int insert(String username,String password,String phn) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        int result = usermapper.insert(username,password,phn);
        sqlSession.commit();
        return result;
    }
    //查找所有用户
    public List<User> selectall() throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        return usermapper.selectall();
    }
    //根据id删除用户
    public int delete(int id) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        int result = usermapper.deleteByid(id);
        sqlSession.commit();
        return result;
    }
    //跟新用户
    public int update(int id, String username, String phn, BigDecimal money) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        int result = usermapper.update(id,username,phn,money);
        sqlSession.commit();
        return result;
    }
    //查询所有商品
    public List<good> selectgoods() throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        return usermapper.selectgoods();
    }
    //新增商品
    public int insertgood(String name,BigDecimal price,String describe,String imageurl) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        int result = usermapper.insertgood(name,price,describe,imageurl);
        sqlSession.commit();
        return result;
    }
    //更新余额
    public int updatemoney(int id, BigDecimal money) throws IOException {
        SqlSession sqlSession = this.getSession();
        Mapper usermapper = sqlSession.getMapper(Mapper.class);
        int result = usermapper.updatemoney(id,money);
        sqlSession.commit();
        return result;
    }
}
