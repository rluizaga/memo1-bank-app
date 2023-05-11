package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Transactions;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    final AccountService accountService;

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Transactions createTransaction(Transactions transaction) {
        if (Objects.equals(transaction.getType(), "withdraw")) {
            accountService.withdraw(transaction.getCbu(),transaction.getAmount());
        }
        if (Objects.equals(transaction.getType(), "deposit")) {
            accountService.deposit(transaction.getCbu(), transaction.getAmount());
        }
        return transactionRepository.save(transaction);
    }

    public Collection<Transactions> getTransactions() {
        return transactionRepository.findAll();
    }
}
