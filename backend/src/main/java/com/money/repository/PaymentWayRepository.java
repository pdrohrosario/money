package com.money.repository;

import com.money.model.PaymentWay;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentWayRepository extends JpaRepository<PaymentWay, Long>
{
	@Query(value = "SELECT * FROM payment_way p where p.way = :payment",nativeQuery = true)
	PaymentWay findPaymentWayByName(String payment);
}
