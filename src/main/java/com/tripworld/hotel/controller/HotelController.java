package com.tripworld.hotel.controller;

import com.tripworld.hotel.dto.CreateHotelDto;
import com.tripworld.hotel.dto.HotelResponseDto;
import com.tripworld.hotel.exception.HotelExistException;
import com.tripworld.hotel.exception.HotelNotFoundException;
import com.tripworld.hotel.service.HotelService;
import com.tripworld.hotel.helpers.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;
import static com.tripworld.hotel.enums.UrlMappings.HOTEL_PATH;

@RestController
@RequestMapping(HOTEL_PATH)
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    ModelConverter modelConverter;


    //Get all hotels
    @GetMapping
    public List<HotelResponseDto> getAllHotels() {
        return hotelService.findAll();
    }

    // Create a new Note
    @PostMapping
    public ResponseEntity<HotelResponseDto> createHotel(@Valid @RequestBody CreateHotelDto createHotelDto, UriComponentsBuilder uriBuilder) {
        this.hotelService.findByHotelName(createHotelDto.getHotelName())
                .ifPresent(s -> {
                    throw new HotelExistException("HotelName Already exist:" + createHotelDto.getHotelName());
                });
        HotelResponseDto hotelResponseDto = this.hotelService.saveHotel(this.modelConverter.convertToEntity(createHotelDto));
        return ResponseEntity
                .created(uriBuilder.path(HOTEL_PATH +"/" +hotelResponseDto.getHotelId())
                        .buildAndExpand()
                        .toUri())
                .build();

    }

    // Get a Single Hotel
    @GetMapping("{id}")
    public ResponseEntity<HotelResponseDto> getHotelById(@PathVariable(value = "id") Long hotelId) {
        return this.hotelService.findHotelByHotelId(hotelId)
                .map(hotelResponseDto -> ResponseEntity.ok().body(hotelResponseDto))
                .orElseThrow(() -> new HotelNotFoundException("Hotel", "id", hotelId));
    }

    // Create a new Note
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable(value = "id") Long hotelId) {
       return this.hotelService.findHotelByHotelId(hotelId).map( hotelResponseDto -> {
            this.hotelService.deleteHotel(hotelId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new HotelNotFoundException("Hotel not found:", "id", hotelId));
    }


}
