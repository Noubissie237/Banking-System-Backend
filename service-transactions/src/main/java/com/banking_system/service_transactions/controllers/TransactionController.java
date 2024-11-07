package com.banking_system.service_transactions.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_system.service_transactions.models.TransactionEvent;
import com.banking_system.service_transactions.services.TransactionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }

    @PostMapping("/new")
    public String createTransaction(@RequestBody TransactionEvent transactionEvent) {
        try {
            transactionService.createTransactionEvent(transactionEvent);
            return transactionEvent.getTransactionType()+" effectué avec succès !";
        } catch (Exception e) {
            e.printStackTrace();
            return "Transaction Failed";
        }
    }

    @GetMapping("/get/{number}")
    public List<TransactionEvent> getTransactionsByNumber(@PathVariable String number) {
        return transactionService.getTransactionByNumber(number);
    }


    @GetMapping("/get-all")
    public List<TransactionEvent> getTransactions() {
        return transactionService.getAllTransactions();
    }
    
}
