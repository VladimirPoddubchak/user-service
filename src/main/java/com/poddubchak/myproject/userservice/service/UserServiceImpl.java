package com.poddubchak.myproject.userservice.service;

import com.poddubchak.microservicies.model.user.MyUser;
import com.poddubchak.myproject.userservice.exception.UserNotFoundException;
import com.poddubchak.myproject.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by @author Vladimir Poddubchak @date 10.10.2019.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public List<MyUser> findAllUsers() {
        return (List)userRepository.findAll();
    }

    @Override
    public MyUser findUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found. ID: " + userId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MyUser createUser(MyUser user) {
        final MyUser newUser = MyUser.builder()
                .login(user.getLogin())
                .hashPassword(user.getHashPassword())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }else{
            throw new UserNotFoundException("User not found. ID: " + userId);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MyUser updateUser(Map<String, String> updates, long userId) {
        final MyUser user = findUserById(userId);
        updates.keySet()
                .forEach(key -> {
                    switch (key) {
                        case "login":
                            user.setLogin(updates.get(key));
                            break;
                        case "hashPassword":
                            user.setHashPassword(updates.get(key));
                    }
                });
        return userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MyUser updateUser(MyUser newUser, long userId) {
        final MyUser user = findUserById(userId);
        user.setLogin(newUser.getLogin());
        user.setHashPassword(newUser.getHashPassword());
        return userRepository.save(user);
    }

}
