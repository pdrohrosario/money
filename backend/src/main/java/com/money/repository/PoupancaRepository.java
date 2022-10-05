package com.money.repository;

import com.money.model.Poupanca;
import com.money.model.dto.PoupancaDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaRepository
{
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO poupanca VALUES ( :planoGastoId, :userId);", nativeQuery = true)
	void saveKeepMoney(@Param("planoGastoId") Long planoGastoId, @Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE poupanca set plan_spent_id = :planoGastoId, user_id = :userId ) VALUES ( :plan_spent_id, :user_id);", nativeQuery = true)
	void update(@Param("planoGastoId") Long planoGastoId, @Param("userId") Long userId);

	@Query(value = "select new com.money.model.dto.PoupancaDTO(k.id, plan.titulo, plan.quantia) from Poupanca k inner join PlanoGasto plan on plan.id = k.planoGasto.id inner join User u on u.userName = :userName")
	List<PoupancaDTO> findKeepMoneyByUserName(@Param("userName") String userName);

	@Query(value = "select new com.money.model.dto.PoupancaDTO(k.id, plan.titulo, plan.quantia) from Poupanca k inner join PlanoGasto plan on plan.id = k.planoGasto.id inner join User u on u.id = k.user.id")
	Optional<Poupanca> findKeepMoneyById(Long goalId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM poupanca k WHERE k.id = :id", nativeQuery = true)
	void deleteKeepMoney(Long id);

	@Query(value = "SELECT * FROM poupanca k inner join user u on k.user_id = u.id and u.user_name = :userName inner join plan_spent p on p.id = k.plan_spent_id and :transferDate between p.start_date and p.end_date", nativeQuery = true)
	Poupanca findByUsernameAndDate(@Param("userName") String userName, @Param("transferDate") LocalDateTime transferDate);

	@Query(value = "SELECT * FROM poupanca p inner join plano_gasto pg on p.plano_gasto_id = pg.id and pg.dataFim > :dataNovaPoupanca",nativeQuery = true)
	List<Poupanca> buscaPoupancaAtiva(LocalDateTime dataNovaPoupanca);
}
