package com.example.wallet.service;

import com.example.wallet.exceptions.BillPaymentException;
import com.example.wallet.exceptions.CustomerException;
import com.example.wallet.exceptions.TransactionException;
import com.example.wallet.exceptions.WalletException;

import java.time.LocalDate;

public interface BillPaymentService {

	public String addBillPayment(String targetMobile, String Name, double amount, String BillType, LocalDate paymentDate, Integer walletId, String key) throws BillPaymentException,WalletException,CustomerException,TransactionException;

}