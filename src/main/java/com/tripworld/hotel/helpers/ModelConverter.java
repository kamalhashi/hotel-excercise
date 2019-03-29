package com.tripworld.hotel.helpers;

import com.tripworld.hotel.domain.Hotel;
import com.tripworld.hotel.dto.CreateHotelDto;
import com.tripworld.hotel.dto.HotelResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelConverter {
    @Autowired
    private ModelMapper modelMapper;

    /****
     * Hotel data transfer response
     * @param hotel
     * @return
     */
    public HotelResponseDto convertToDto(Hotel hotel) {
        HotelResponseDto hotelDto = modelMapper.map(hotel, HotelResponseDto.class);
        return hotelDto;
    }

    /**
     * convert Data transfer to entity
     *
     * @param createHotelDto
     * @return
     */
    public Hotel convertToEntity(CreateHotelDto createHotelDto) {
        Hotel hotel = modelMapper.map(createHotelDto, Hotel.class);
        return hotel;
    }
}
