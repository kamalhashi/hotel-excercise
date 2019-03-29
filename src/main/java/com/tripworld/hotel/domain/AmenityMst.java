package com.tripworld.hotel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "amenity_mst")
@Data
@EqualsAndHashCode(callSuper = true)
public class AmenityMst extends  AuditModel {

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
