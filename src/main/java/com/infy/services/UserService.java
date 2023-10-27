package com.infy.services;

import java.util.List;

import com.infy.entities.User;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId

    User getUser(Integer userId);

    //TODO: delete
    //TODO: update


}
