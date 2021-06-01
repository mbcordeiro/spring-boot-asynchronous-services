package com.matheuscordeiro.carsasyncservices.api.repositories;

import com.matheuscordeiro.carsasyncservices.api.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
