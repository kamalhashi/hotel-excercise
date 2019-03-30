package com.tripworld.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripworld.hotel.config.JpaEnversConfiguration;
import com.tripworld.hotel.domain.Hotel;
import com.tripworld.hotel.dto.HotelResponseDto;
import com.tripworld.hotel.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static com.tripworld.hotel.enums.UrlMappings.HOTEL_PATH;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.Is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HotelControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelService hotelService;

    @Test
    public void createHotel_thenStatus201() throws Exception {
        HotelResponseDto hotelResponseDto = HotelResponseDto.builder()
                        .hotelId(123L)
                        .hotelName("Burj Al Arab")
                        .description("The hotel is located near the beach Jumairah")
                        .cityCode("W1245")
                        .build();


        Hotel hotel1 = Hotel.builder()
                .hotelName("Burj Al Khalifa")
                .description("Located in heart of Dubai, near the beach")
                .cityCode("UAE")
                .build();
        when(hotelService.saveHotel(any(Hotel.class))).thenReturn(hotelResponseDto);

        MvcResult result = mockMvc.perform(post(HOTEL_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(hotel1)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString(HOTEL_PATH + "/123")))
                .andReturn();
    }

    @Test
    public void createHotelTwice_thenStatus302() throws Exception {
        HotelResponseDto hotelResponseDto = HotelResponseDto.builder()
                        .hotelId(123L)
                        .hotelName("Burj Al Arab")
                        .description("The hotel is located near the beach Jumairah")
                        .cityCode("W1245")
                        .build();


        Hotel hotel1 = Hotel.builder()
                .hotelName("Burj Al Khalifa")
                .description("Located in heart of Dubai, near the beach")
                .cityCode("UAE")
                .build();
        when(hotelService.saveHotel(any(Hotel.class))).thenReturn(hotelResponseDto);

        when(hotelService.findByHotelName(any(String.class))).thenReturn(Optional.of(hotelResponseDto));

        mockMvc.perform(post(HOTEL_PATH)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(hotel1)))
                .andExpect(status().isFound())
                .andReturn();

    }


    @Test
    public void getAllHotels_thenStatus200() throws Exception {

        HotelResponseDto hotelResponseDto = HotelResponseDto.builder()
                        .hotelId(123L)
                        .hotelName("Burj Al Arab")
                        .description("The hotel is located near the beach Jumairah")
                        .cityCode("W1245")
                        .build();

        when(hotelService.findAll()).thenReturn(List.of(hotelResponseDto));

        mockMvc.perform(get(HOTEL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].hotelId", Is.is(123)));
    }


    @Test
    public void getHotelById() {
    }

    @Test
    public void deleteHotel() {
    }

}