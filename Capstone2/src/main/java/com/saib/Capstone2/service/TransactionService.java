package com.saib.Capstone2.service;

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

import com.saib.Capstone2.models.Transaction;
import com.saib.Capstone2.repository.TransactionRepository;
import com.saib.Capstone2.util.Results;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	

	public List<Transaction> getAllTransaction()
	{
		List<Transaction> list=transactionRepository.findAll();
		return list;
	
		
	}
	
	
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
	public Transaction getTransactionByTransactionType(String transactionType)
	{
		/**Optional<Transaction> optional=transactionRepository.findByType(transactionType);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Number:"+transactionType+"doesn't exist");
		}**/
		return null;
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
