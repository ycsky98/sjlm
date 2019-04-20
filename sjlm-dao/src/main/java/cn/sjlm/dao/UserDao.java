package cn.sjlm.dao;

import cn.sjlm.pojo.User;

public interface UserDao {
    /**
     * @params [id]
     * @return cn.sjlm.pojo.User
     * @description 通过用户id查找用户
     */
    User selectUserById(Integer id);
}
