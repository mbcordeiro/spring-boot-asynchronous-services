package com.matheuscordeiro.carsasyncservices.api.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(nullable=false)
    private String manufacturer;

    @NotNull
    @Column(nullable=false)
    private String model;

    @NotNull
    @Column(nullable=false)
    private String type;
}
