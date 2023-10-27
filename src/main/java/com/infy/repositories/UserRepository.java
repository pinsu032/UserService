package com.infy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>
{
    //if you want to implement any custom method or query
    //write
}
