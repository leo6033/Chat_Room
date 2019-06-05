/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IOUtil
 * Author:   ITryagain
 * Date:     2019/5/15 18:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 〈IO流操作相关工具类〉<br>
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class IOUtil {
    /** 关闭字节输入流 */
    public static void close(InputStream is){
        if(null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /** 关闭字节输出流 */
    public static void close(OutputStream os){
        if(null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** 关闭字节输入流和输出流 */
    public static void close(InputStream is, OutputStream os){
        close(is);
        close(os);
    }
}
