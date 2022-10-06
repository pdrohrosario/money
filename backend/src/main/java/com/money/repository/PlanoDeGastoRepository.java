package com.money.repository;

import com.money.model.PlanoGasto;
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
public interface PlanoDeGastoRepository extends JpaRepository<PlanoGasto, Long>
{
	@Query(value = "SELECT * FROM plano_gasto p where p.id = :id ", nativeQuery = true)
	Optional<PlanoGasto> findById(@Param("id") Long id);

	@Query(value = "DELETE FROM plano_gasto p where p.id = :id", nativeQuery = true)
	void delete(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE plano_gasto p SET titulo = :titulo, quantia = :quantia, descricao = :descricao, data_fim = :dataFim WHERE p.id = :idPlan ;", nativeQuery = true)
	void update(@Param("idPlan") Long idPlan,@Param("dataFim") LocalDateTime dataFim,@Param("descricao") String descricao,  @Param("quantia") Double quantia,@Param("titulo") String titulo);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO plano_gasto ( id, titulo, quantia, descricao, data_inicio, data_fim)"
		+ " VALUES (:id,:titulo,:quantia, :descricao, :data_inicio,:data_fim);", nativeQuery = true)
	void savePlanSpent(@Param("id") Long id, @Param("titulo") String titulo, @Param("quantia") Double quantia,
		@Param("descricao") String descricao, @Param("data_inicio") LocalDateTime dataInicio,
		@Param("data_fim") LocalDateTime dataFim);


	@Query(value = "SELECT * FROM plano_gasto",nativeQuery = true)
	List<PlanoGasto> listPlanoDeGasto();

//	@Query(value = "SELECT * FROM plano_gasto p where p.titulo = :titulo and p.data_inicio = :dataInicio ", nativeQuery = true)
//	Optional<PlanoGasto> findBytituloAndStarttitulo(@Param("titulo") String titulo,
//		@Param("dataInicio") LocalDateTime dataInicio);
}
