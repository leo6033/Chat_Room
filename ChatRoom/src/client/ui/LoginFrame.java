/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LoginFrame
 * Author:   ITryagain
 * Date:     2019/5/16 20:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client.ui;

import client.DataBuffer;
import client.util.ClientUtil;
import common.model.entity.Request;
import common.model.entity.Response;
import common.model.entity.ResponseStatus;
import common.model.entity.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = -3426717670093483287L;

    private JTextField idTxt;
    private JPasswordField pwdFld;

    public LoginFrame(){
        this.init();
        setVisible(true);
    }

    public void init(){
        this.setTitle("JQ登录");
        this.setSize(330, 230);
        //设置默认窗体在屏幕中央
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);
        this.setResizable(false);

        //把Logo放置到JFrame的北边
        Icon icon = new ImageIcon("images/logo.png");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(324,47));
        this.add(label, BorderLayout.NORTH);

        //登录信息面板
        JPanel mainPanel = new JPanel();
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        mainPanel.setBorder(BorderFactory.createTitledBorder(border, "输入登录信息", TitledBorder.CENTER,TitledBorder.TOP));
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JLabel nameLbl = new JLabel("账号:");
        nameLbl.setBounds(50, 30, 40, 22);
        mainPanel.add(nameLbl);
        idTxt = new JTextField();
        idTxt.setBounds(95, 30, 150, 22);
        idTxt.requestFocusInWindow();//用户名获得焦点
        mainPanel.add(idTxt);

        JLabel pwdLbl = new JLabel("密码:");
        pwdLbl.setBounds(50, 60, 40, 22);
        mainPanel.add(pwdLbl);
        pwdFld = new JPasswordField();
        pwdFld.setBounds(95, 60, 150, 22);
        mainPanel.add(pwdFld);

        //按钮面板放置在JFrame的南边
        JPanel btnPanel = new JPanel();
        this.add(btnPanel, BorderLayout.SOUTH);
        btnPanel.setLayout(new BorderLayout());
        btnPanel.setBorder(new EmptyBorder(2, 8, 4, 8));

        JButton registeBtn = new JButton("注册");
        btnPanel.add(registeBtn, BorderLayout.WEST);
        JButton submitBtn = new JButton("登录");
        btnPanel.add(submitBtn, BorderLayout.EAST);

        //关闭窗口
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                Request req = new Request();
                req.setAction("exit");
                try {
                    ClientUtil.sendTextRequest(req);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally{
                    System.exit(0);
                }
            }
        });

        //注册
        registeBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();  //打开注册窗体
            }
        });

        //"登录"
        submitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    /** 登录 */
    @SuppressWarnings("unchecked")
    private void login() {
        if (idTxt.getText().length() == 0
                || pwdFld.getPassword().length == 0) {
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "账号和密码是必填的",
                    "输入有误",JOptionPane.ERROR_MESSAGE);
            idTxt.requestFocusInWindow();
            return;
        }

        if(!idTxt.getText().matches("^\\d*$")){
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "账号必须是数字",
                    "输入有误",JOptionPane.ERROR_MESSAGE);
            idTxt.requestFocusInWindow();
            return;
        }

        Request req = new Request();
        req.setAction("userLogin");
        req.setAttribute("id", idTxt.getText());
        req.setAttribute("password", new String(pwdFld.getPassword()));

        //获取响应
        Response response = null;
        try {
            response = ClientUtil.sendTextRequest(req);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if(response.getStatus() == ResponseStatus.OK){
            //获取当前用户
            User user2 = (User)response.getData("user");
            if(user2!= null){ //登录成功
                DataBuffer.currentUser = user2;
                //获取当前在线用户列表
                DataBuffer.onlineUsers = (List<User>)response.getData("onlineUsers");

                LoginFrame.this.dispose();
                new ChatFrame();  //打开聊天窗体
            }else{ //登录失败
                String str = (String)response.getData("msg");
                JOptionPane.showMessageDialog(LoginFrame.this,
                        str,
                        "登录失败",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "服务器内部错误，请稍后再试！！！","登录失败",JOptionPane.ERROR_MESSAGE);
        }
    }
}
