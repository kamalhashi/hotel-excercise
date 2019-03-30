package com.tripworld.hotel.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "amenity_mst")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmenityMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "amenity_id")
    private Long amenityId;
    @NotNull
    @Size(max = 10)
    private String shortDesc;
    @NotNull
    @Size(max = 20)
    private String description;

}
