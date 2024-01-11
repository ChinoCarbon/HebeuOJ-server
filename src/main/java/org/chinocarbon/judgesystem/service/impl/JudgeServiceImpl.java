package org.chinocarbon.judgesystem.service.impl;

import org.chinocarbon.judgesystem.Utils.MySerializeUtil;
import org.chinocarbon.judgesystem.core.CoreCompile;
import org.chinocarbon.judgesystem.core.CoreJudge;
import org.chinocarbon.judgesystem.core.LanguageType;
import org.chinocarbon.judgesystem.dao.JudgeDao;
import org.chinocarbon.judgesystem.dao.ProblemDao;
import org.chinocarbon.judgesystem.dao.UserDao;
import org.chinocarbon.judgesystem.enums.PointStatement;
import org.chinocarbon.judgesystem.pojo.PointMessage;
import org.chinocarbon.judgesystem.pojo.Result;
import org.chinocarbon.judgesystem.pojo.Judgement;
import org.chinocarbon.judgesystem.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author ChinoCarbon
 * @since 2022/5/15-3:27 PM
 */
@Service
public class JudgeServiceImpl implements JudgeService
{
    JudgeDao judgeDao;
    ProblemDao problemDao;
    UserDao userDao;

    @Autowired
    public void setJudgeDao(JudgeDao judgeDao)
    {
        this.judgeDao = judgeDao;
    }
    @Autowired
    public void setProblemDao(ProblemDao problemDao)
    {
        this.problemDao = problemDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public Result judge(Judgement judgement, String absoluteConfigFilePath,
                        String absoluteFilePath, String absoluteJudgementPath, String absoluteCompileMachinePath,
                        String absoluteJudgeMachinePath) throws IOException
    {

        System.out.println(absoluteCompileMachinePath);
        System.out.println(absoluteJudgeMachinePath);

        File file = new File(absoluteJudgementPath + File.separator + judgement.getJudgementId());
        System.out.println(absoluteJudgementPath + File.separator + judgement.getJudgementId());
        System.out.println(file.mkdir());

        String fileBack = LanguageType.getType(judgement.getLanguageType()).toString().toLowerCase(Locale.ROOT);
        FileWriter fileWriter = new FileWriter(absoluteJudgementPath + File.separator + judgement.getJudgementId() + File.separator + "Main." + fileBack);
        fileWriter.write(judgement.getCode());
        fileWriter.close();

        Result result = new Result(judgement);

        CoreCompile coreCompile = new CoreCompile(LanguageType.getType(judgement.getLanguageType()),
                absoluteFilePath + File.separator + judgement.getProblemId(),
                absoluteJudgementPath + File.separator + judgement.getJudgementId(),
                absoluteCompileMachinePath);

        coreCompile.compile(result);
        problemDao.addOne(judgement.getProblemId());
        userDao.addOne(judgement.getUserId());
        if(result.isCE())
        {
            MySerializeUtil.mySerialize(result, absoluteJudgementPath + File.separator + judgement.getJudgementId() + File.separator + "result.txt");
            System.out.println("序列化原始对象完成！OK！");
            return result;
        }
        CoreJudge coreJudge = new CoreJudge(LanguageType.getType(judgement.getLanguageType()), absoluteFilePath,
                absoluteJudgeMachinePath, absoluteConfigFilePath, 2000, 128, judgement.getProblemId(), judgement.getJudgementId());
        coreJudge.judge(result);
        List<PointMessage> list = result.getMessages();
        boolean f = false;
        for (PointMessage pointMessage : list)
        {
            if (pointMessage.getStatement() != PointStatement.ACCEPTED)
            {
                f = true;
                break;
            }
        }
        if(!f)
        {
            problemDao.addPassedOne(judgement.getProblemId());
            userDao.addPassOne(judgement.getUserId());
            problemDao.addAPass(judgement.getUserId(), judgement.getProblemId());
        }
        MySerializeUtil.mySerialize(result, absoluteJudgementPath + File.separator + judgement.getJudgementId() + File.separator + "result.txt");
        System.out.println("序列化原始对象完成！OK！");
        return result;
    }

    @Override
    public int getJudgementId(Judgement judgement)
    {
        judgeDao.insertARecord(judgement);
        return judgement.getJudgementId();
    }
}
