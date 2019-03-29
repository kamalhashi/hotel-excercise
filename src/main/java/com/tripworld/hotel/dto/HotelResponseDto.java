package com.tripworld.hotel.dto;

import com.tripworld.hotel.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDto {
    @NotBlank
    private Long hotelId;
    @NotBlank
    private String hotelName;
    private String description;
    private String cityCode;
    private List<Room> rooms;
}
