package com.matheuscordeiro.springbootasynchronousservice.services;

import com.matheuscordeiro.springbootasynchronousservice.models.EmployeeAddresses;
import com.matheuscordeiro.springbootasynchronousservice.models.EmployeeNames;
import com.matheuscordeiro.springbootasynchronousservice.models.EmployeePhone;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private static Logger LOG = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeNames> getEmployeeName() throws InterruptedException {
        LOG.info("getEmployeeName Starts");
        EmployeeNames employeeNameData = restTemplate.getForObject("http://localhost:8080/name", EmployeeNames.class);

        LOG.info("employeeNameData, {}", employeeNameData);
        Thread.sleep(1000);
        LOG.info("employeeNameData completed");

        return CompletableFuture.completedFuture(employeeNameData);
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeeAddresses> getEmployeeAddress() throws InterruptedException {
        LOG.info("getEmployeeAddress Starts");
        EmployeeAddresses employeeAddressData = restTemplate.getForObject("http://localhost:8080/address", EmployeeAddresses.class);

        LOG.info("employeeAddressData, {}", employeeAddressData);
        Thread.sleep(1000);
        LOG.info("employeeAddressData completed");

        return CompletableFuture.completedFuture(employeeAddressData);
    }

    @Async("asyncExecutor")
    public CompletableFuture<EmployeePhone> getEmployeePhone() throws InterruptedException
    {
        LOG.info("getEmployeePhone Starts");
        EmployeePhone employeePhoneData = restTemplate.getForObject("http://localhost:8080/phone", EmployeePhone.class);

        LOG.info("employeePhoneData, {}", employeePhoneData);
        Thread.sleep(1000L);	//Intentional delay
        LOG.info("employeePhoneData completed");
        return CompletableFuture.completedFuture(employeePhoneData);
    }
}
