package com.saib.Capstone2.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.saib.Capstone2.models.Transaction;
import com.saib.Capstone2.service.TransactionService;
import com.saib.Capstone2.util.Results;

@RestController
public class TransactionController {
	/*
	 *  GET - /transactions - Get me all transaction 
	 *  GET - /transactions/id - Get me details for a single transaction 
	 *   GET - /transactions/type - Get me details for a single transaction by type
	 *   GET - /transactions/date - Get me details for a single transaction by date
	 *   GET - /transactions/getByDateAndType - Get me details for a single transaction by type and date
	 *  POST - /transactions - Creating a new transaction 
	 *  PUT - /delete/id -
	 *  DELETE -/transactions/id - for deleting an transaction from db
	 *  
	 *  
	 */
	@Autowired
	TransactionService transactionService;
	
	/*
	 *  GET - /transactions - Get me all transaction 
	 * */
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions(){
		List<Transaction> list=transactionService.getAllTransaction();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transaction Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	         }
	
	/*
	 *  GET - /transactions/id - Get me details for a single transaction  by its ID
	 * */
	
	@GetMapping("/transactions/transactionId/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionId(@PathVariable long transactionId) {
		Transaction transaction=transactionService.getTransactionByTransactionId(transactionId);
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	      }
	
	/*
	 *   GET - /transactions/type - Get me details for a single transaction by type
	 * */
	
	@GetMapping("/transactions/transactionType/{transactionType}")
	
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionType(@PathVariable String transactionType)
	
	{
		List<Transaction> list=transactionService.getTransactionByTransactionType(transactionType);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
	}
	
	/*
	 *   GET - /transactions/type - Get me details for a single transaction by date
	 * */
	
	@GetMapping("/transactions/date/{date}")
	
	public ResponseEntity<ApiSuccessPayload> getTransactionByDate(@RequestParam @DateTimeFormat (pattern ="yyyy-MM-dd HH:mm:ss") LocalDateTime date)
	{
		
		List<Transaction> list=transactionService.getTransactionByDate(date);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
	}
	
	/*
	 * GEt- /transactions/transactionByTypeAndDate
	 * */
     @GetMapping("/transactions/getByDateAndType/{transactionType}/{date}")
     public ResponseEntity<ApiSuccessPayload> getTransactionByDateAndType(@RequestParam @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime date,
    		 @PathVariable String transactionType ){
    	 List<Transaction> list= transactionService.getTransactionByDateAndType(date, transactionType);
    	 ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success", HttpStatus.OK);
    	 ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
    	 return response;
     }
   

	
	/*
	 * 
	 *  POST - /transactions - Creating a new transaction 
	 * */
	
	@PostMapping("/transactions")
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
	/*
	 *  PUT - /delete/id -
	 * */
	@PutMapping("/delete/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transactionId)
	{
		String result=transactionService.updateTransaction(transaction, transactionId);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	/*
	 *  DELETE -/transactions/id - for deleting an transaction from db
	 * 
	 * */
	@DeleteMapping("/transactions/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> deleteTransaction(@PathVariable long transactionId)
	{
		String result=transactionService.deleteTransaction(transactionId);
	ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
	ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
	return response;
	}
	
	/*
	 * Request parameters for the sorting and pageing 
	 * */

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
	
	
	




