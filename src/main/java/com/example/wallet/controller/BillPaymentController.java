package com.example.wallet.controller;

import com.example.wallet.exceptions.BillPaymentException;
import com.example.wallet.exceptions.CustomerException;
import com.example.wallet.exceptions.TransactionException;
import com.example.wallet.exceptions.WalletException;
import com.example.wallet.service.BillPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@CrossOrigin
public class BillPaymentController {

	@Autowired
	private BillPaymentService billPayService;


	/*--------------------------------------------   Add Bill Payment Mapping -------------------------------------------------*/
	@PostMapping("/addBillPayment")
	public ResponseEntity<String> addBillPayment(@RequestParam("targetMobile") String targetMobile, @RequestParam("Name") String Name, @RequestParam("amount") double amount, @RequestParam("BillType") String BillType, @RequestParam("key") String key) throws BillPaymentException, WalletException, CustomerException, TransactionException {
		
		LocalDate date=LocalDate.now();
		String output = billPayService.addBillPayment(targetMobile, Name, amount, BillType,date , 0, key);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}
}
