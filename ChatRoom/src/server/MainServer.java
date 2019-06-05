/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MainServer
 * Author:   ITryagain
 * Date:     2019/5/15 17:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package server;

import server.controller.RequestProcessor;
import server.ui.ServerInfoFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class MainServer {
    public static void main(String[] args) {
        int port = Integer.parseInt(DataBuffer.configProp.getProperty("port"));
        //初始化服务器套节字
        try {
            DataBuffer.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {//启动新线程进行客户端连接监听
            public void run() {
                try {
                    while (true) {
                        // 监听客户端的连接
                        Socket socket = DataBuffer.serverSocket.accept();
                        System.out.println("客户来了："
                                + socket.getInetAddress().getHostAddress()
                                + ":" + socket.getPort());

                        //针对每个客户端启动一个线程，在线程中调用请求处理器来处理每个客户端的请求
                        new Thread(new RequestProcessor(socket)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //设置外观感觉
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //启动服务器监控窗体
        new ServerInfoFrame();
    }
}
