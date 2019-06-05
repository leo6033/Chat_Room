/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: FileInfo
 * Author:   ITryagain
 * Date:     2019/5/15 18:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package common.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class FileInfo implements Serializable {
    private static final long serialVersionUID = -5394575332459969403L;
    /** 消息接收者 */
    private User toUser;
    /** 消息发送者 */
    private User fromUser;
    /** 源文件名 */
    private String srcName;
    /** 发送时间 */
    private Date sendTime;
    /** 目标地IP */
    private String destIp;
    /** 目标地端口 */
    private int destPort;
    /** 目标文件名 */
    private String destName;
    public User getToUser() {
        return toUser;
    }
    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
    public User getFromUser() {
        return fromUser;
    }
    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
    public String getSrcName() {
        return srcName;
    }
    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public String getDestIp() {
        return destIp;
    }
    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }
    public int getDestPort() {
        return destPort;
    }
    public void setDestPort(int destPort) {
        this.destPort = destPort;
    }
    public String getDestName() {
        return destName;
    }
    public void setDestName(String destName) {
        this.destName = destName;
    }
}
