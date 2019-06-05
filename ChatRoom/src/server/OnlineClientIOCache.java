/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OnlineClientIOCache
 * Author:   ITryagain
 * Date:     2019/5/15 17:36
 * Description: 在线客户端的IO流缓存类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈在线客户端的IO流缓存类〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class OnlineClientIOCache {
    //针对同一个Socket中获取的流在全局范围中最好只包装一次，以免出错
    private ObjectInputStream ois; // 对象输入流
    private ObjectOutputStream oos; // 对象输出流

    public OnlineClientIOCache(ObjectInputStream ois, ObjectOutputStream oos){
        this.ois = ois;
        this.oos = oos;
    }

    public ObjectOutputStream getOos(){
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

}
