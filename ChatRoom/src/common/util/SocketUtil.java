/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SocketUtil
 * Author:   ITryagain
 * Date:     2019/5/16 20:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package common.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 〈网络操作相关工具类〉<br>
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/16
 * @since 1.0.0
 */

public class SocketUtil {
    /** 关闭Socket */
    public static void close(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 关闭ServerSocket */
    public static void close(ServerSocket ss) {
        if (ss != null && !ss.isClosed()) {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
