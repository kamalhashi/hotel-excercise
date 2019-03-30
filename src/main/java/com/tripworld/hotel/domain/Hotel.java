package com.tripworld.hotel.domain;



import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "hotels")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "hotel_id")
    private Long hotelId;
    @NotNull
    @Size(max = 50)
    private String hotelName;
    @NotNull
    @Size(max = 500)
    private String description;
    @NotNull
    @Size(max = 3)
    private String cityCode;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //it’s a good practice to mark many-to-one side as the owning side.
    @JoinColumn(name = "hotel_id")
    private Set<Room> rooms;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Set<HotelAmenity> hotelAmenities;

}
