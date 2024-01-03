package org.chinocarbon.judgesystem.controller;

import org.chinocarbon.judgesystem.Utils.MySerializeUtil;
import org.chinocarbon.judgesystem.core.LanguageType;
import org.chinocarbon.judgesystem.pojo.Judgement;
import org.chinocarbon.judgesystem.pojo.Result;
import org.chinocarbon.judgesystem.service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ChinoCarbon
 * @since 2022/5/8-7:37 PM
 */
@Controller
public class JudgeController
{
    private JudgeService judgeService;

    @Autowired
    @Qualifier("judgeServiceImpl")
    public void setJudgeService(JudgeService judgeService)
    {
        this.judgeService = judgeService;
    }

    @RequestMapping("getJudgementId")
    @ResponseBody
    public Integer getJudgementId(@RequestBody Judgement submitMessage, HttpServletResponse response, HttpServletRequest request) throws IOException
    {
        request.getSession().setAttribute("judgement", submitMessage);
        int p = judgeService.getJudgementId(submitMessage);
        System.out.println(p);
        return p;
    }

    @RequestMapping("judgement")
    @ResponseBody
    public Result readyToJudge(@RequestParam int id, HttpServletResponse response, HttpServletRequest request) throws IOException, ClassNotFoundException
    {
        if(new File(request.getServletContext().getRealPath("/judgements/" + id + "/result.txt")).isFile())
        {
            Object obj= MySerializeUtil.myDeserialize(request.getServletContext().getRealPath("/judgements/" + id + "/result.txt"));
            if(obj instanceof Result)
            {
                Result result = (Result) obj;
                System.out.println("反序列化之后的对象：" + request);
                return result;
            }
        }
        Judgement submitMessage = (Judgement) request.getSession().getAttribute("judgement");
        if(LanguageType.getType(submitMessage.getLanguageType()) == null) return null;
        if(id == submitMessage.getJudgementId())
        {
            return judgeService.judge(submitMessage,
                    (String) request.getServletContext().getAttribute("absoluteConfigFilePath"),
                    (String) request.getServletContext().getAttribute("absoluteFilePath"),
                    (String) request.getServletContext().getAttribute("absoluteJudgementPath"),
                    (String) request.getServletContext().getAttribute("absoluteCompileMachinePath"),
                    (String) request.getServletContext().getAttribute("absoluteJudgeMachinePath"));
        } else return null;
    }

}
