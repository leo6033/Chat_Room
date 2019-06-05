/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserService
 * Author:   ITryagain
 * Date:     2019/5/15 18:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package server.model.service;

import common.model.entity.User;
import common.util.IOUtil;
import server.DataBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author ITryagain
 * @create 2019/5/15
 * @since 1.0.0
 */

public class UserService {
    private static int idCount = 3; //id

    /** 新增用户 */
    public void addUser(User user){
        user.setId(++idCount);
        List<User> users = loadAllUser();
        users.add(user);
        saveAllUser(users);
    }

    /** 用户登录 */
    public User login(long id, String password){
        User result = null;
        List<User> users = loadAllUser();
        for (User user : users) {
            if(id == user.getId() && password.equals(user.getPassword())){
                result = user;
                break;
            }
        }
        return result;
    }

    /** 根据ID加载用户 */
    public User loadUser(long id){
        User result = null;
        List<User> users = loadAllUser();
        for (User user : users) {
            if(id == user.getId()){
                result = user;
                break;
            }
        }
        return result;
    }


    /** 加载所有用户 */
    @SuppressWarnings("unchecked")
    public List<User> loadAllUser() {
        List<User> list = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new FileInputStream(
                            DataBuffer.configProp.getProperty("dbpath")));

            list = (List<User>)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            IOUtil.close(ois);
        }
        return list;
    }

    private void saveAllUser(List<User> users) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(
                            DataBuffer.configProp.getProperty("dbpath")));
            //写回用户信息
            oos.writeObject(users);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            IOUtil.close(oos);
        }
    }



    /** 初始化几个测试用户 */
    public void initUser(){
        User user = new User("admin", "Admin", 'm', 0);
        user.setId(1);

        User user2 = new User("123", "yong", 'm', 1);
        user2.setId(2);

        User user3 = new User("123", "anni", 'f', 2);
        user3.setId(3);

        List<User> users = new CopyOnWriteArrayList<User>();
        users.add(user);
        users.add(user2);
        users.add(user3);

        this.saveAllUser(users);
    }

    public static void main(String[] args){
        new UserService().initUser();
        List<User> users = new UserService().loadAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
