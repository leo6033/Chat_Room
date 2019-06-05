/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ResponceType
 * Author:   ITryagain
 * Date:     2019/5/15 18:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package common.model.entity;

/**
 * 〈响应类型枚举〉<br>
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public enum ResponseType {
    /**
     * 文本内容
     */
    TEXT,
    /**
     * 准备发送文件
     */
    TOSENDFILE,
    /**
     * 同意接收文件
     */
    AGREERECEIVEFILE,
    /**
     * 拒绝接收文件
     */
    REFUSERECEIVEFILE,
    /**
     * 发送文件
     */
    RECEIVEFILE,
    /**
     * 客户端登录
     */
    LOGIN,
    /**
     * 客户端退出
     */
    LOGOUT,
    /**
     * 聊天
     */
    CHAT,
    /**
     * 振动
     */
    SHAKE,
    /**
     * 其它
     */
    OTHER,
    /*
     * 广播
     */
    BOARD,
    /*
     * 踢除
     */
    REMOVE
}
