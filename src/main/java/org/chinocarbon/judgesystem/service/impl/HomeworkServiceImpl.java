package org.chinocarbon.judgesystem.service.impl;

import org.chinocarbon.judgesystem.dao.HomeworkDao;
import org.chinocarbon.judgesystem.pojo.Homework;
import org.chinocarbon.judgesystem.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChinoCarbon
 * @since 2022/6/22-9:10 AM
 */
@Service
public class HomeworkServiceImpl implements HomeworkService
{
    HomeworkDao homeworkDao;

    @Autowired
    public void setHomeworkDao(HomeworkDao homeworkDao)
    {
        this.homeworkDao = homeworkDao;
    }

    @Override
    public String add(Homework homework)
    {
        homeworkDao.addHomework(homework);
        for (int problemId: homework.getProblems())
        {
            homeworkDao.addHomeworkProblems(homework.getHomeworkId(), problemId);
        }
        for (int classId: homework.getClasses())
        {
            homeworkDao.addHomeworkClasses(homework.getHomeworkId(), classId);
        }
        return "SUCCESS";
    }
}
