/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Response
 * Author:   ITryagain
 * Date:     2019/5/15 18:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package common.model.entity;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈响应对象〉<br>
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class Response implements Serializable {
    private static final long serialVersionUID = 1689541820872288991L;
    /** 响应状态 */
    private ResponseStatus status;
    /** 响应数据的类型 */
    private ResponseType type;

    private Map<String, Object> dataMap;

    /** 响应输出流 */
    private OutputStream outputStream;

    public Response(){
        this.status = ResponseStatus.OK;
        this.dataMap = new HashMap<String, Object>();
    }


    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setData(String name, Object value){
        this.dataMap.put(name, value);
    }

    public Object getData(String name){
        return this.dataMap.get(name);
    }

    public void removeData(String name){
        this.dataMap.remove(name);
    }

    public void clearData(){
        this.dataMap.clear();
    }
}
