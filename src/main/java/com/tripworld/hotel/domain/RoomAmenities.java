package com.tripworld.hotel.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "room_amenities")
@Builder
@Data
public class RoomAmenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_ame_id")
    private Long roomAmeId;
    private BigDecimal amount;
    private boolean chargeable;

    @ManyToOne
    @JoinColumn(name = "room_id",  nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "amenity_id",  nullable = false)
    AmenityMst amenityMst;
}
