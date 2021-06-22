package com.utils;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author Fall
 */
public class SSHConnection {
    private String user = "root";
    // 服务器登录名
    private String password = "Qweasdzxc1";
    // 登陆密码
    private String host = "112.74.61.205";
    //服务器公网IP
    private int port = 22;
    // 跳板机ssh开放的接口   默认端口 22
    private int local_port = 3307;
    // 这个是本地的端口，很重要！！！选取一个没有占用的port即可
    private String remote_host = "127.0.0.1";
    // 要访问的mysql所在的host    服务器局域网IP（127.0.0.1也行）
    private int remote_port = 3306;
    // 服务器上数据库端口号
    private Session session = null;
    /**
     *    建立SSH连接
     */
    public void SSHConnection() throws Exception{
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            // 日志打印自己脑补
            session.connect();
            session.setPortForwardingL(local_port, remote_host, remote_port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *    断开SSH连接
     */
    public void closeSSH () throws Exception
    {
        this.session.disconnect();
    }
}
