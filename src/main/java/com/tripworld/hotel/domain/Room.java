package com.tripworld.hotel.domain;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "rooms")
@Data
@EqualsAndHashCode(callSuper = true)
public class Room extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @NotNull
    @Size(max = 500)
    private String description;
    @ManyToOne
    @JoinColumn(name = "hotel_id",insertable = false, updatable = false)
    private Hotel hotel;
}
