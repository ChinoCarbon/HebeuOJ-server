package org.chinocarbon.judgesystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.chinocarbon.judgesystem.pojo.Problem;
import org.chinocarbon.judgesystem.pojo.SinglePage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/5/13-2:51 PM
 */

@Mapper
public interface ProblemDao extends BaseMapper<Problem>
{
    @Select("select * from HebeuOJ.tbl_problem where problem_title like concat('%', #{arg0}, '%')")
    List<Problem> findSomeByKeyWord(String keyWord);
    @Select("select * from HebeuOJ.tbl_problem where problem_id = #{problemId}")
    Problem getProblemInformation(Problem problem);

    @Update("update HebeuOJ.tbl_problem set problem_submitted = problem_submitted + 1 where problem_id = #{id}")
    void addOne(int id);

    @Update("update HebeuOJ.tbl_problem set problem_passed = problem_passed + 1 where problem_id = #{id}")
    void addPassedOne(int id);
    @Insert("insert into HebeuOJ.tbl_problem (problem_title) value (#{problemTitle})")
    @Options(useGeneratedKeys = true, keyProperty = "problemId")
    int addAProblem(Problem problem);

    @Insert("insert into tbl_user_problem (user_id, problem_id) value (#{userId}, #{problemId})")
    void addAPass(int userId, int problemId);

    @Select("select * from tbl_user_problem where problem_id = #{problemId} and user_id = #{usedId}")
    int isPass(int usedId, int problemId);
}
