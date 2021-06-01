package com.matheuscordeiro.carsasyncservices.api.services;

import com.matheuscordeiro.carsasyncservices.api.models.Car;
import com.matheuscordeiro.carsasyncservices.api.services.interfaces.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    public CompletableFuture<List<Car>> saveCars(InputStream inputStream) {
        return null;
    }

    @Override
    public List<Car> parseCSVFile(InputStream inputStream) {
        return null;
    }

    @Override
    public CompletableFuture<List<Car>> getAllCars() {
        return null;
    }
}
