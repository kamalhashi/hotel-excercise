package com.tripworld.hotel.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms")
@Builder
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "room_id")
    private Long roomId;
    @NotNull
    @Size(max = 500)
    private String description;
    @ManyToOne
    @JoinColumn(name = "hotel_id",insertable = false, updatable = false)
    private Hotel hotel;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<RoomAmenities> roomAmenities;

}
