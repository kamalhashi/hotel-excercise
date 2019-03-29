package com.tripworld.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class HotelExistException extends RuntimeException {

    public HotelExistException(String message){
        super(message);
    }

}
