package com.money.repository;

import com.money.model.Poupanca;
import com.money.model.TipoGasto;
import com.money.model.dto.PoupancaDTO;
import com.money.model.dto.PoupancaDetalheDTO;
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
public interface PoupancaRepository extends JpaRepository<Poupanca, Long>
{
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO poupanca (id, quantia_mes_esperada, quantidade_meses, quantidade_minima_transferencias, plano_gasto_id, user_id)"
		+ " VALUES (:id,:quatiaMes, :quantMes, :qtdTransferencias, :planoGastoId, :userId)", nativeQuery = true)
	void saveKeepMoney(@Param("id") Long id, @Param("quatiaMes") Double quatiaMes, @Param("quantMes") Integer quantMes,
		@Param("qtdTransferencias") Integer qtdTransferencias, @Param("planoGastoId") Long planoGastoId,
		@Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE poupanca set plan_spent_id = :planoGastoId, user_id = :userId ) VALUES ( :plan_spent_id, :user_id);", nativeQuery = true)
	void update(@Param("planoGastoId") Long planoGastoId, @Param("userId") Long userId);

	@Query(value = "select new com.money.model.dto.PoupancaDTO(k.id, plan.titulo, plan.quantia) from Poupanca k inner join PlanoGasto plan on plan.id = k.planoGasto.id inner join User u on u.id = k.user.id and u.userName = :userName")
	List<PoupancaDTO> findPoupancaByUserName(@Param("userName") String userName);

	@Query(value = "select new com.money.model.dto.PoupancaDetalheDTO(k.id, plan.titulo, plan.quantia, plan.descricao, plan.dataInicio, plan.dataFim, k.quantidadeMinimaTransferencias, k.quantiaMesEsperada, k.quantidadeMeses ) from Poupanca k "
		+ "inner join PlanoGasto plan on plan.id = k.planoGasto.id inner join User u on u.id = k.user.id where k.id = :poupancaId")
	PoupancaDetalheDTO findPoupancaDetalheById(Long poupancaId);

	@Query(value="SELECT * FROM poupanca p where p.id = :id",nativeQuery = true)
	Optional<Poupanca> findPoupancaById(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM poupanca k WHERE k.id = :id", nativeQuery = true)
	void deleteKeepMoney(Long id);

	@Query(value = "SELECT * FROM poupanca k inner join user u on k.user_id = u.id and u.user_name = :userName "
		+ "inner join plano_gasto p on p.id = k.plano_gasto_id and :transferDate between p.data_inicio and p.data_fim", nativeQuery = true)
	Poupanca findByUsernameAndDate(@Param("userName") String userName,
		@Param("transferDate") LocalDateTime transferDate);

	@Query(value = "SELECT * FROM poupanca p inner join user u on u.id = p.user_id and u.user_name = :userName "
		+ "inner join plano_gasto pg on p.plano_gasto_id = pg.id and pg.data_fim > :dataNovaPoupanca", nativeQuery = true)
	List<Poupanca> buscaPoupancaAtiva(LocalDateTime dataNovaPoupanca, String userName);
}
