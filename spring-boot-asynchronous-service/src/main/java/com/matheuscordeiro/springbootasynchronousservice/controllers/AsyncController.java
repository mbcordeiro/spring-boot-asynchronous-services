package com.matheuscordeiro.springbootasynchronousservice.controllers;

import com.matheuscordeiro.springbootasynchronousservice.models.EmployeeAddresses;
import com.matheuscordeiro.springbootasynchronousservice.models.EmployeeNames;
import com.matheuscordeiro.springbootasynchronousservice.models.EmployeePhone;
import com.matheuscordeiro.springbootasynchronousservice.services.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class AsyncController {
    private static Logger LOG = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    AsyncService service;

    @GetMapping("async")
    public void getAsync() throws InterruptedException, ExecutionException {
        LOG.info("get Async start");

        CompletableFuture<EmployeeAddresses> employeeAddress = service.getEmployeeAddress();
        CompletableFuture<EmployeeNames> employeeName = service.getEmployeeName();
        CompletableFuture<EmployeePhone> employeePhone = service.getEmployeePhone();

        CompletableFuture.allOf(employeeAddress, employeeName, employeePhone);

        LOG.info("EmployeeAddress--> " + employeeAddress.get());
        LOG.info("EmployeeName--> " + employeeName.get());
        LOG.info("EmployeePhone--> " + employeePhone.get());
    }
}
