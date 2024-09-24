package com.example.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankAccount {

	@Id
	@NotNull
	private Integer accountNo;

	@NotNull
	@Size(min = 5, max = 10,message = "Invalid IFSC Code [ 5-10 Characters only ]")
	private String IFSCCode;
	
	@NotNull
	@Size(min = 3, max = 15,message = "Invalid Bank Name [ 3-15 characters only ]")
	private String bankName;
	
	@NotNull
	private Double balance;
	
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	private Wallet wallet;


	public BankAccount(@NotNull Integer accountNo, @NotNull String iFSCCode,
                       @NotNull @Size(min = 4, max = 10, message = "Bank name not valid") String bankName,
                       @NotNull Double balance) {
		super();
		this.accountNo = accountNo;
		this.IFSCCode = iFSCCode;
		this.bankName = bankName;
		this.balance = balance;
	}

}