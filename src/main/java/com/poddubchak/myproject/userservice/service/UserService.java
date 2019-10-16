package com.poddubchak.myproject.userservice.service;


import com.poddubchak.microservicies.model.user.MyUser;

import java.util.List;
import java.util.Map;

/**
 * Created by @author Vladimir Poddubchak @date 10.10.2019.
 */

public interface UserService {

    List<MyUser> findAllUsers();
    MyUser findUserById(long userId);
    MyUser createUser(MyUser user);
    MyUser updateUser(MyUser user, long userId);
    MyUser updateUser(Map<String,String> updates, long userId);
    void deleteUser(long userId);
}
