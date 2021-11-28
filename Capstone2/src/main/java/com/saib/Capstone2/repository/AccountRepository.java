package com.saib.Capstone2.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.Capstone2.models.Account;

@Qualifier("Account")
@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

}