package com.bluestragglers.dao;

import com.bluestragglers.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("INSERT INTO user(username, address, sex, birthday) VALUES (#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("UPDATE user SET username = #{username}, address = #{address}, sex = #{sex}, birthday = #{birthday} WHERE id = #{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param user
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(User user);

    /**
     * 根据 ID 查询用户
     * @param userId
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
//    @Select("SELECT * FROM user WHERE username like #{username}")
    @Select("SELECT * FROM user WHERE username like '%${value}%'")
    List<User> findUserByName(String username);

    /**
     * 查询总用户数量
     * @return
     */
    @Select("SELECT count(*) from user")
    int findTotalUser();
}