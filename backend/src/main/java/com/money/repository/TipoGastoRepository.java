package com.money.repository;

import com.money.model.TipoGasto;
import com.money.model.dto.TipoGastoDTO;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoGastoRepository extends JpaRepository<TipoGasto, Long>
{
	@Query(value = "SELECT * FROM tipo_gasto t where t.nome=?1 ", nativeQuery = true)
	TipoGasto findTypeSpentByName(String nomeName);

	@Query(value = "SELECT * FROM tipo_gasto t where t.id = :id ", nativeQuery = true)
	Optional<TipoGasto> findById(@Param("id") Long id);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM tipo_gasto t where t.id = :id", nativeQuery = true)
	void delete(@Param("id") Long id);

	@Query(value = "SELECT new com.money.model.dto.TipoGastoDTO(t.id, t.nome) FROM TipoGasto t")
	List<TipoGastoDTO> listTypeSpent();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO tipo_gasto (nome) values(:tipoGasto)", nativeQuery = true)
	void saveTypeSpent(@Param("tipoGasto") String tipoGasto);

	@Modifying
	@Transactional
	@Query(value = "UPDATE tipo_gasto t SET nome = :tipoGasto WHERE t.id = :id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("tipoGasto") String tipoGasto);
}
