package org.chinocarbon.judgesystem.controller;

import org.chinocarbon.judgesystem.dao.HomeworkDao;
import org.chinocarbon.judgesystem.pojo.Homework;
import org.chinocarbon.judgesystem.pojo.Problem;
import org.chinocarbon.judgesystem.pojo.User;
import org.chinocarbon.judgesystem.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/6/22-8:52 AM
 */

@RestController
public class HomeworkController
{
    HomeworkService homeworkService;

    HomeworkDao homeworkDao;

    @Autowired
    @Qualifier("homeworkServiceImpl")
    public void setHomeworkService(HomeworkService homeworkService)
    {
        this.homeworkService = homeworkService;
    }

    @Autowired
    public void setHomeworkDao(HomeworkDao homeworkDao)
    {
        this.homeworkDao = homeworkDao;
    }

    @RequestMapping("addHomework")
    public String addHomework(@RequestBody Homework homework)
    {
        return homeworkService.add(homework);
    }

    @RequestMapping("getHomeworkByTeacherId")
    public List<Homework> getHomeworkByTeacherId(@RequestBody Homework homework)
    {
        return homeworkDao.selectHomeworkByTeacherId(homework.getHomeworkTeacher());
    }

    @RequestMapping("getHomeworkByClassId")
    public List<Homework> getHomeworkByClassId(@RequestBody User user)
    {
        return homeworkDao.selectHomeworkByClassId(user.getUserClass());
    }

    @RequestMapping("getProblemsByHomeworkId")
    public List<Problem> getProblemsByHomeworkId(@RequestBody Homework homework)
    {
        return homeworkDao.getProblemsByHomeworkId(homework.getHomeworkId());
    }
}
