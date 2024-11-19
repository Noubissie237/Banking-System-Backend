package com.banking_system.service_transactions.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking_system.service_transactions.events.RechargeTemplate;
import com.banking_system.service_transactions.models.TransactionEvent;
import com.banking_system.service_transactions.models.TransactionType;
import com.banking_system.service_transactions.services.TransactionService;

@Component
public class RechargesConsumer {
    
    @Autowired
    private TransactionService transactionService;

    @RabbitListener(queues = "rechargeByAgenceDone")
    public void receiveRechargeEvent(RechargeTemplate event) {
        TransactionEvent transac = new TransactionEvent();

        transac.setAgenceId(event.getAgence());
        transac.setNumeroSender("00000000");
        transac.setNumeroReceiver(event.getNumero());
        transac.setAmount(event.getMontant());
        transac.setTransactionType(TransactionType.RECHARGE);       
        
        transactionService.createTransactionEvent(transac);
    }
}
