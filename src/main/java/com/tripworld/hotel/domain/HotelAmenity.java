package com.tripworld.hotel.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "hotel_amenities")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelAmenity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "hotel_ame_id")
    private Long hotelAmeId;
    private BigDecimal amount;
    private boolean chargeable;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "amenity_id",  nullable = false)
    private AmenityMst amenityMst;

}