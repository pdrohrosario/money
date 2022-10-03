package com.money.repository;

import com.money.model.TypeSpent;
import com.money.model.dto.TypeSpentDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSpentRepository extends JpaRepository<TypeSpent, Long>
{
	@Query(value = "SELECT * FROM type_spent t where t.category=?1 ", nativeQuery = true)
	TypeSpent findTypeSpentByName(String categoryName);

	@Query(value = "SELECT * FROM type_spent t where t.id = :id ", nativeQuery = true)
	Optional<TypeSpent> findById(@Param("id") Long id);

	@Query(value="DELETE FROM type_spent t where t.id = :id", nativeQuery = true)
	void delete(@Param("id") Long id);

	@Query(value = "SELECT new com.money.model.dto.TypeSpentDTO(t.id, t.category) FROM TypeSpent t")
	List<TypeSpentDTO> listTypeSpent();

	@Query(value = "INSERT INTO type_spent values(typeSpent)", nativeQuery = true)
	void saveTypeSpent(@Param("typeSpent") String typeSpent);

	@Query(value = "UPDATE type_spent t SET category = :typeSpent WHERE t.id = :id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("typeSpent") String typeSpent);
}
