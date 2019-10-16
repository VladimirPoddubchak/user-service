package com.poddubchak.myproject.userservice.controller;

import com.poddubchak.microservicies.model.user.MyUser;
import com.poddubchak.myproject.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by @author Vladimir Poddubchak @date 10.10.2019.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    /**
     curl -X GET localhost:8090/users
     */
    @GetMapping
    public List<MyUser> findAllUsers() {
        return userService.findAllUsers();
    }
    /**
     curl -X GET localhost:8090/users/{userId}
     */
    @GetMapping("/{userId}")
    public MyUser findUser(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }
    /**
     curl -X POST localhost:8090/users -H "Content-type:application/json" -d "{\"login\":\"Login\",\"hashPassword\":\"123\"}"
     */
    @PostMapping
    public MyUser createUser(@RequestBody MyUser user) {
        return userService.createUser(user);
    }
    /**
     curl -X DELETE localhost:8090/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
    /**
     curl -X PUT localhost:8090/users/{userId} -H "Content-type:application/json" -d "{\"login\":\"Login\",\"hashPassword\":\"123\"}"
     */
    @PutMapping("/{userId}")
    public MyUser updateUser(@RequestBody MyUser user, @PathVariable Long userId) {
        return userService.updateUser(user, userId);
    }
    /**
     curl -X PATCH localhost:8090/users/{userId} -H "Content-type:application/json" -d "{\"login\":\"Login\",\"hashPassword\":\"123\"}"
     */
    @PatchMapping("/{userId}")
    public MyUser updateUser(@RequestBody Map<String, String> updates, @PathVariable Long userId) {
        return userService.updateUser(updates, userId);
    }
}
