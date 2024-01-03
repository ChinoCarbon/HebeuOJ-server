package org.chinocarbon.judgesystem.core;

import org.chinocarbon.judgesystem.enums.PointStatement;
import org.chinocarbon.judgesystem.pojo.PointMessage;
import org.chinocarbon.judgesystem.pojo.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ChinoCarbon
 * @since 2022/4/30-4:32 PM
 */
public class CoreJudge
{
    private LanguageType languageType;
    private String absoluteSourceFilePath;
    private String absoluteConfigFilePath;
    private String absoluteJudgeMachinePath;
    private  int maxTime;
    private int maxMemory;
    private int problemNum;

    private int judgementId;

    public CoreJudge(LanguageType languageType, String absoluteSourceFilePath, String absoluteJudgeMachinePath, String absoluteConfigFilePath,
                     int maxTime, int maxMemory, int problemNum, int judgementId)
    {
        this.languageType = languageType;
        this.absoluteSourceFilePath = absoluteSourceFilePath;
        this.absoluteConfigFilePath = absoluteConfigFilePath;
        this.absoluteJudgeMachinePath = absoluteJudgeMachinePath;
        this.maxTime = maxTime;
        this.maxMemory = maxMemory;
        this.problemNum = problemNum;
        this.judgementId = judgementId;
    }

    private void coreJudge(Result result, ProcessBuilder processBuilder) throws IOException
    {
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String singleLine = null;
        int tot = 0;

        while((singleLine = br.readLine()) != null)
        {
            System.out.println(singleLine);
            if(singleLine.equals("start judge")) continue;
            if(singleLine.length() < 3 || !"#&#".equals(singleLine.substring(0, 3))) continue;
            String[] results = singleLine.split(" ");
            result.addAMessage(
                    new PointMessage(
                            ++tot, PointStatement.getStatementByString(results[1]), Long.parseLong(results[2]), 123));
        }
    }
    private void judgeJava(Result result) throws IOException
    {
        System.out.println("start judge");
        ProcessBuilder processBuilder = new ProcessBuilder
                ("python", absoluteJudgeMachinePath + "/JudgeJava.py",
                        String.valueOf(maxTime), String.valueOf(problemNum), String.valueOf(judgementId), absoluteConfigFilePath);
        coreJudge(result, processBuilder);
    }

    private void judgeC(Result result) throws IOException
    {
        System.out.println("start judge");
        ProcessBuilder processBuilder = new ProcessBuilder
                ("sh", "-c", absoluteJudgeMachinePath + "/JudgeC.sh "
                                + maxTime + " " + problemNum + " " + judgementId + " " +absoluteConfigFilePath);
        coreJudge(result, processBuilder);
    }

    private void judgeCPP(Result result) throws IOException
    {
        System.out.println("start judge");
        ProcessBuilder processBuilder = new ProcessBuilder
                ("python", absoluteJudgeMachinePath + "/JudgeCPP.py",
                                 String.valueOf(maxTime), String.valueOf(problemNum), String.valueOf(judgementId), absoluteConfigFilePath);
        coreJudge(result, processBuilder);
    }

    public void judge(Result result) throws IOException
    {
        switch (this.languageType)
        {
            case C -> judgeC(result);
            case CPP -> judgeCPP(result);
            case Java -> judgeJava(result);
        }
    }

}
