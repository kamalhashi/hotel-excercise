package com.tripworld.hotel.repository;

import com.tripworld.hotel.config.MockConfig;
import com.tripworld.hotel.domain.AmenityMst;
import com.tripworld.hotel.domain.Hotel;
import com.tripworld.hotel.domain.HotelAmenity;
import com.tripworld.hotel.domain.Room;
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
    public void testCreateHotel_Without_Rooms() {
        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Burj Al Arab");
        hotel1.setDescription("Located in heart of Dubai, near the beach");
        hotel1.setCityCode("UAE");

        entityManager.persistAndFlush(hotel1);

        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());
    }

    @Test
    @Rollback(true)
    public void testCreateHotel_WithRooms() {

        Room room1 = new Room();
        room1.setDescription("Double Room");


        Room room2 = new Room();
        room2.setDescription("Double Room");

        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Burj Al Arab1");
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
    @Rollback(false)
    public void testCreateHotel_WithRooms_WithAmneties() {


        AmenityMst amenityMst = (AmenityMst) entityManager.find(AmenityMst.class, 1L);

        HotelAmenity hotelAmenity = new HotelAmenity();
        hotelAmenity.setAmenityMst(amenityMst);
        hotelAmenity.setChargeable(false);

        Hotel hotel1 = new Hotel();
        hotel1.setHotelName("Burj Al Arab1");
        hotel1.setDescription("Located in heart of Dubai, near the beach");
        hotel1.setCityCode("UAE");

        hotel1.setHotelAmenities(List.of(hotelAmenity));

        entityManager.persistAndFlush(hotel1);

        // is hotel and room inserted ?
        Optional<HotelResponseDto> found = hotelService.findByHotelName(hotel1.getHotelName());
        assertEquals(found.get().getHotelName(), hotel1.getHotelName());

    }




}