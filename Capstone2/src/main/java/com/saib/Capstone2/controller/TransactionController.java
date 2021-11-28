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
import org.springframework.web.bind.annotation.RestController;

import com.saib.Capstone2.config.ApiSuccessPayload;
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
	public ResponseEntity<ApiSuccessPayload> getAllTransactions()
	{
		List<Transaction> list=transactionService.getAllTransaction();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transaction Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/transactions/{transaction_id}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransaction_id(@PathVariable long transaction_id)
	{
		Transaction transaction=transactionService.getTransactionByTransaction_id(transaction_id);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/{transaction_type}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransaction_type(@PathVariable String transaction_type)
	{
		Transaction transaction=transactionService.getTransactionByTransaction_type(transaction_type);
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<ApiSuccessPayload> addTransaction(@RequestBody Transaction transaction)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(transaction);
		String result=transactionService.addTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Transaction created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/delete/{transaction_id}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transaction_id)
	{
		return null;
	}
	
	@DeleteMapping("/transaction/{transaction_id}")
	public ResponseEntity<ApiSuccessPayload> deleteTransaction(@PathVariable long transaction_id)
	{
		return null;
	}
	
	
	

}


