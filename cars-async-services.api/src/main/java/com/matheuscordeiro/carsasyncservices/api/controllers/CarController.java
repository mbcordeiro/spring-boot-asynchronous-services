package com.matheuscordeiro.carsasyncservices.api.controllers;

import com.matheuscordeiro.carsasyncservices.api.models.Car;
import com.matheuscordeiro.carsasyncservices.api.services.interfaces.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @PostMapping(consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity uploadFile(@RequestParam(value = "files") MultipartFile[] files) {
        try {
            for (final MultipartFile file : files) {
                carService.saveCars(file.getInputStream());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody CompletableFuture<ResponseEntity> getAllCars() throws InterruptedException {
        return carService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetCarFailure);
    }

    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
