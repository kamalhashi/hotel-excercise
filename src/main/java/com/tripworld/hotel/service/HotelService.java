package com.tripworld.hotel.service;

import com.tripworld.hotel.domain.Hotel;
import com.tripworld.hotel.dto.HotelResponseDto;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    public List<HotelResponseDto> findAll();
    public HotelResponseDto saveHotel(Hotel hotel);
    public Optional<HotelResponseDto> findHotelByHotelId(Long hotelId);
    public Optional<HotelResponseDto> findByHotelName(String hotelName);
    public void deleteHotel(Long hotelId);
}
