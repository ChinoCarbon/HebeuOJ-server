package org.chinocarbon.judgesystem.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.chinocarbon.judgesystem.pojo.User;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/5/11-9:48 AM
 */
@Mapper
public interface UserDao
{
    @Select("select * from HebeuOJ.tbl_user where user_id = #{id}")
    User selectUserById(int id);
    @Select("select * from HebeuOJ.tbl_user where user_class = #{id}")
    List<User> selectUserByClassId(int id);
    @Select("select * from HebeuOJ.tbl_user where user_name = #{username}")
    User selectUserByUserName(String username);
    @Select("select * from HebeuOJ.tbl_user where user_email = #{email}")
    User selectUserByEmail(String email);
    @Select("select * from HebeuOJ.tbl_user where user_name = #{userName} and user_password = #{userPassword}")
    User selectUserByUserNameAndPassword(User user);
    @Insert("insert into HebeuOJ.tbl_user (user_name, user_password, user_email, user_class) \n" +
            "        value (#{userName}, #{userPassword}, #{userEmail}, 4)")
    int insertAUser(User user);
    @Update("update tbl_user set user_submission = user_submission + 1 where user_id = #{userId}")
    void addOne(int userId);
    @Update("update tbl_user set user_passed = user_passed + 1 where user_id = #{userId}")
    void addPassOne(int userId);
}
