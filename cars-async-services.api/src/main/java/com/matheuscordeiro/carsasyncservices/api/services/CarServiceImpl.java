package com.matheuscordeiro.carsasyncservices.api.services;

import com.matheuscordeiro.carsasyncservices.api.models.Car;
import com.matheuscordeiro.carsasyncservices.api.repositories.CarRepository;
import com.matheuscordeiro.carsasyncservices.api.services.interfaces.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public CompletableFuture<List<Car>> saveCars(InputStream inputStream) throws IOException {
        final long start = System.currentTimeMillis();

        List<Car> cars = parseCSVFile(inputStream);

        LOGGER.info("Saving a list of cars of size {} records", cars.size());

        cars = carRepository.saveAll(cars);

        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }

    @Override
    public List<Car> parseCSVFile(InputStream inputStream) throws IOException {
        final List<Car> cars=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(";");
                    final Car car=new Car();
                    car.setManufacturer(data[0]);
                    car.setModel(data[1]);
                    car.setType(data[2]);
                    cars.add(car);
                }
                return cars;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new IOException("Failed to parse CSV file {}", e);
        }
    }

    @Async
    @Override
    public CompletableFuture<List<Car>> getAllCars() {
        LOGGER.info("Request to get a list of cars");

        final List<Car> cars = carRepository.findAll();
        return CompletableFuture.completedFuture(cars);
    }
}
