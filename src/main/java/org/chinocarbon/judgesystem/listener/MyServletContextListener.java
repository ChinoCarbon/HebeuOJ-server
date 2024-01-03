package org.chinocarbon.judgesystem.listener;
/**
 * @author ChinoCarbon
 * @since 2022/7/14-1:40 PM
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

@WebListener
public class MyServletContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener
{

    public MyServletContextListener()
    {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Properties properties = new Properties();
        try
        {
            properties.load(new FileInputStream("C:\\Users\\12992\\IdeaProjects\\HebeuOJ-SpringBoot\\src\\main\\resources\\importantPath.properties"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
//        sce.getServletContext().setAttribute("absoluteFilePath", "/Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/webapp/problems");
//        sce.getServletContext().setAttribute("absoluteJudgementPath", "/Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/webapp/judgements");
//        sce.getServletContext().setAttribute("absoluteJudgeJavaPath", "/Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/webapp/judgements");
//        sce.getServletContext().setAttribute("absoluteJudgeCPath", "/Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/webapp/judgements");
//        sce.getServletContext().setAttribute("absoluteJudgeCPPPath", "/Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/webapp/judgements");
//        sce.getServletContext().setAttribute("absoluteConfigFilePath", "/Users/liyinuo/IdeaProjects/HebeuOJ-SpringBoot/src/main/webapp/judgements");

        sce.getServletContext().setAttribute("absoluteIcoPath", properties.get("absoluteIcoPath"));
        sce.getServletContext().setAttribute("absoluteFilePath", properties.get("absoluteFilePath"));
        sce.getServletContext().setAttribute("absoluteJudgementPath", properties.get("absoluteJudgementPath"));
        sce.getServletContext().setAttribute("absoluteJudgeMachinePath", properties.get("absoluteJudgeMachinePath"));
        sce.getServletContext().setAttribute("absoluteCompileMachinePath", properties.get("absoluteCompileMachinePath"));
        sce.getServletContext().setAttribute("absoluteConfigFilePath", properties.get("absoluteConfigFilePath"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se)
    {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se)
    {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe)
    {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe)
    {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe)
    {
        /* This method is called when an attribute is replaced in a session. */
    }
}
