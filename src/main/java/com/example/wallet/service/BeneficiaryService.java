package com.example.wallet.service;

import java.util.List;

import com.example.wallet.exceptions.BeneficiaryException;
import com.example.wallet.exceptions.CustomerException;
import com.example.wallet.exceptions.WalletException;
import com.example.wallet.model.Beneficiary;
import com.example.wallet.dto.BeneficiaryDTO;

public interface BeneficiaryService {
	
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String key) throws BeneficiaryException, CustomerException, WalletException;

	public List<Beneficiary> findAllByWallet(Integer walletId) throws BeneficiaryException;

	public Beneficiary viewBeneficiary(String beneficiaryName, String key) throws BeneficiaryException, CustomerException;

	public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException, CustomerException;

	public Beneficiary deleteBeneficiary(String key, BeneficiaryDTO beneficiaryDTO) throws BeneficiaryException, CustomerException;

}
