package com.bluestragglers.test;

import com.bluestragglers.dao.IUserDao;
import com.bluestragglers.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MyBatisAnnoTest {
    public static void main(String[] args) throws Exception{
//      1.获取字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//      2.根据字节输入流构建 SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//      3.根据 SqlSessionFactory 生产一个 SqlSession
        SqlSession session = factory.openSession();
//      4.使用 SqlSession 获取 Dao 的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
//      5.执行 Dao 的方法
        List<User> users = userDao.findAll();
//      6.测试过程
        for (User user : users) {
            System.out.println("user = " + user);
        }
//      7.释放资源
        session.close();
        in.close();
    }
}
