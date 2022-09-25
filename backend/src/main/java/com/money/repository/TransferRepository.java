package com.money.repository;

import com.money.model.Transfer;
import com.money.model.dto.TransferDTO;
import com.money.model.dto.TransferDetalheDTO;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>
{
	@Query(value="SELECT new com.money.model.dto.TransferDetalheDTO(t.id, t.amountSpent, t.description, t.transferDate, p.way, ts.category) FROM Transfer t "
		+ "INNER JOIN PaymentWay p ON p.id = t.paymentWay.id INNER JOIN TypeSpent ts ON ts.id = t.typeSpent.id "
		+ "INNER JOIN User u ON u.id = t.user.id and t.id = :transferId")
	TransferDetalheDTO findTransferById(Long transferId);

	@Query(value="SELECT new com.money.model.dto.TransferDTO(t.id, t.amountSpent, t.transferDate, t.typeSpent.category) FROM Transfer t "
		+ "INNER JOIN PaymentWay p ON p.id = t.paymentWay.id INNER JOIN TypeSpent ts ON ts.id = t.typeSpent.id "
		+ "INNER JOIN User u ON u.id = t.user.id and u.id = :userId")
	List<TransferDTO> findTransferByUserId(Long userId);


	@Modifying
	@Transactional
	@Query(value = "INSERT INTO transfer (amount_spent, description, transfer_date, payment_way_id, type_spent_id, user_id )"
		+ "VALUES (:amount, :description, :transferDate, :paymentWayId, :typeSpentId, :userId);",nativeQuery = true)
	void saveTransfer(Double amount, String description, LocalDateTime transferDate, Long paymentWayId, Long typeSpentId, Long userId);
}
