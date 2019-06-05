/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ClientMain
 * Author:   ITryagain
 * Date:     2019/5/16 20:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client;

import client.ui.LoginFrame;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈客户端入口类〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class ClientMain {

    public static void main(String[] args) {
        connection(); //连接到服务器

        //设置外观感觉
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeel);
//            UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());

        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginFrame();  //启动登录窗体
    }

    /** 连接到服务器 */
    public static void connection() {
        String ip = DataBuffer.configProp.getProperty("ip");
        int port = Integer.parseInt(DataBuffer.configProp.getProperty("port"));
        try {
            DataBuffer.clientSeocket = new Socket(ip, port);
            DataBuffer.oos = new ObjectOutputStream(DataBuffer.clientSeocket.getOutputStream());
            DataBuffer.ois = new ObjectInputStream(DataBuffer.clientSeocket.getInputStream());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "连接服务器失败,请检查!","服务器未连上", JOptionPane.ERROR_MESSAGE);//否则连接失败
            System.exit(0);
        }
    }
}
