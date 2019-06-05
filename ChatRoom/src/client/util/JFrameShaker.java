/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: JFrameShaker
 * Author:   ITryagain
 * Date:     2019/5/16 20:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 〈一句话功能简述〉<br> 
 * 〈窗口振动器〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class JFrameShaker {
    /** 窗口距离中心左右晃动的最大距离(像索) */
    public static final int SHAKE_DISTANCE = 4;
    /** 窗口晃动一个周期（中间,右,中间,左,中间）所用的时间(ms). 这个值越小,晃动的就越快 */
    public static final double SHAKE_CYCLE = 10;
    /** 晃动的时长(ms) */
    public static final int SHAKE_DURATION = 600;

    private JFrame frame;
    private Point oldLocation;
    private long startTime;
    private Timer shakeTimer;

    public JFrameShaker(JFrame frame) {
        this.frame = frame;
    }

    public void startShake() {
        oldLocation = frame.getLocation();// 获取窗口的原始位置

        startTime = System.currentTimeMillis(); // 开始计时
        shakeTimer = new Timer((int) SHAKE_CYCLE / 5, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - startTime;// 计算振动的用时
                // 利用时间计算出某一时刻晃动的幅度
                double waveOffset = (elapsed % SHAKE_CYCLE) / SHAKE_CYCLE;
                double angle = waveOffset * Math.PI;
                double angley = waveOffset * Math.PI;
                int shakeX = (int) ((Math.sin(angle) * SHAKE_DISTANCE) + oldLocation.x);
                int shakeY = (int) ((Math.sin(angley) * SHAKE_DISTANCE) + oldLocation.y);
                frame.setLocation(shakeX, shakeY);
                if (elapsed >= SHAKE_DURATION) { // 振动时长到了就停止
                    stopShake();
                }
            }
        });
        shakeTimer.start(); // 启动定时任务
    }

    /** 停止振动 */
    public void stopShake() {
        shakeTimer.stop();
        frame.setLocation(oldLocation);
    }

    public static void main(String asrg[]) {
        final JFrame frame = new JFrame();
        JButton button = new JButton("打开");
        frame.setBounds(300, 200, 500, 500);
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFrameShaker dialog1 = new JFrameShaker(frame);
                dialog1.startShake();
            }
        });
    }
}
