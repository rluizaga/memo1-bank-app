package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionID;

    private Long cbu;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transactions(){
    }

    public Transactions(Double amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}