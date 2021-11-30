package com.saib.Capstone2.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.Capstone2.models.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /*
     *to find transaction by its type
     * */
	public List<Transaction> findTransactionByTransactionType(String transactionType);
	
	 /*
     *to find transaction by its date
     * */
	public List<Transaction> findTransactionByDate(LocalDateTime date);
	
	/*
	 *to find transaction by its type and date
	 * */
	 public List<Transaction> findTransactionByDateAndTransactionType(LocalDateTime date,String transactionType);
	

}
