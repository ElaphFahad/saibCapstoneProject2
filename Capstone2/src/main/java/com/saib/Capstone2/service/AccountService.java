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

import com.saib.Capstone2.models.Account;
import com.saib.Capstone2.repository.AccountRepository;
import com.saib.Capstone2.util.Results;



@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> getAllAccount()
	{
		List<Account> list=accountRepository.findAll();
		return list;
	
		
	}
	
	public Account getAccountByAccountNumber(long accountNumber)
	{
		Optional<Account> optional=accountRepository.findById(accountNumber);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account with Account Number:"+accountNumber+"doesn't exist");
		}
		
	}
	
	
	public String addAccount(Account account)
	{
		String result="";
		Account storedAccount=accountRepository.save(account);
		if(storedAccount!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not created");
		}
		
		return result;
	}
	
	public String updateAccount(Account account, long accountNumber)
	{
		String result="";
		
		account.setAccountNumber(accountNumber);
		Account updatedAccount=accountRepository.save(account);
		
		if(updatedAccount!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;
		
	}
	
	public String deleteAccount(long accountNumber)
	{
		String result="";
	
		accountRepository.deleteById(accountNumber);
		
		
			result=Results.SUCCESS;
			return result;
	
		
	}
	
	public List<Account> getAccountsByGender(String gender){
		
		
		List<Account> accounts=accountRepository.findAccountByGender(gender);
		if(accounts.size()==0) {
	 new ResponseStatusException(HttpStatus.NOT_FOUND,"No account details found for given gender:"+gender);
	
		
		}return accounts;
	}
	
	public List<Account> getAllAccount(Integer pageNo,Integer pageSize)
	{
		Pageable paging=PageRequest.of(pageNo,pageSize);
	
		
		Page<Account> pagedResult=accountRepository.findAll(paging);
		int totalElements=pagedResult.getNumberOfElements();
		int total=pagedResult.getTotalPages();
		System.out.println("Total Number of Pages are:"+total+" | Total Elements:"+totalElements);
		
		if(pagedResult.hasContent())
		{
			return pagedResult.getContent();
		}
		else
		{
			return new ArrayList<Account>();
		}
		
	}
	
	public List<Account> getAllAccount(Integer pageNo,Integer pageSize, String sortBy)
	{
		Pageable paging=PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
	
		
		Page<Account> pagedResult=accountRepository.findAll(paging);
		int totalElements=pagedResult.getNumberOfElements();
		int total=pagedResult.getTotalPages();
		System.out.println("Total Number of Pages are:"+total+" | Total Elements:"+totalElements);
		
		if(pagedResult.hasContent())
		{
			return pagedResult.getContent();
		}
		else
		{
			return new ArrayList<Account>();
		}
		
	}

}