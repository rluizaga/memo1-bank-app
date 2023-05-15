package com.aninfo.service;

import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.TransactionType;
import com.aninfo.model.Transactions;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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

    public Optional<Transactions> findById(Long transactionID) {
        return transactionRepository.findById(transactionID);
    }

    public Collection<Transactions> findByCbu(Long cbu) {
        return transactionRepository.findTransactionsByCbu(cbu);
    }

    public ResponseEntity<Void> deleteById(Long transactionID) {
        Optional<Transactions> transactionOptional = transactionRepository.findById(transactionID);
        if (!transactionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Transactions transaction = transactionOptional.get();
        if (transaction.getType() == TransactionType.WITHDRAW) {
            accountService.deposit(transaction.getCbu(), transaction.getAmount());
        } else if (transaction.getType() == TransactionType.DEPOSIT) {
            accountService.withdraw(transaction.getCbu(), transaction.getAmount());
        }
        transactionRepository.deleteById(transactionID);
        return ResponseEntity.noContent().build();
    }
}
