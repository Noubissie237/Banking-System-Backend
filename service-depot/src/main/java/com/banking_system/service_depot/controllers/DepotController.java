package com.banking_system.service_depot.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_depot.events.DepotProducer;
import com.banking_system.service_depot.services.DepotService;

@RestController
@RequestMapping("/api")
public class DepotController {
    
    @Autowired
    DepotService depotService;

    @PostMapping("/money/depot")
    public void depotMoney(@RequestBody DepotProducer depotProducer) throws IOException {
        depotService.depotMoney(depotProducer);
    }
}
