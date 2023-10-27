package com.infy.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infy.entities.Hotel;
import com.infy.entities.Rating;
import com.infy.entities.User;
import com.infy.exceptions.ResourceNotFoundException;
import com.infy.feign.HotelFeign;
import com.infy.feign.RatingFeign;
import com.infy.repositories.UserRepository;
import com.infy.services.UserService;
import com.netflix.discovery.converters.Auto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelFeign hotelFeign;
    @Autowired
    private RatingFeign ratingFeign;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
         List<User> users = userRepository.findAll();
         List<User> userList = new ArrayList<>();
         for(User user : users) {
             List<Rating> ratings = ratingFeign.getRatings(user.getUserId());
             System.out.println("Size of rating::"+ratings.size());
             List<Rating> ratingList = ratings.stream().map(rating -> {
             	Hotel hotel = hotelFeign.getHotel(rating.getHotelId());
                 rating.setHotel(hotel);
                 return rating;
             }).collect(Collectors.toList());

             user.setRatings(ratingList);
             userList.add(user);
         }
         return userList;
    }

    //get single user
    @Override
    public User getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        List<Rating> ratings = ratingFeign.getRatings(user.getUserId());
        System.out.println("Size of rating::"+ratings.size());
        List<Rating> ratingList = ratings.stream().map(rating -> {
        	Hotel hotel = hotelFeign.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
