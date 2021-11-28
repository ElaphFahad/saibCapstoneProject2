package com.saib.Capstone2.models;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity 
@Table(name="transaction")
public class Transaction {

	@Id
	@Column (name ="transaction_id", unique = true)
	private long transaction_id	;

	@Column (name ="from_account")
	private long from_account	;

	@Column (name ="to_account")
	private long to_account	;

	@Column (name ="from_account_name")
	private String from_account_name	;

	@Column (name ="to_account_name")
	private String to_account_name	;

	@Column (name ="same_bank_transaction")
	private String same_bank_transaction	;

	@Column (name ="other_bank_transaction")
	private String other_bank_transaction	;

	@Column (name ="amount")
	private double amount;	

	@Column (name ="date")
	private LocalDate date;	

	@Column (name ="time")
	private LocalTime time;	

	@Column (name ="transaction_type")
	private String transaction_type	;
	   
    @Column (name ="status")
	private String status;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(long transaction_id, long from_account, long to_account, String from_account_name,
			String to_account_name, String same_bank_transaction, String other_bank_transaction, double amount,
			LocalDate date, LocalTime time, String transaction_type, String status) {
		super();
		this.transaction_id = transaction_id;
		this.from_account = from_account;
		this.to_account = to_account;
		this.from_account_name = from_account_name;
		this.to_account_name = to_account_name;
		this.same_bank_transaction = same_bank_transaction;
		this.other_bank_transaction = other_bank_transaction;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.transaction_type = transaction_type;
		this.status = status;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public long getFrom_account() {
		return from_account;
	}

	public void setFrom_account(long from_account) {
		this.from_account = from_account;
	}

	public long getTo_account() {
		return to_account;
	}

	public void setTo_account(long to_account) {
		this.to_account = to_account;
	}

	public String getFrom_account_name() {
		return from_account_name;
	}

	public void setFrom_account_name(String from_account_name) {
		this.from_account_name = from_account_name;
	}

	public String getTo_account_name() {
		return to_account_name;
	}

	public void setTo_account_name(String to_account_name) {
		this.to_account_name = to_account_name;
	}

	public String getSame_bank_transaction() {
		return same_bank_transaction;
	}

	public void setSame_bank_transaction(String same_bank_transaction) {
		this.same_bank_transaction = same_bank_transaction;
	}

	public String getOther_bank_transaction() {
		return other_bank_transaction;
	}

	public void setOther_bank_transaction(String other_bank_transaction) {
		this.other_bank_transaction = other_bank_transaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", from_account=" + from_account + ", to_account="
				+ to_account + ", from_account_name=" + from_account_name + ", to_account_name=" + to_account_name
				+ ", same_bank_transaction=" + same_bank_transaction + ", other_bank_transaction="
				+ other_bank_transaction + ", amount=" + amount + ", date=" + date + ", time=" + time
				+ ", transaction_type=" + transaction_type + ", status=" + status + "]";
	}
    
    
    
}
