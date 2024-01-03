package org.chinocarbon.judgesystem.controller;

import org.chinocarbon.judgesystem.dao.ClassDao;
import org.chinocarbon.judgesystem.pojo.Classes;
import org.chinocarbon.judgesystem.pojo.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/6/21-7:37 PM
 */

@RestController
public class ClassController
{
    ClassDao classDao;

    @Autowired
    public void setClassDao(ClassDao classDao)
    {
        this.classDao = classDao;
    }

    @RequestMapping("listClasses")
    public List<Classes> listClasses(@RequestParam int userId)
    {
        return classDao.listAllClasses(userId);
    }

    @RequestMapping("getClassesByHomeworkId")
    public List<Classes> getClassesByHomeworkId(@RequestBody Homework homework)
    {
        return classDao.getClassesByHomeworkId(homework.getHomeworkId());
    }
}
