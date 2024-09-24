package com.example.wallet.service;

import com.example.wallet.exceptions.BankAccountException;
import com.example.wallet.exceptions.CustomerException;
import com.example.wallet.model.BankAccount;
import com.example.wallet.model.Wallet;
import com.example.wallet.dto.BankAccountDTO;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
	
	public Wallet addBankAccount(String key,BankAccountDTO bankAccountDTO) throws BankAccountException,CustomerException;
	
	public Wallet removeBankAccount(String key,BankAccountDTO bankAccountDTO) throws BankAccountException, CustomerException;
	
	public Optional<BankAccount> viewBankAccount(String key, Integer accountNo) throws BankAccountException, CustomerException;
	
	public List<BankAccount> viewAllBankAccounts(String key) throws BankAccountException, CustomerException;

}
