package com.tripworld.hotel.service;

import com.tripworld.hotel.domain.Hotel;
import com.tripworld.hotel.dto.HotelResponseDto;
import com.tripworld.hotel.repository.HotelRepository;
import com.tripworld.hotel.helpers.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImp implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ModelConverter modelConverter;

    @Override
    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
    public List<HotelResponseDto> findAll() {
        return this.hotelRepository.findAll()
                .stream()
                .map(hotel -> this.modelConverter.convertToDto(hotel))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public HotelResponseDto saveHotel(Hotel hotel) {
        Hotel newHotel= this.hotelRepository.save(hotel);
        return this.modelConverter.convertToDto(newHotel);
    }

    @Override
    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
    public Optional<HotelResponseDto> findHotelByHotelId(Long hotelId) {
        return this.hotelRepository.findById(hotelId)
                .map(hotel -> this.modelConverter.convertToDto(hotel));
    }

    @Override
    @Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
    public Optional<HotelResponseDto> findByHotelName(String hotelName) {
        return this.hotelRepository.findOneByHotelName(hotelName)
                .map(hotel -> this.modelConverter.convertToDto(hotel));
    }

    @Override
    public void deleteHotel(Long hotelId) {
        this.hotelRepository.deleteById(hotelId);
    }


}
