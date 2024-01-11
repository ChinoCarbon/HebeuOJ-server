package org.chinocarbon.judgesystem.core;

import org.chinocarbon.judgesystem.pojo.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ChinoCarbon
 * @since 2022/4/29-8:08 PM
 */
public class CoreCompile
{
    private final LanguageType languageType;
    private final String absoluteSourceFilePath;
    private final String absoluteJudgementFilePath;

    private final String absoluteCompileMachinePath;

    public CoreCompile(LanguageType type, String absoluteSourceFilePath, String absoluteJudgementFilePath,
                       String absoluteCompileMachinePath)
    {
       this.languageType = type;
       this.absoluteSourceFilePath = absoluteSourceFilePath;
       this.absoluteJudgementFilePath = absoluteJudgementFilePath;
       this.absoluteCompileMachinePath = absoluteCompileMachinePath;
    }

    private void compileCore(Process process, Result result) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder errorMessage = new StringBuilder(reader.readLine());
        if("compile done".equals(errorMessage.toString()))
        {
            result.setCE(false);
            result.getCompileMessage().append("compile success");
        } else {
            errorMessage.append("\n");
            String singleErrMsg;
            while((singleErrMsg = reader.readLine()) != null)
            {
                System.out.println(singleErrMsg);
                if("compile done".equals(singleErrMsg))
                {
                    break;
                }
                errorMessage.append(singleErrMsg).append('\n');
            }
            result.setCE(true);
            result.getCompileMessage().append(errorMessage);
        }
    }

    private void compileC(Result result) throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c",
                 absoluteCompileMachinePath + "/CompileC.sh " + absoluteJudgementFilePath);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        compileCore(process, result);
    }

    private void compileCPP(Result result) throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c",
                absoluteCompileMachinePath + "/CompileCpp.sh " + absoluteJudgementFilePath);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        compileCore(process, result);
    }

    private void compileJava(Result result) throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c",
                absoluteCompileMachinePath + "/CompileJava.sh " + absoluteJudgementFilePath);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        compileCore(process, result);
    }

    public void compile(Result result) throws IOException
    {
        switch (this.languageType)
        {
            case C -> compileC(result);
            case CPP -> compileCPP(result);
            case Java -> compileJava(result);
        }
    }
}
