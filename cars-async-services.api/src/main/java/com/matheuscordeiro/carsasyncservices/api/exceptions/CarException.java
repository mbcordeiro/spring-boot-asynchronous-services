package com.matheuscordeiro.carsasyncservices.api.exceptions;

import com.matheuscordeiro.carsasyncservices.api.controllers.CarController;
import com.matheuscordeiro.carsasyncservices.api.models.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;

public class CarException {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
