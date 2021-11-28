package com.saib.Capstone2.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.saib.Capstone2.models.Transaction;

@Qualifier("Transaction")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {



	Optional<Transaction> findByType(String transaction_type);

}
