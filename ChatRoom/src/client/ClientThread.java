/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ClientThread
 * Author:   ITryagain
 * Date:     2019/5/16 20:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client;

import client.ui.ChatFrame;
import client.util.ClientUtil;
import client.util.JFrameShaker;
import common.model.entity.*;
import common.util.IOUtil;
import common.util.SocketUtil;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈客户端线程，不断监听服务器发送过来的信息〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class ClientThread extends Thread {
    private JFrame currentFrame;  //当前窗体

    public ClientThread(JFrame frame){
        currentFrame = frame;
    }

    public void run() {
        try {
            while (DataBuffer.clientSeocket.isConnected()) {
                Response response = (Response) DataBuffer.ois.readObject();
                ResponseType type = response.getType();

                System.out.println("获取了响应内容：" + type);
                if (type == ResponseType.LOGIN) {
                    User newUser = (User)response.getData("loginUser");
                    DataBuffer.onlineUserListModel.addElement(newUser);

                    ChatFrame.onlineCountLbl.setText(
                            "在线用户列表("+ DataBuffer.onlineUserListModel.getSize() +")");
                    ClientUtil.appendTxt2MsgListArea("【系统消息】用户"+newUser.getNickname() + "上线了！\n");
                }else if(type == ResponseType.LOGOUT){
                    User newUser = (User)response.getData("logoutUser");
                    DataBuffer.onlineUserListModel.removeElement(newUser);

                    ChatFrame.onlineCountLbl.setText(
                            "在线用户列表("+ DataBuffer.onlineUserListModel.getSize() +")");
                    ClientUtil.appendTxt2MsgListArea("【系统消息】用户"+newUser.getNickname() + "下线了！\n");

                }else if(type == ResponseType.CHAT){ //聊天
                    Message msg = (Message)response.getData("txtMsg");
                    ClientUtil.appendTxt2MsgListArea(msg.getMessage());
                }else if(type == ResponseType.SHAKE){ //振动
                    Message msg = (Message)response.getData("ShakeMsg");
                    ClientUtil.appendTxt2MsgListArea(msg.getMessage());
                    new JFrameShaker(this.currentFrame).startShake();
                }else if(type == ResponseType.TOSENDFILE){ //准备发送文件
                    toSendFile(response);
                }else if(type == ResponseType.AGREERECEIVEFILE){ //对方同意接收文件
                    sendFile(response);
                }else if(type == ResponseType.REFUSERECEIVEFILE){ //对方拒绝接收文件
                    ClientUtil.appendTxt2MsgListArea("【文件消息】对方拒绝接收，文件发送失败！\n");
                }else if(type == ResponseType.RECEIVEFILE){ //开始接收文件
                    receiveFile(response);
                }else if(type == ResponseType.BOARD){
                    Message msg = (Message)response.getData("txtMsg");
                    ClientUtil.appendTxt2MsgListArea(msg.getMessage());
                }else if(type == ResponseType.REMOVE){
                    ChatFrame.remove();
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** 发送文件 */
    private void sendFile(Response response) {
        final FileInfo sendFile = (FileInfo)response.getData("sendFile");

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        Socket socket = null;
        try {
            socket = new Socket(sendFile.getDestIp(),sendFile.getDestPort());//套接字连接
            bis = new BufferedInputStream(new FileInputStream(sendFile.getSrcName()));//文件读入
            bos = new BufferedOutputStream(socket.getOutputStream());//文件写出

            byte[] buffer = new byte[1024];
            int n = -1;
            while ((n = bis.read(buffer)) != -1){
                bos.write(buffer, 0, n);
            }
            bos.flush();
            synchronized (this) {
                ClientUtil.appendTxt2MsgListArea("【文件消息】文件发送完毕!\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            IOUtil.close(bis,bos);
            SocketUtil.close(socket);
        }
    }

    /** 接收文件 */
    private void receiveFile(Response response) {
        final FileInfo sendFile = (FileInfo)response.getData("sendFile");

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(sendFile.getDestPort());
            socket = serverSocket.accept(); //接收
            bis = new BufferedInputStream(socket.getInputStream());//缓冲读
            bos = new BufferedOutputStream(new FileOutputStream(sendFile.getDestName()));//缓冲写出

            byte[] buffer = new byte[1024];
            int n = -1;
            while ((n = bis.read(buffer)) != -1){
                bos.write(buffer, 0, n);
            }
            bos.flush();
            synchronized (this) {
                ClientUtil.appendTxt2MsgListArea("【文件消息】文件接收完毕!存放在["
                        + sendFile.getDestName()+"]\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            IOUtil.close(bis,bos);
            SocketUtil.close(socket);
            SocketUtil.close(serverSocket);
        }
    }

    /** 准备发送文件	 */
    private void toSendFile(Response response) {
        FileInfo sendFile = (FileInfo)response.getData("sendFile");

        String fromName = sendFile.getFromUser().getNickname()
                + "(" + sendFile.getFromUser().getId() + ")";
        String fileName = sendFile.getSrcName()
                .substring(sendFile.getSrcName().lastIndexOf(File.separator)+1);

        int select = JOptionPane.showConfirmDialog(this.currentFrame,
                fromName + " 向您发送文件 [" + fileName+ "]!\n同意接收吗?",
                "接收文件", JOptionPane.YES_NO_OPTION);
        try {
            Request request = new Request();
            request.setAttribute("sendFile", sendFile);

            if (select == JOptionPane.YES_OPTION) {
                JFileChooser jfc = new JFileChooser();
                jfc.setSelectedFile(new File(fileName));
                int result = jfc.showSaveDialog(this.currentFrame);

                if (result == JFileChooser.APPROVE_OPTION){
                    //设置目的地文件名
                    sendFile.setDestName(jfc.getSelectedFile().getCanonicalPath());
                    //设置目标地的IP和接收文件的端口
                    sendFile.setDestIp(DataBuffer.ip);
                    sendFile.setDestPort(DataBuffer.RECEIVE_FILE_PORT);

                    request.setAction("agreeReceiveFile");
//                    receiveFile(response);
                    ClientUtil.appendTxt2MsgListArea("【文件消息】您已同意接收来自 "
                            + fromName +" 的文件，正在接收文件 ...\n");
                } else {
                    request.setAction("refuseReceiveFile");
                    ClientUtil.appendTxt2MsgListArea("【文件消息】您已拒绝接收来自 "
                            + fromName +" 的文件!\n");
                }
            } else {
                request.setAction("refuseReceiveFile");
                ClientUtil.appendTxt2MsgListArea("【文件消息】您已拒绝接收来自 "
                        + fromName +" 的文件!\n");
            }

            ClientUtil.sendTextRequest2(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

