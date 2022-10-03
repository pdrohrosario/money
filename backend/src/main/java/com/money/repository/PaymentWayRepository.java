package com.money.repository;

import com.money.model.PaymentWay;
import com.money.model.PaymentWay;
import com.money.model.dto.PaymentWayDTO;
import com.money.model.dto.PaymentWayDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentWayRepository extends JpaRepository<PaymentWay, Long>
{
	@Query(value = "SELECT * FROM payment_way p where p.way = :payment",nativeQuery = true)
	PaymentWay findPaymentWayByName(String payment);

	@Query(value = "SELECT * FROM payment_way t where t.id = :id ", nativeQuery = true)
	Optional<PaymentWay> findById(@Param("id") Long id);

	@Query(value="DELETE FROM payment_way t where t.id = :id", nativeQuery = true)
	void delete(@Param("id") Long id);

	@Query(value = "SELECT new com.money.model.dto.PaymentWayDTO(t.id, t.way) FROM PaymentWay t")
	List<PaymentWayDTO> listPaymentWay();

	@Query(value = "INSERT INTO payment_way values(paymentWay)", nativeQuery = true)
	void savePaymentWay(@Param("paymentWay") String paymentWay);

	@Query(value = "UPDATE payment_way t SET category = :paymentWay WHERE t.id = :id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("paymentWay") String paymentWay);
}
