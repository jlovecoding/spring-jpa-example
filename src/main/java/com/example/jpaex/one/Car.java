package com.example.jpaex.one;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private String carId;

    private String model;
}
