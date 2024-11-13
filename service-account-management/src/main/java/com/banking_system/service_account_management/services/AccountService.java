package com.banking_system.service_account_management.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking_system.service_account_management.event.RechargeEventConsumer;
import com.banking_system.service_account_management.event.TransfertEventConsumer;
import com.banking_system.service_account_management.event.RetraitEventConsumer;
import com.banking_system.service_account_management.models.Account;
import com.banking_system.service_account_management.models.AgentAccount;
import com.banking_system.service_account_management.repositories.AccountRepository;
import com.banking_system.service_account_management.repositories.AgentAccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AgentAccountRepository agentAccountRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createAccount(Account account) {
        try {
            accountRepository.save(account);
            String message = "Compte créé avec succès ! Vous pouvez à présent profiter de nos services 😀 ";
            rabbitTemplate.convertAndSend("clientExchange", "client-account.create", message);
        } catch (Exception e) {
            throw new RuntimeException("Account Creation Error : ",e);
        }
    }

    public void createAgentAccount(AgentAccount account) {
        try {
            agentAccountRepository.save(account);
            String message = "Compte Agent créé avec succès ! Vous pouvez à présent profiter de nos services 😀 ";
            rabbitTemplate.convertAndSend("clientExchange", "agent-account.create", message);
        } catch (Exception e) {
            throw new RuntimeException("Account Creation Error : ",e);
        }
    }

    public void incrementSolde(Account account, Double montant) {
        if (account == null) return;
        account.setSolde(account.getSolde() + montant);
    }
    
    public void decrementSolde(Account account, Double montant) {
        if (account == null) return;
        account.setSolde(account.getSolde() - montant);
    }

    public Account findAccountByNumber(String numero) {
        return accountRepository.findByNumber(numero).orElseThrow(null);
    }

    @Transactional
    public void makeTransfert(TransfertEventConsumer transfert) {
        Account cible = findAccountByNumber(transfert.getNumero_cible());
        Account source = findAccountByNumber(transfert.getNumero_source());
        incrementSolde(cible, transfert.getMontant());
        decrementSolde(source, transfert.getMontant() + transfert.getFrais());
    }

    @Transactional
    public void makeRetrait( RetraitEventConsumer retrait) {
        Account cible = findAccountByNumber(retrait.getNumero_cible());
        Account agent = findAccountByNumber(retrait.getNumero_agent());
        Double agentGain = (retrait.getFrais() * 0.25 ) ;
        incrementSolde(agent, retrait.getMontant() + agentGain);
        decrementSolde(cible, retrait.getMontant() + retrait.getFrais());
    
    @Transactional
    public void makeRecharge(RechargeEventConsumer recharge) {
        Account account = findAccountByNumber(recharge.getNumero());
        incrementSolde(account, recharge.getMontant());

        rabbitTemplate.convertAndSend("transactionExchange", "recharge.send.agence", recharge);
    }
}
