/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: User
 * Author:   ITryagain
 * Date:     2019/5/15 17:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package common.model.entity;

import javax.swing.*;
import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class User implements Serializable {
    private static final long serialVersionUID = 5942011574971970871L;
    private long id;
    private String password;
    private String nickname;
    private int head;
    private char sex;

    public User(String password, String nickname, char sex, int head){
        this.password = password;
        this.sex = sex;
        this.head = head;
        if(nickname.equals("")||nickname==null)
        {
            this.nickname = "未命名";
        }else{
            this.nickname = nickname;
        }
    }

    public User(long id, String password){
        this.id = id;
        this.password = password;
    }

    public long getId(){
        return  id;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setSex(char sex){
        this.sex=sex;
    }

    public char getSex(){
        return this.sex;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setHead(int head){
        this.head = head;
    }

    public int getHead(){
        return this.head;
    }

    public ImageIcon getHeadIcon(){
        ImageIcon image = new ImageIcon("images/"+head+".png");
        return image;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + head;
        result = prime * result + (int)(id ^ (id >> 32));
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + sex;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if(head != other.head || id != other.id || sex != other.sex)
            return false;
        if(nickname == null){
            if(other.nickname != null)
                return false;
        }else if(!nickname.equals(other.nickname))
            return false;
        if(password == null){
            if(other.password != null)
                return false;
        }else if(!password.equals(other.password))
            return  false;
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName()
                + "[id=" + this.id
                + ",pwd=" + this.password
                + ",nickname=" + this.nickname
                + ",head=" + this.head
                + ",sex=" + this.sex
                + "]";
    }
}
