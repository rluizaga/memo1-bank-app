package com.aninfo.service;

import com.aninfo.model.Account;
import com.aninfo.model.Transactions;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transactions createTransaction(Transactions transactions) {
        return transactionRepository.save(transactions);
    }

    public Collection<Transactions> getTransactions() {
        return transactionRepository.findAll();
    }
}
