package org.chinocarbon.judgesystem.controller;

import org.chinocarbon.judgesystem.Utils.EncodeUtils;
import org.chinocarbon.judgesystem.enums.AccountStatus;
import org.chinocarbon.judgesystem.pojo.User;
import org.chinocarbon.judgesystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author ChinoCarbon
 * @since 2022/5/12-4:34 PM
 */

@Controller
public class AccountController
{
    private AccountService accountService;

    @Autowired
    @Qualifier("accountServiceImpl")
    public void setLogInService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @RequestMapping("/checkLogIn")
    public void checkLogIn(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("ready to check LogIn");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");

        AccountStatus accountStatus;
        if(!((String) request.getSession().getAttribute("LogInCheckCode")).equalsIgnoreCase(user.getCheckCode()))
        {
            System.out.println(request.getSession().getAttribute("LogInCheckCode"));
            accountStatus = AccountStatus.WRONG_CHECK_CODE;
        } else
        {
            accountStatus = accountService.checkLogIn(user);
        }
        System.out.println(accountStatus);
        if(accountStatus == AccountStatus.SUCCESS)
        {
            StringBuilder initToken = new StringBuilder();
            initToken.append(user.getUserId()).append("&");
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
            initToken.append(localDateTime);

            Cookie cookie = new Cookie("loginToken", EncodeUtils.encode(initToken.toString()));
            cookie.setMaxAge(60 * 60 * 2);
            response.addCookie(cookie);
        }
        response.getWriter().write(accountStatus.toString());
    }

    @RequestMapping("/checkRegister")
    public void checkRegister(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("ready to check Register");
        AccountStatus accountStatus;

        if(!((String) request.getSession().getAttribute("RegisterCheckCode")).equalsIgnoreCase(user.getCheckCode()))
        {
            System.out.println(request.getSession().getAttribute("RegisterCheckCode"));
            accountStatus = AccountStatus.WRONG_CHECK_CODE;
        } else
        {
            accountStatus = accountService.checkRegister(user);
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        response.getWriter().write(accountStatus.toString());
    }
}
