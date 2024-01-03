package org.chinocarbon.judgesystem.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.github.pagehelper.PageInfo;
import org.chinocarbon.judgesystem.dao.ProblemDao;
import org.chinocarbon.judgesystem.enums.AccountStatus;
import org.chinocarbon.judgesystem.pojo.SinglePage;
import org.chinocarbon.judgesystem.pojo.Problem;
import org.chinocarbon.judgesystem.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author ChinoCarbon
 * @since 2022/5/9-2:44 PM
 */

@RestController
public class ProblemController
{
    private ProblemService problemService;

    private ProblemDao problemDao;

    @Autowired
    @Qualifier("problemServiceImpl")
    public void setProblemService(ProblemService problemService)
    {
        this.problemService = problemService;
    }

    @Autowired
    public void setProblemDao(ProblemDao problemDao)
    {
        this.problemDao = problemDao;
    }

    @PutMapping("/ProblemsH")
    public String addAProblem(@RequestBody Problem problem, HttpServletRequest request) throws IOException
    {
        try
        {
            problemService.createAProblem(problem);
            File file = new File(request.getServletContext().getAttribute("absoluteFilePath") + "/" + problem.getProblemId());
            System.out.println(request.getServletContext().getAttribute("absoluteFilePath") + "/" + problem.getProblemId());
            System.out.println(file.mkdir());
            FileWriter fileWriter = new FileWriter(request.getServletContext().getAttribute("absoluteFilePath") + "/" + problem.getProblemId() + "/content.md");
            System.out.println(request.getServletContext().getAttribute("absoluteFilePath") + "/" + problem.getProblemId() + "/content.md");
            fileWriter.write(problem.getProblemContent());
            fileWriter.close();
            return AccountStatus.SUCCESS.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
            return AccountStatus.FAILED.toString();
        }
    }

    @GetMapping("/problemsH/{id}")
    public String getProblemContent(@PathVariable int id, HttpServletRequest request) throws IOException
    {
        try
        {
            FileReader reader = new FileReader(request.getServletContext().getAttribute("absoluteFilePath") + "/" + id + "/content.md");
            System.out.println(request.getServletContext().getAttribute("absoluteFilePath") + "/" + id + "/content.md");
            StringBuilder stringBuilder = new StringBuilder();
            char[] buf = new char[1024];
            int len;
            while((len = reader.read(buf)) != -1)
            {
                stringBuilder.append(buf, 0, len);
            }
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        } catch (Exception e)
        {
            return null;
        }
    }

    @PostMapping("/problemsH")
    public Problem findProblemById(@RequestBody Problem problem)
    {
        System.out.println(problem);
        return problemDao.selectById(problem.getProblemId());
      //  return problemDao.getProblemInformation(problem);
    }

    @PostMapping("/abcde")
    public List<Problem> findProblemByIdff(@RequestBody Problem problem)
    {
        return problemDao.selectList(null);
        //  return problemDao.getProblemInformation(problem);
    }

    @RequestMapping("/findAllByPages")
    public PageInfo<Problem> getAllProblems(@RequestBody SinglePage singlePage)
    {
        return problemService.findAllByPages(singlePage);
    }

    @RequestMapping("/findLikeByPages")
    public PageInfo<Problem> getSomeProblems(@RequestBody SinglePage singlePage)
    {
        return problemService.findSomeByKeyWord(singlePage);
    }

    @RequestMapping("/getProblemInformation")
    public Problem getProblemInformation(@RequestBody Problem problem)
    {
        System.out.println(problem);
        //return problemDao.getProblemInformation(problem);
        return problemDao.selectById(problem.getProblemId());
    }

    @PostMapping("/uploadInOut")
    public String uploadInOut(@RequestParam String problemId, MultipartFile file, HttpServletRequest request) throws IOException
    {
        String uploadPath = request.getServletContext().getRealPath("/problems/" + problemId + "/data");

        File saveFile = new File(uploadPath);
        if(!saveFile.exists()) {
            saveFile.mkdir();
        }
        File[] files = saveFile.listFiles();
        for(File file1: files)
        {
            file1.delete();
        }

        if(!saveFile.exists()) {
            saveFile.mkdir();
        }
        String dateTime = String.valueOf(LocalDateTime.now());
        String uuid = UUID.randomUUID().toString();
        String saveName = file.getOriginalFilename();
        String savePathName = uploadPath + "/" + saveName;
        File result = new File(savePathName);
        file.transferTo(result);
        return AccountStatus.SUCCESS.toString();
    }

    @PostMapping("/uploadImage")
    public String uploadImage(MultipartFile file, HttpServletRequest request) throws IOException
    {
        String uploadPath = request.getServletContext().getRealPath("/img");
        File saveFile = new File(uploadPath);
        if(!saveFile.exists()) {
            saveFile.mkdir();
        }
        String dateTime = String.valueOf(LocalDateTime.now());
        String uuid = UUID.randomUUID().toString();
        String saveName = dateTime + "-" + uuid + "-" + file.getOriginalFilename();
        String savePathName = uploadPath + "/" + saveName;
        File result = new File(savePathName);
        file.transferTo(result);
        return request.getServletContext().getContextPath() + "/img/" + saveName;
    }

    @RequestMapping("isPass")
    public boolean isPass(@RequestParam int userId, @RequestParam int problemId)
    {
        return problemService.isPass(userId, problemId);
    }
}
