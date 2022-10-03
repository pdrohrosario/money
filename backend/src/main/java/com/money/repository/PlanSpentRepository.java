package com.money.repository;

import com.money.model.PlanSpent;
import com.money.model.TypeSpent;
import com.money.model.dto.TypeSpentDTO;
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
public interface PlanSpentRepository extends JpaRepository<PlanSpent, Long>
{
	@Query(value = "SELECT * FROM type_spent t where t.category=?1 ", nativeQuery = true)
	TypeSpent findTypeSpentByName(String categoryName);

	@Query(value = "SELECT * FROM plan_spent p where t.id = :id ", nativeQuery = true)
	Optional<PlanSpent> findById(@Param("id") Long id);

	@Query(value = "DELETE FROM plan_spent p where p.id = :id", nativeQuery = true)
	void delete(@Param("id") Long id);

	@Query(value = "SELECT new com.money.model.dto.TypeSpentDTO(t.id, t.category) FROM TypeSpent t")
	List<TypeSpentDTO> listTypeSpent();

	@Query(value = "INSERT INTO type_spent values(typeSpent)", nativeQuery = true)
	void saveTypeSpent(@Param("typeSpent") String typeSpent);

	@Query(value = "UPDATE plan_spent p SET title = :title, amount = :amount, description = :description, start_date = :startDate, end_date = :endDate WHERE p.id = :id", nativeQuery = true)
	void update(@Param("title") String title, @Param("amount") Double amount,
		@Param("description") String description, @Param("startDate") LocalDateTime startDate,
		@Param("endDate") LocalDateTime endDate, @Param("id") Long id);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO plan_spent ( title, amount, description, start_date, end_date, goal_id)"
		+ " VALUES (:title,:amount, :description, :start_date,:end_date; :goalId);", nativeQuery = true)
	void savePlanSpent(@Param("title") String title, @Param("amount") Double amount,
		@Param("description") String description, @Param("start_date") LocalDateTime startDate,
		@Param("end_date") LocalDateTime endDate, @Param("goalId") Long goalId);

	void savePlanSpent(String title, Double amount, String description, LocalDateTime startDate,
		LocalDateTime endDate);

	@Query(value = "SELECT * FROM plan_spent p where p.title = :title and p.start_date = :startDate ", nativeQuery = true)
	Optional<PlanSpent> findByTitleAndStartTitle(@Param("title") String title,
		@Param("startDate") LocalDateTime startDate);
}
