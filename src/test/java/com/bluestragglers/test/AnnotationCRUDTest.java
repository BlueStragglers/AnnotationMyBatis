package com.bluestragglers.test;

import com.bluestragglers.dao.IUserDao;
import com.bluestragglers.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSaveUser() {
//        User user = userDao.findById(49);
//        System.out.println("user = " + user);
        User user = new User();
        user.setUsername("MyBatis Annotation");
        user.setAddress("北京市海淀区");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.saveUser(user);
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findUserByName("MyBatis");
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void findTotalCount() {
        int total = userDao.findTotalUser();
        System.out.println("total = " + total);
    }
}
