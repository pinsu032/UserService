package com.infy.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infy.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingFeign {

	@GetMapping(value = "ratings/users/{userId}")
	List<Rating> getRatings(@PathVariable("userId") Integer userId);
}
