package org.chinocarbon.judgesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.chinocarbon.judgesystem.dao.ProblemDao;
import org.chinocarbon.judgesystem.pojo.SinglePage;
import org.chinocarbon.judgesystem.pojo.Problem;
import org.chinocarbon.judgesystem.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/5/13-2:49 PM
 */
@Service
public class ProblemServiceImpl implements ProblemService
{
    private ProblemDao problemDao;

    @Autowired
    public void setProblemDao(ProblemDao problemDao)
    {
        this.problemDao = problemDao;
    }

    @Override
    public PageInfo<Problem> findAllByPages(SinglePage singlePage)
    {
        PageHelper.startPage(singlePage.getPage(), singlePage.getNumPerPage());
        List<Problem> list = problemDao.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Problem> findSomeByKeyWord(SinglePage singlePage)
    {
        PageHelper.startPage(singlePage.getPage(), singlePage.getNumPerPage());
        LambdaQueryWrapper<Problem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Problem::getProblemTitle, singlePage.getKeyWord());
        List<Problem> list = problemDao.selectList(queryWrapper);
         return new PageInfo<>(list);
    }

    @Override
    public int createAProblem(Problem problem)
    {
        String f = problem.getProblemContent();
        problem.setProblemContent("");
        int s = problemDao.insert(problem);
        problem.setProblemContent(f);
        return s;
    }

    @Override
    public boolean isPass(int userId, int problemId)
    {
        try
        {
            System.out.println(problemDao.isPass(userId, problemId));
            return true;
        } catch (Exception e)
        {
            return false;
        }

    }
}
