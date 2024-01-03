package org.chinocarbon.judgesystem.service;

import org.chinocarbon.judgesystem.pojo.Result;
import org.chinocarbon.judgesystem.pojo.Judgement;

import java.io.IOException;

/**
 * @author ChinoCarbon
 * @since 2022/5/15-3:27 PM
 */
public interface JudgeService
{
    Result judge(Judgement judgement, String absoluteConfigFilePath, String absoluteFilePath,
                 String absoluteJudgementPath, String absoluteCompileMachinePath, String absoluteJudgeMachinePath) throws IOException;

    int getJudgementId(Judgement judgement);
}
