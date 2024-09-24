package com.example.wallet.service;

import com.example.wallet.exceptions.BillPaymentException;
import com.example.wallet.exceptions.CustomerException;
import com.example.wallet.exceptions.TransactionException;
import com.example.wallet.exceptions.WalletException;
import com.example.wallet.model.BillPayment;
import com.example.wallet.repository.BillPaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BillPaymentServiceImpl implements BillPaymentService{

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private BillPaymentRepo billPaymentRepo;


	/*--------------------------------------------   Add Bill Payment -------------------------------------------------*/
	@Override
	public String addBillPayment(String targetMobile, String Name, double amount, String billType, LocalDate paymentDate, Integer walletId, String key) throws BillPaymentException  {

		BigDecimal value = BigDecimal.valueOf(amount);

//		String str = walletService.fundTransfer(targetMobile, Name, value, key);

		BillPayment billPayment = new BillPayment(amount, billType, LocalDate.now());

		billPaymentRepo.save(billPayment);

//		return str;

		return "";
	}
}
