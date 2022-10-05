package com.money.repository;

import com.money.model.Transferencia;
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
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>
{
	@Query(value =
		"SELECT new com.money.model.dto.TransferDetalheDTO(t.id, t.quantia, t.descricao, t.data, p.nome, ts.nome) FROM Transferencia t "
			+ "INNER JOIN FormaPagamento p ON p.id = t.formaPagamento.id INNER JOIN TipoGasto ts ON ts.id = t.tipoGasto.id "
			+ "INNER JOIN User u ON u.id = t.user.id and t.id = :transferId")
	TransferDetalheDTO findTransferDetalheById(Long transferId);

	@Query(value =
		"SELECT new com.money.model.dto.TransferDTO(t.id, t.quantia, t.data, ts.nome) FROM Transferencia t "
			+ "INNER JOIN TipoGasto ts ON ts.id = t.tipoGasto.id "
			+ "INNER JOIN User u ON u.id = t.user.id and u.userName = :userName")
	List<TransferDTO> findTransfersByUserName(@Param("userName") String userName);

	@Query(value = "SELECT * FROM transferencia t where t.id = :transferId", nativeQuery = true)
	Optional<Transferencia> findTransferById(@Param("transferId") Long trasnferId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM transfer t WHERE t.id = :transferId", nativeQuery = true)
	void delete(@Param("transferId") Long transferId);

	@Modifying
	@Transactional
	@Query(value =
		"INSERT INTO transfer (quantia, descricao, data, forma_pagamento_id, tipo_gasto_id, user_id )"
			+ "VALUES (:quantia, :descricao, :data, :formaPagamentoId, :tipoGastoId, :userId);", nativeQuery = true)
	void saveTransfer(Double quantia, String descricao, LocalDateTime data, Long formaPagamentoId,
		Long tipoGastoId, Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE transfer t SET t.descricao = :descricao, t.data = :data, t.forma_pagamento_id = :formaPagamentoId,"
		+ " t.tipo_gasto_id = :tipoGastoId where t.id = :transferId", nativeQuery = true)
	void update(@Param("transferId") Long transferId, @Param("descricao") String descricao,
		@Param("data") LocalDateTime data, @Param("formaPagamentoId") Long formaPagamentoId,
		@Param("tipoGastoId") Long tipoGastoId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO transferencia_plano_gasto values (:transferId, :planoGastoId)",nativeQuery = true)
	void saveTransferKeepMoney(@Param("transferId") Long transferId, @Param("planoGastoId") Long planoGastoId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM transferencia_plano_gasto where transfer_id = :transferId and plano_gasto_id = :planoGastoId)",nativeQuery = true)
	void deleteTransferKeepMoney(@Param("transferId") Long transferId, @Param("planoGastoId") Long keepId);
}
