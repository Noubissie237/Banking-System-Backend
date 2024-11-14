package com.banking_system.service_retrait.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.banking_system.service_retrait.events.RetraitProducer;
import com.banking_system.service_retrait.services.RetraitService;

@RestController
@RequestMapping("/api")
public class RetraitController {

    @Autowired
    RetraitService retraitService;

    @PostMapping("/money/retrait")
    public void retirerMoney(@RequestBody RetraitProducer event) throws IOException {
        retraitService.retirerMoney( event);
    }
}
