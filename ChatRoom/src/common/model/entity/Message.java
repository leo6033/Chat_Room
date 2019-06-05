/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Message
 * Author:   ITryagain
 * Date:     2019/5/15 18:56
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

public class Message implements Serializable {
    private static final long serialVersionUID = 1820192075144114657L;
    /** 消息接收者 */
    private User toUser;
    /** 消息发送者 */
    private User fromUser;
    /** 消息内容 */
    private String message;
    /** 发送时间 */
    private Date sendTime;


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
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
