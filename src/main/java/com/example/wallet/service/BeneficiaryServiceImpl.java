package com.example.wallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wallet.exceptions.BeneficiaryException;
import com.example.wallet.exceptions.CustomerException;
import com.example.wallet.exceptions.WalletException;
import com.example.wallet.model.Beneficiary;
import com.example.wallet.model.CurrentUserSession;
import com.example.wallet.model.Customer;
import com.example.wallet.model.Wallet;
import com.example.wallet.dto.BeneficiaryDTO;
import com.example.wallet.repository.BeneficiaryRepo;
import com.example.wallet.repository.CurrentSessionRepo;
import com.example.wallet.repository.CustomerRepo;
import com.example.wallet.repository.WalletRepo;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
   
   @Autowired
   private BeneficiaryRepo beneficiaryRepo;

   @Autowired
   private CurrentSessionRepo currentSessionRepo;
   
   @Autowired
   private WalletRepo walletRepo;

   @Autowired
   private CustomerRepo customerRepo;


   /*--------------------------------------------   Add Beneficiary -------------------------------------------------*/
   @Override
   public Beneficiary addBeneficiary(Beneficiary beneficiary,String key) throws BeneficiaryException, CustomerException, WalletException {

      CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
      if(currUserSession==null) {
         throw new CustomerException("No Customer LoggedIn");
      }

      Optional<Customer> customer = customerRepo.findById(currUserSession.getUserId());
      Optional<Wallet> wallet = walletRepo.findById(walletRepo.showCustomerWalletDetails(currUserSession.getUserId()).getWalletId());

      if (!customer.isPresent()) {
         throw new CustomerException("Beneficiary is not Registered to the Application.");
      }

      if (!wallet.isPresent()){
         throw new WalletException("Invalid User.");
      }

      Optional<Beneficiary> optional=beneficiaryRepo.findById(beneficiary.getBeneficiaryMobileNumber());

      if(optional.isEmpty()) {
         return beneficiaryRepo.save(beneficiary);
      }
      throw new BeneficiaryException("Duplicate Details [ Beneficiary Already Exist ]");


   }


   /*--------------------------------------------   Find Beneficiary -------------------------------------------------*/
   @Override
   public List<Beneficiary> findAllByWallet(Integer walletId) throws BeneficiaryException {

      List<Beneficiary> beneficiaries = beneficiaryRepo.findByWallet(walletId);

      if(beneficiaries.isEmpty()) {
         throw new BeneficiaryException("No Beneficiary Exist");
      }
      return beneficiaries;

   }


   /*--------------------------------------------   View Beneficiary -------------------------------------------------*/
   @Override
   public Beneficiary viewBeneficiary(String beneficiaryName, String key) throws BeneficiaryException, CustomerException {

      CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
      if(currUserSession==null) {
         throw new CustomerException("No Customer LoggedIn");
      }

      Wallet wallet = walletRepo.showCustomerWalletDetails(currUserSession.getUserId());

      Beneficiary beneficiaries = beneficiaryRepo.findByNameWallet(wallet.getWalletId(),beneficiaryName);

      if(beneficiaries == null) {
         throw new BeneficiaryException("No Beneficiary Exist");
      }
      return beneficiaries;

   }


   /*--------------------------------------------   View All Beneficiary -------------------------------------------------*/
   @Override
   public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException, CustomerException {

      CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
      if(currUserSession == null) {
         throw new CustomerException("No Customer LoggedIn");
      }

      Wallet wallet = walletRepo.showCustomerWalletDetails(currUserSession.getUserId());

      List<Beneficiary> beneficiaries = beneficiaryRepo.findByWallet(wallet.getWalletId());

      if(beneficiaries.isEmpty()) {
         throw new BeneficiaryException("No Beneficiary Exist");
      }
      return beneficiaries;
   }


   /*--------------------------------------------   Delete Beneficiary -------------------------------------------------*/
   @Override
   public Beneficiary deleteBeneficiary(String key, BeneficiaryDTO beneficiaryDTO) throws BeneficiaryException, CustomerException {

      CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
      if(currUserSession == null) {
         throw new CustomerException("No Customer LoggedIn");
      }

      Wallet wallet = walletRepo.showCustomerWalletDetails(currUserSession.getUserId());

      Beneficiary beneficiaries = beneficiaryRepo.findByMobileWallet(wallet.getWalletId(),beneficiaryDTO.getBeneficiaryMobileNumber());

      if(beneficiaries == null) {
         throw new BeneficiaryException("No Beneficiary found");
      }

      beneficiaryRepo.delete(beneficiaries);

      return beneficiaries;
   }

}