package com.money.repository;

import com.money.model.TypeSpent;
import com.money.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSpentRepository extends JpaRepository<TypeSpent, Long>
{
	@Query(value = "SELECT id FROM type_spent t where t.category=?1 ", nativeQuery = true)
	TypeSpent findTypeSpentByName(String categoryName);
}
