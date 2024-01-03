package org.chinocarbon.judgesystem.listener;
/**
 * @author ChinoCarbon
 * @since 2022/7/14-1:40 PM
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        try (InputStream inputStream = MyServletContextListener.class.getClassLoader().getResourceAsStream("importantPath.properties")){
            // 通过类加载器获取资源文件流
            if (inputStream == null) {
                System.out.println("Unable to find file");
                return;
            }
            // 读取属性文件
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 假设 properties 是已经加载好的 Properties 对象
        for (String key : properties.stringPropertyNames()) {
            sce.getServletContext().setAttribute(key, properties.get(key));
        }
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
