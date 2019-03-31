package com.tripworld.hotel.repository;

import com.tripworld.hotel.config.JpaEnversConfiguration;
import com.tripworld.hotel.domain.*;
import com.tripworld.hotel.dto.HotelResponseDto;
import com.tripworld.hotel.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = JpaEnversConfiguration.class)
public class HotelRepositoryTest {

    @Autowired
    HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Rollback(true)
    public void should_createHotel_without_rooms() {
        Hotel hotel1 =  Hotel.builder()
                .hotelName("Burj Al Khalifa")
                .description("Located in heart of Dubai, near the beach")
                .cityCode("UAE")
                .build();

        entityManager.persistAndFlush(hotel1);


        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());
    }

    @Test
    @Rollback(true)
    public void should_createHotel_WithTwoRooms() {

        Room room1 =  Room.builder()
                .description("Single Room")
                .build();


        Room room2 = Room.builder()
                .description("Double Room")
                .build();

        Hotel hotel1 = Hotel.builder()
                .hotelName("Burj Al Arab")
                .description("Located in heart of Dubai, near the beach")
                .cityCode("UAE")
                .rooms(Set.of(room1, room2))
                .build();

        entityManager.persistAndFlush(hotel1);


        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());

    }


    @Test
    @Rollback(true)
    @Transactional
    public void should_createHotel_WithRooms_WithHotelAmneties() {


        HotelAmenity hotelAmenity = HotelAmenity.builder()
                .amenityMst(AmenityMst.builder().amenityId(1L).build())
                .chargeable(false)
                .build();

        Hotel hotel1 =  Hotel.builder()
                .hotelName("Address Hotel")
                .description("Located in heart of Dubai, near the beach")
                .cityCode("UAE")
                .hotelAmenities(Set.of(hotelAmenity))
                .build();

        entityManager.persistAndFlush(hotel1);



        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());

    }


    @Test
    @Rollback(false)
    public void should_createHotel_WithHotelAmneties_WithRoom_WithRoomAmneties() {

        /**
         * We should have some dummy data in amenity_mst table.
         * Fetch one of the rows in amenity_mst table and assigned to hotel
         */


        //Create hotel and assign amenity_mst to that hotel.
        HotelAmenity hotelAmenity =  HotelAmenity.builder()
                .amenityMst(AmenityMst.builder().amenityId(1L).build())
                .chargeable(true)
                .amount(new BigDecimal(34.5))
                .build();

        //Create Room amenity
        RoomAmenities roomAmenities = RoomAmenities.builder()
                .amenityMst(AmenityMst.builder().amenityId(1L).build())
                .chargeable(true)
                .amount(new BigDecimal(34.5))
                .build();


        //Create room and assign to amenity
         Room room1 = Room.builder()
                 .description("Single Room without amenity")
                 .build();

        Room room2 = Room.builder()
                .description("Double Room with amenity WIFI")
                .roomAmenities(Set.of(roomAmenities))
                .build();

        //CREATE HOTEL and assign hotel_Amenities and rooms
        Hotel hotel1 =  Hotel.builder()
                .hotelName("Hilton Hotel")
                .description("Located in heart of Dubai, near the beach")
                .cityCode("UAE")
                .hotelAmenities(Set.of(hotelAmenity))
                .rooms(Set.of(room1,room2))
                .build();
        entityManager.persistAndFlush(hotel1);

        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());

    }

}