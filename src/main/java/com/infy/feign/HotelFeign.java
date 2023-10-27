package com.infy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infy.entities.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelFeign {

	@GetMapping(value = "hotels/{hotelId}")
	Hotel getHotel(@PathVariable("hotelId") Integer hotelId);
}
