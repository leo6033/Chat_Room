/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyCellRenderer
 * Author:   ITryagain
 * Date:     2019/5/16 20:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package client.model.entity;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import common.model.entity.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class MyCellRenderer extends JLabel implements ListCellRenderer {
    private static final long serialVersionUID = 3460394416991636990L;

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        User user = (User)value;
        String name = user.getNickname() + "(" + user.getId() + ")";
        setText(name);
        setIcon(user.getHeadIcon());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}
