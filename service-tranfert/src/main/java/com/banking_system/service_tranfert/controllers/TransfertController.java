package com.banking_system.service_tranfert.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_tranfert.events.TransfertProducer;
import com.banking_system.service_tranfert.services.TransfertService;

@RestController
@RequestMapping("/api")
public class TransfertController {
    
    @Autowired
    TransfertService transfertService;

    @PostMapping("/money/transfert")
    public void transfertMoney(@RequestBody TransfertProducer transfertProducer) throws IOException {
        transfertService.transfertMoney(transfertProducer);
    }
}
