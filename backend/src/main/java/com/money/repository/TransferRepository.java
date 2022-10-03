package com.money.repository;

import com.money.model.Transfer;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>
{
	@Query(value =
		"SELECT new com.money.model.dto.TransferDetalheDTO(t.id, t.amountSpent, t.description, t.transferDate, p.way, ts.category) FROM Transfer t "
			+ "INNER JOIN PaymentWay p ON p.id = t.paymentWay.id INNER JOIN TypeSpent ts ON ts.id = t.typeSpent.id "
			+ "INNER JOIN User u ON u.id = t.user.id and t.id = :transferId")
	TransferDetalheDTO findTransferDetalheById(Long transferId);

	@Query(value =
		"SELECT new com.money.model.dto.TransferDTO(t.id, t.amountSpent, t.transferDate, t.typeSpent.category) FROM Transfer t "
			+ "INNER JOIN PaymentWay p ON p.id = t.paymentWay.id INNER JOIN TypeSpent ts ON ts.id = t.typeSpent.id "
			+ "INNER JOIN User u ON u.id = t.user.id and u.userName = :userName")
	List<TransferDTO> findTransfersByUserName(@Param("userName") String userName);

	@Query(value = "SELECT * FROM transfer t where t.id = :transferId", nativeQuery = true)
	Optional<Transfer> findTransferById(@Param("transferId") Long trasnferId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM transfer t WHERE t.id = :transferId", nativeQuery = true)
	void delete(@Param("transferId") Long transferId);

	@Modifying
	@Transactional
	@Query(value =
		"INSERT INTO transfer (amount_spent, description, transfer_date, payment_way_id, type_spent_id, user_id )"
			+ "VALUES (:amount, :description, :transferDate, :paymentWayId, :typeSpentId, :userId);", nativeQuery = true)
	void saveTransfer(Double amount, String description, LocalDateTime transferDate, Long paymentWayId,
		Long typeSpentId, Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE transfer t SET t.description = :description, t.transfer_date = :transferDate, t.payment_way_id = :paymentWayId,"
		+ " t.type_spent_id = :typeSpentId where t.id = :transferId", nativeQuery = true)
	void update(@Param("") Long transferId, @Param("description") String description,
		@Param("transferDate") LocalDateTime transferDate, @Param("paymentWayId") Long paymentWayId,
		@Param("typeSpentId") Long typeSpentId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO transfer_plan_spent values (:transferId, :keepId)",nativeQuery = true)
	void saveTransferKeepMoney(@Param("transferId") Long transferId, @Param("keepId") Long keepId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM transfer_plan_spent where transfer_id = :transferId and keep_spent_id = :keepId)",nativeQuery = true)
	void deleteTransferKeepMoney(@Param("transferId") Long transferId, @Param("keepId") Long keepId);
}
