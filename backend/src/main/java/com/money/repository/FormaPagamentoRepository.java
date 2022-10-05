package com.money.repository;

import com.money.model.FormaPagamento;
import com.money.model.dto.FormaPagamentoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>
{
	@Query(value = "SELECT * FROM forma_pagamento fp where fp.categoria = :categoria",nativeQuery = true)
	FormaPagamento findPaymentWayByName(String categoria);

	@Query(value = "SELECT * FROM forma_pagamento fp where fp.id = :id ", nativeQuery = true)
	Optional<FormaPagamento> findById(@Param("id") Long id);

	@Query(value="DELETE FROM forma_pagamento fp where fp.id = :id", nativeQuery = true)
	void delete(@Param("id") Long id);

	@Query(value = "SELECT new com.money.model.dto.FormaPagamentoDTO(fp.id, fp.categoria) FROM FormaPagamento fp")
	List<FormaPagamentoDTO> listPaymentWay();

	@Query(value = "INSERT INTO forma_pagamento fp  values(categoria)", nativeQuery = true)
	void savePaymentWay(@Param("categoria") String categoria);

	@Query(value = "UPDATE forma_pagamento fp SET categoria = :categoria WHERE fp.id = :id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("categoria") String categoria);
}
