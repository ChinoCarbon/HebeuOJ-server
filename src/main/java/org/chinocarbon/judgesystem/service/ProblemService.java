package org.chinocarbon.judgesystem.service;

import com.github.pagehelper.PageInfo;
import org.chinocarbon.judgesystem.pojo.SinglePage;
import org.chinocarbon.judgesystem.pojo.Problem;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/5/13-2:48 PM
 */
public interface ProblemService
{
    PageInfo<Problem> findAllByPages(SinglePage singlePage);
    PageInfo<Problem> findSomeByKeyWord(SinglePage singlePage);

    int createAProblem(Problem problem);
    boolean isPass(int userId, int problemId);
}
