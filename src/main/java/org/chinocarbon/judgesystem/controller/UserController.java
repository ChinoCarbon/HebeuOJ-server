package org.chinocarbon.judgesystem.controller;

import com.alibaba.fastjson.JSONObject;
import org.chinocarbon.judgesystem.Utils.EncodeUtils;
import org.chinocarbon.judgesystem.dao.UserDao;
import org.chinocarbon.judgesystem.pojo.Classes;
import org.chinocarbon.judgesystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

/**
 * @author ChinoCarbon
 * @since 2022/5/11-9:22 AM
 */
@RestController
public class UserController
{
    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @RequestMapping("/checkCookie")
    public String selectUsernameByCookie(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Cookie[] cookies = request.getCookies();

        if(cookies == null)
        {
            System.out.println("未找到Cookie");
            return "Cookie Not Found";
        }

        String token = null;
        for(Cookie cookie: cookies)
        {
            if ("loginToken".equals(cookie.getName()))
            {
                token = EncodeUtils.decode(cookie.getValue());
                break;
            }
        }

        if(token == null)
        {
            System.out.println("未找到cookie");
            return "Cookie Not Found";
        }
        System.out.println("token=" + token);

        String[] tokenInfo = token.split("&");
        try
        {
            User user = userDao.selectUserById(Integer.parseInt(tokenInfo[0]));
            System.out.println(user);
            System.out.println(tokenInfo[0]);
            System.out.println(Duration.between(LocalDateTime.parse(tokenInfo[1]),
                    LocalDateTime.now(ZoneId.of("Asia/Shanghai"))).toMinutes());
            if (user != null &&
                    Duration.between(LocalDateTime.parse(tokenInfo[1]),
                            LocalDateTime.now(ZoneId.of("Asia/Shanghai"))).toMinutes() < 120)
            {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSONObject.toJSONString(user));
                return null;
            }
            System.out.println("token过期 不合法");
            return "Cookie Not Found";
        } catch (Exception e)
        {
            System.out.println("出错");
            return "Cookie Not Found";
        }
    }

    @RequestMapping("/getIco")
    public void getIco(@RequestParam int id, HttpServletResponse response, HttpServletRequest request)
    {
        System.out.println(request.getServletContext().getAttribute("absoluteIcoPath") + "/" + id);
        File parentFile = new File(request.getServletContext().getAttribute("absoluteIcoPath") + "/" + id);
        System.out.println(parentFile.exists());
        System.out.println(parentFile.getAbsolutePath());
        File ico = Objects.requireNonNull(parentFile.listFiles())[0];
        String[] names = ico.getName().split(" ");
        if("png".equalsIgnoreCase(names[names.length - 1]))
        {
            response.setContentType("image/png");
        } else
        {
            response.setContentType("image/jpeg");
        }
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(ico);
            byte[] b = new byte[1024];
            int len = 0;
            while((len = fileInputStream.read(b)) != -1)
            {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if(fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("selectUserById")
    public User getSingleUser(@RequestBody User user)
    {
        return userDao.selectUserById(user.getUserId());
    }

    @RequestMapping("selectUserByClassId")
    public List<User> getSingleUserByClassId(@RequestBody Classes aclass)
    {
        return userDao.selectUserByClassId(aclass.getClassId());
    }
}
