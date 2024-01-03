package org.chinocarbon.judgesystem.dao;

import org.apache.ibatis.annotations.*;
import org.chinocarbon.judgesystem.pojo.Homework;
import org.chinocarbon.judgesystem.pojo.Problem;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/6/22-9:02 AM
 */
@Mapper
public interface HomeworkDao
{
    @Insert("insert into tbl_homework (homework_teacher, homework_title) VALUE (#{homeworkTeacher}, #{homeworkTitle})")
    @Options(useGeneratedKeys = true, keyProperty = "homeworkId")
    void addHomework(Homework homework);

    @Insert("insert into tbl_homework_problem (homework_id, problem_id) value (#{homeworkId}, #{problemId})")
    void addHomeworkProblems(int homeworkId, int problemId);

    @Insert("insert into tbl_homework_class (homework_id, class_id) value (#{homeworkId}, #{classId})")
    void addHomeworkClasses(int homeworkId, int classId);

    @Select("select * from tbl_homework where homework_teacher = #{teacherId}")
    List<Homework> selectHomeworkByTeacherId(int teacherId);

    @Select("select * from tbl_homework inner join tbl_homework_class on tbl_homework.homework_id = tbl_homework_class.homework_id where class_id = #{classId}")
    List<Homework> selectHomeworkByClassId(int classId);

    @Select("select * from tbl_problem inner join tbl_homework_problem on tbl_homework_problem.problem_id = tbl_problem.problem_id where homework_id = #{homeworkId}")
    List<Problem> getProblemsByHomeworkId(int homeworkId);
}
