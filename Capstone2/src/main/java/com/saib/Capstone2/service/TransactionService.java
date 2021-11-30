package com.saib.Capstone2.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saib.Capstone2.models.Account;
import com.saib.Capstone2.models.Transaction;
import com.saib.Capstone2.repository.TransactionRepository;
import com.saib.Capstone2.util.Results;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	
    /*
     * Get All The Transaction
     * 
     * */
	public List<Transaction> getAllTransaction()
	{
		List<Transaction> list=transactionRepository.findAll();
		return list;
	
		
	}
	
	/*
	 * Get The Transaction By Id 
	 * */
	
	public Transaction getTransactionByTransactionId(long transactionId)
	{
		Optional<Transaction> optional=transactionRepository.findById(transactionId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Number:"+transactionId+"doesn't exist");
		}
		
	}
	/*
	 * Add Transaction 
	 */
	
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
         
	/*
	 * Get Transaction By Transaction Type 
	 * 
	 * */
	
	public List<Transaction> getTransactionByTransactionType(String transactionType){
		
		
		List<Transaction> transaction=transactionRepository.findTransactionByTransactionType(transactionType);
		if(transaction.size()==0) {
	 new ResponseStatusException(HttpStatus.NOT_FOUND,"No transaction found for given Type:"+transactionType);
	
		
		}return transaction;
	}
	
	/*
	 * Get Transaction By Date
	 * */
	
      public List<Transaction> getTransactionByDate(LocalDateTime date){
		
    	  List<Transaction> transaction=transactionRepository.findTransactionByDate(date);
    	  
  		if(transaction.size()==0) {
  	 new ResponseStatusException(HttpStatus.NOT_FOUND,"No transaction found for given Type:"+date);
  	
  		
  		}
  		return transaction;
	}
     
      /*
       * Get transaction by type and date 
       * 
       * */
      public List<Transaction>getTransactionByDateAndType(LocalDateTime date,String transactionType){
    	  List<Transaction> transactions=transactionRepository.findTransactionByDateAndTransactionType(date, transactionType);
    	  return transactions;
      }
      
      public String updateTransaction(Transaction transaction,  long transactionId)
  	{
  		String result="";
  		
  		transaction.setTransaction_id(transactionId);
  		Transaction updateTransaction=transactionRepository.save(transaction);
  		
  		if(updateTransaction!=null)
  		{
  			result=Results.SUCCESS;
  		}
  		else
  		{
  			
  			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
  		}
  		return result;
  		
  	}
  	
     
      
      
	/*
	 * Delete Transaction
	 * */
	public String deleteTransaction(long transactionId)
	{
		String result="";
	
		transactionRepository.deleteById(transactionId);
		
		
			result=Results.SUCCESS;
			return result;
	
		
	}
	/*
	 * Paging and Sorting 
	 * */
	public List<Transaction> getAllTransaction(Integer pageNo,Integer pageSize ,String sortBy)
	{
		Pageable paging=PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		
		Page<Transaction> pagedResult=transactionRepository.findAll(paging);
		int totalElement=pagedResult.getNumberOfElements();
		int total=pagedResult.getTotalPages();
		
		System.out.println("Total Number of pages are:"+total+"| Total Elements:"+totalElement);
	
		
		if(pagedResult.hasContent())
		{
			return pagedResult.getContent();
		}
		else
		{
			return new ArrayList<Transaction>();
		}
		
	}
	

}