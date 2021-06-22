package com.utils;


import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Component
public class MyContextListener implements ServletContextListener {
    private SSHConnection conexionssh;
    public MyContextListener() {
        super();
    }
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // 建立连接
        System.out.println("Context initialized ... !");
        try {
            conexionssh = new SSHConnection();
            conexionssh.SSHConnection();
            System.out.println("成功建立SSH连接！");
        } catch (Throwable e) {
            System.out.println("SSH连接失败！");
            e.printStackTrace(); // error connecting SSH server
        }
    }
    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // 断开连接
        System.out.println("Context destroyed ... !");
        try {
            conexionssh.closeSSH(); // disconnect
            System.out.println("成功断开SSH连接!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("断开SSH连接出错！");
        }
    }
}
