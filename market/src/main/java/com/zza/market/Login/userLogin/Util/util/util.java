package com.zza.market.Login.userLogin.Util.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;



public abstract class util {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        // mybatis配置文件，这个地方的root地址为：resources，路径要对。
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     * 获取SqlSession
     *
     * @return
     * @throws IOException
     */
    public SqlSession getSession() throws IOException {
        return this.getSqlSessionFactory().openSession();
    }

    /**
     * 关闭当前Session
     *
     * @throws IOException
     */
    public void close() throws IOException {
        this.getSession().close();
    }
}
