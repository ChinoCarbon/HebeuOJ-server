package org.chinocarbon.judgesystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.chinocarbon.judgesystem.pojo.Classes;
import org.chinocarbon.judgesystem.pojo.User;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/6/21-7:40 PM
 */

@Mapper
public interface ClassDao extends BaseMapper<Classes>
{
    @Select("select * from tbl_class where class_teacher = #{userId}")
    @Results({
            @Result(id=true ,property = "classId", column = "class_id"),
            @Result(property = "className", column = "class_name"),
            @Result(property = "classTeacher", column = "class_teacher"),
            @Result(
                    property = "students",
                    column = "class_id", //根据user表中的id来查询 此用户所拥有的订单信息
                    javaType = List.class, //返回此用户所拥有订单的List集合类型
                    many = @Many(select = "org.chinocarbon.judgesystem.dao.UserDao.selectUserByClassId") //根据user数据表中id 在orders表中来查询此订单
            )
    })
    List<Classes> listAllClasses(int userId);

    @Select("select * from tbl_class inner join tbl_homework_class on tbl_class.class_id = tbl_homework_class.class_id where homework_id = #{homeworkId}")
    List<Classes> getClassesByHomeworkId(int homeworkId);


}
