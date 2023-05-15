package com.aninfo.service;

import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.TransactionType;
import com.aninfo.model.Transactions;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    final AccountService accountService;

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Transactions createTransaction(Transactions transaction) {
        if (transaction.getType() == TransactionType.WITHDRAW) {
            accountService.withdraw(transaction.getCbu(),transaction.getAmount());
        }
        else if (transaction.getType() == TransactionType.DEPOSIT) {
            accountService.deposit(transaction.getCbu(), transaction.getAmount());
        } else {
            throw new InvalidTransactionTypeException("Invalid transaction type: " + transaction.getType());
        }
        return transactionRepository.save(transaction);
    }

    public Collection<Transactions> getTransactions() {
        return transactionRepository.findAll();
    }
}
