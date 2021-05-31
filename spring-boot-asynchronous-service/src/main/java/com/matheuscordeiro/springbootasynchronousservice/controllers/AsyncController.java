package com.matheuscordeiro.springbootasynchronousservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    private static Logger LOG = LoggerFactory.getLogger(AsyncController.class);

    @GetMapping("async")
    public void getAsync() {
        LOG.info("get Async start");
    }
}
