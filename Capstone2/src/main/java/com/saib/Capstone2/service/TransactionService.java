package com.saib.Capstone2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.saib.Capstone2.models.Transaction;
import com.saib.Capstone2.repository.TransactionRepository;
import com.saib.Capstone2.util.Results;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	public TransactionService(@Qualifier("Transaction")TransactionRepository repository) {
		this.transactionRepository = repository;
		// TODO Auto-generated constructor stub
	}


	public List<Transaction> getAllTransaction()
	{
		List<Transaction> list=transactionRepository.findAll();
		return list;
	
		
	}
	
	
	public Transaction getTransactionByTransaction_id(long transaction_id)
	{
		Optional<Transaction> optional=transactionRepository.findById(transaction_id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Number:"+transaction_id+"doesn't exist");
		}
		
	}
	public Transaction getTransactionByTransaction_type(String transaction_type)
	{
		Optional<Transaction> optional=transactionRepository.findByType(transaction_type);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Number:"+transaction_type+"doesn't exist");
		}
		
	}
	
	public String addTransaction(Transaction transaction)
	{
		String result="";
		Transaction storedTransaction=transactionRepository.save(transaction);
		if(storedTransaction!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction not created");
		}
		
		return result;
	}
	

}
