package com.tripworld.hotel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Currency;


@Entity
@Table(name = "hotel_amenities")
@EqualsAndHashCode(callSuper = true)
@Data
public class HotelAmenity extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_ame_id")
    private Long hotelAmeId;
    private BigDecimal amount;
    private boolean chargeable;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    AmenityMst amenityMst;

}