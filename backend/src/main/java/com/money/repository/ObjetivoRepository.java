package com.money.repository;

import com.money.model.Objetivo;
import com.money.model.dto.ObjetivoDTO;
import com.money.model.dto.ObjetivoDetalheDTO;
import com.money.model.dto.ObjetivosFinalizadosDTO;
import com.money.model.dto.ObjetivoStatusDTO;
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
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long>
{
	@Query(value = "select new com.money.model.dto.ObjetivoDTO(g.id, p.titulo, t.nome, p.quantia) from Objetivo g " 
		+ "inner join TipoGasto t on t.id = g.tipoGasto.id inner join PlanoGasto p on p.id = g.planoGasto.id " 
		+ "inner join User u on u.id = g.user.id and u.userName = :userName")
	List<ObjetivoDTO> findGoalsByUserName(@Param("userName") String userName);

	@Query(value = "select new com.money.model.dto.ObjetivoDetalheDTO(g.id, p.titulo, t.nome ,u.name, p.quantia, p.dataInicio, p.dataFim, p.descricao) from Objetivo g " 
		+ "inner join PlanoGasto p on p.id = g.planoGasto.id " 
		+ "inner join TipoGasto t on t.id = g.tipoGasto.id " 
		+ "inner join User u on u.id = g.user.id and g.id = :objetivoId")
	ObjetivoDetalheDTO findGoalDetalheById(@Param("objetivoId") Long objetivoId);

	@Query(value = "select * from objetivo g where g.id = :objetivoId", nativeQuery = true)
	Optional<Objetivo> findGoalById(@Param("objetivoId") Long objetivoId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO objetivo (tipo_gasto_id, plano_gasto_id, user_id)"
		+ " VALUES (:tipo_gasto_id, :plano_gasto_id, :user_id);", nativeQuery = true)
	void saveGoal(@Param("plano_gasto_id") Long planoGastoId, @Param("user_id") Long userId,
		@Param("tipo_gasto_id") Long tipoGastoId);

	@Query(value =
		"select new com.money.model.dto.ObjetivoStatusDTO(SUM(t.quantia), p.quantia, p.dataInicio, p.dataFim) from Transferencia t"
			+ " inner join Objetivo g on t.tipoGasto.id = g.tipoGasto.id " 
			+ " inner join PlanoGasto p on p.id = g.tipoGasto.id " 
			+ "inner join User u on u.id = t.user.id and g.user.id = u.id and u.id = :userId")
	ObjetivoStatusDTO verifyGoalStatus(@Param("objetivoId") Long objetivoId, @Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM objetivo g WHERE g.id = :objetivoId", nativeQuery = true)
	void deleteGoal(@Param("objetivoId") Long objetivoId);


	@Modifying
	@Transactional
	@Query(value = "UPDATE goal g SET g.type_spent_id = :tipoGastoId WHERE g.id = :objetivoId ", nativeQuery = true)
	void update(@Param("tipoGastoId") Long tipoGastoId, @Param("objetivoId") Long objetivoId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE goal g SET quantia = :quantia, description = :description, end_date = :dataFim WHERE g.id = :objetivoId ", nativeQuery = true)
	void update(@Param("quantia") Double quantia, @Param("description") String description,
		@Param("dataFim") LocalDateTime dataFim, @Param("objetivoId") Long objetivoId);

	@Query(value =
		" select g.id, p.quantia,  p.quantia - SUM(t.quantia), ts.nome, p.dataInicio, p.dataFim from Objetivo g "
			+ "inner join Transferencia t on t.tipoGasto.id = g.tipoGasto.id inner join TipoGasto ts on ts.id = g.tipoGasto.id "
			+ "inner join PlanoGasto p on p.id = g.planoGasto.id "
			+ "inner join User u on  u.id = t.user.id and u.id = g.user.id and u.userName = :userName "
			+ "where p.dataFim < :nowDate and t.data between p.dataInicio and p.dataFim")
	List<ObjetivosFinalizadosDTO> findOldenGoalsByUserName(@Param("userName") String userName,
		@Param("noWDate") LocalDateTime nowDate);
}
