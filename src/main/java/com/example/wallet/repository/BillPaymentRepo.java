package com.example.wallet.repository;

import com.example.wallet.model.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentRepo extends JpaRepository<BillPayment, Integer> {

}