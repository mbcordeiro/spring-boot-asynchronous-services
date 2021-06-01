package com.matheuscordeiro.carsasyncservices.api.services.interfaces;

import com.matheuscordeiro.carsasyncservices.api.models.Car;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    CompletableFuture<List<Car>> saveCars(final InputStream inputStream);

    List<Car> parseCSVFile(final InputStream inputStream);

    CompletableFuture<List<Car>> getAllCars();
}
