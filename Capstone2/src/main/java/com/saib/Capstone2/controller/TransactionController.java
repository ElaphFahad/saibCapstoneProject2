package com.saib.Capstone2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saib.Capstone2.config.ApiSuccessPayload;
import com.saib.Capstone2.config.ApiSuccessTransaction;
import com.saib.Capstone2.models.Account;
import com.saib.Capstone2.models.Transaction;
import com.saib.Capstone2.service.TransactionService;
import com.saib.Capstone2.util.Results;

@RestController
public class TransactionController {
	/*
	 *  GET - /transactions - Get me all transaction 
	 *  GET - /transactions/id - Get me details for a single transaction 
	 *   GET - /transactions/type - Get me details for a single transaction by type
	 *  POST - /transactions - Creating a new transaction 
	 *  PUT - /delete/id -
	 *  DELETE -/transactions/id - for deleting an transaction from db
	 *  
	 *  
	 */
	@Autowired
	TransactionService transactionService;
	
	
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessTransaction> getAllTransactions()
	{
		List<Transaction> list=transactionService.getAllTransaction();
		
		ApiSuccessTransaction payload=ApiSuccessTransaction.build(list, "Transaction Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessTransaction> response=new ResponseEntity<ApiSuccessTransaction>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/transactions/{transactionId}")
	public ResponseEntity<ApiSuccessTransaction> getTransactionByTransactionId(@PathVariable long transactionId)
	{
		Transaction transaction=transactionService.getTransactionByTransactionId(transactionId);
		
		ApiSuccessTransaction payload=ApiSuccessTransaction.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessTransaction> response=new ResponseEntity<ApiSuccessTransaction>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/{transactionType}")
	public ResponseEntity<ApiSuccessTransaction> getTransactionByTransactionType(@PathVariable String transactionType)
	{
		Transaction transaction=transactionService.getTransactionByTransactionType(transactionType);
		ApiSuccessTransaction payload=ApiSuccessTransaction.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessTransaction> response=new ResponseEntity<ApiSuccessTransaction>(payload,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<ApiSuccessTransaction> addTransaction(@RequestBody Transaction transaction)
	{
		ResponseEntity<ApiSuccessTransaction> response=null;
		System.out.println(transaction);
		String result=transactionService.addTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessTransaction payload=ApiSuccessTransaction.build(result, "Transaction created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessTransaction>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/delete/{transactionId}")
	public ResponseEntity<ApiSuccessTransaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transactionId)
	{
		return null;
	}
	
	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSuccessTransaction> deleteTransaction(@PathVariable long transactionId)
	{
		return null;
	}

@GetMapping("/transactions/all/sorted")
public ResponseEntity<ApiSuccessPayload> getAllAccounts(@RequestParam int pageNumber,
		                                                 @RequestParam int pageSize,
		                                                 @RequestParam String sortBy)
{
	List<Transaction> list=transactionService.getAllTransaction(pageNumber, pageSize, sortBy);
	HttpStatus status=HttpStatus.OK;
	ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status);
	ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
	return response;
	
}

}
	
	
	




