package com.matheuscordeiro.carsasyncservices.api.services.interfaces;

import com.matheuscordeiro.carsasyncservices.api.models.Car;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    CompletableFuture<List<Car>> saveCars(final InputStream inputStream) throws IOException, InterruptedException;

    List<Car> parseCSVFile(final InputStream inputStream) throws Exception;

    CompletableFuture<List<Car>> getAllCars() throws InterruptedException;
}
