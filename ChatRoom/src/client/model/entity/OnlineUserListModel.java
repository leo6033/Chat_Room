/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OnlineUserListModel
 * Author:   ITryagain
 * Date:     2019/5/16 20:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client.model.entity;

import java.util.List;

import javax.swing.AbstractListModel;

import common.model.entity.User;

/**
 * 〈在线用户JList的Model〉<br>
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class OnlineUserListModel extends AbstractListModel {
    private static final long serialVersionUID = -3903760573171074301L;
    private List<User> onlineUsers;

    public OnlineUserListModel(List<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public void addElement(Object object) {
        if (onlineUsers.contains(object)) {
            return;
        }
        int index = onlineUsers.size();
        onlineUsers.add((User)object);
        fireIntervalAdded(this, index, index);
    }

    public boolean removeElement(Object object) {
        int index = onlineUsers.indexOf(object);
        if (index >= 0) {
            fireIntervalRemoved(this, index, index);
        }
        return onlineUsers.remove(object);
    }

    public int getSize() {
        return onlineUsers.size();
    }

    public Object getElementAt(int i) {
        return onlineUsers.get(i);
    }

    public List<User> getOnlineUsers() {
        return onlineUsers;
    }
}
