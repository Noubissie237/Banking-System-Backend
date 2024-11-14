package com.banking_system.service_transactions.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.banking_system.service_transactions.events.TransactionTemplate;
import com.banking_system.service_transactions.events.RetraitTemplate;
import com.banking_system.service_transactions.models.TransactionEvent;
import com.banking_system.service_transactions.models.TransactionType;
import com.banking_system.service_transactions.services.TransactionService;

@Component
public class TransactionsConsumer {
    
    @Autowired
    private TransactionService transactionService;

    @RabbitListener(queues = "transfertMoneyQueue1")
    public void receiveTransfertEvent(TransactionTemplate event) {
        TransactionEvent transac = new TransactionEvent();

        transac.setAgenceId(event.getAgence());
        transac.setNumeroSender(event.getNumero_source());
        transac.setNumeroReceiver(event.getNumero_cible());
        transac.setAmount(event.getMontant());
        transac.setTransactionType(TransactionType.TRANSFERT);

        transactionService.createTransactionEvent(transac);
    }

    @RabbitListener(queues = "retraitMoneyQueueForTransactions")
    public void receiveRetraitEvent(RetraitTemplate event) {
        TransactionEvent transac = new TransactionEvent();

        transac.setAgenceId(event.getAgence());
        transac.setNumeroSender(event.getNumero_cible());
        transac.setNumeroReceiver(event.getNumero_agent());
        transac.setAmount(event.getMontant());
        transac.setTransactionType(TransactionType.RETRAIT);

        transactionService.createTransactionEvent(transac);
    }
}
