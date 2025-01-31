package com.aninfo.repository;

import com.aninfo.model.Account;
import com.aninfo.model.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transactions, Long> {

    Collection<Transactions> findTransactionsByCbu(Long cbu);
    @Override
    List<Transactions> findAll();

}
