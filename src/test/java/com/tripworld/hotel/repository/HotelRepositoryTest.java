package com.tripworld.hotel.repository;

import com.tripworld.hotel.config.MockConfig;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = MockConfig.class)
public class HotelRepositoryTest {

    @Autowired
    HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Rollback(true)
    public void createHotel_Without_Rooms() {
        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Burj Al Khalifa");
        hotel1.setDescription("Located in heart of Dubai, near the beach");
        hotel1.setCityCode("UAE");

        entityManager.persistAndFlush(hotel1);

        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());
    }

    @Test
    @Rollback(true)
    public void createHotel_WithRoomsOnly() {

        Room room1 = new Room();
        room1.setDescription("Double Room");


        Room room2 = new Room();
        room2.setDescription("Double Room");

        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Burj Al Arab");
        hotel1.setDescription("Located in heart of Dubai, near the beach");
        hotel1.setCityCode("UAE");

        hotel1.setRooms(List.of(room1, room2));

        entityManager.persistAndFlush(hotel1);

        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());
        assertEquals(found.get().getRooms().get(0).getDescription(), room1.getDescription());

    }


    @Test
    @Rollback(true)
    public void testCreateHotel_WithRooms_WithHotelAmneties() {


        AmenityMst amenityMst = (AmenityMst) entityManager.find(AmenityMst.class, 1L);

        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setAmenityMst(amenityMst);
        hotelAmenity.setChargeable(false);
        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Address Hotel");
        hotel1.setDescription("Located in heart of Dubai, near the beach");
        hotel1.setCityCode("UAE");

        hotel1.setHotelAmenities(List.of(hotelAmenity));

        entityManager.persistAndFlush(hotel1);

        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());

    }


    @Test
    @Rollback(true)
    public void createHotel_WithHotelAmneties_WithRoom_WithRoomAmneties() {

        /**
         * We should have some dummy data in amenity_mst table.
         * Fetch one of the rows in amenity_mst table and assigned to hotel
         */
        AmenityMst amenityMst = (AmenityMst) entityManager.find(AmenityMst.class, 1L);

        //Create hotel and assign amenity_mst to that hotel.
        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setAmenityMst(amenityMst);
        hotelAmenity.setChargeable(false);

        //Create Room amenity
        RoomAmenities roomAmenities = new RoomAmenities();
        roomAmenities.setAmenityMst(amenityMst);
        roomAmenities.setChargeable(true);
        roomAmenities.setAmount(new BigDecimal(34.5));


        //Create room and assign to amenity
         Room room1 = new Room();
         room1.setDescription("Single Room without amenity");

        Room room2 = new Room();
        room2.setDescription("Double Room with amenity WIFI");
        room2.setRoomAmenities(List.of(roomAmenities));

        //CREATE HOTEL and assign hotel_Amenities and rooms
        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Hilton Hotel");
        hotel1.setDescription("Located in heart of Dubai, near the beach");
        hotel1.setCityCode("UAE");
        hotel1.setHotelAmenities(List.of(hotelAmenity));

        hotel1.setRooms(List.of(room1,room2));

        entityManager.persistAndFlush(hotel1);

        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());

    }




}