package com.money.repository;

import com.money.model.Goal;
import com.money.model.User;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.GoalDetalheDTO;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;

public interface GoalRepository extends JpaRepository<Goal,Long>
{
	@Query(value="select new com.money.model.dto.GoalDetalheDTO(g.id, t.category ,u.name, g.amount, g.startDate, g.endDate, g.description) from Goal g inner join TypeSpent t on t.id = g.typeSpentId inner join User u on u.id = g.userId")
	Page<GoalDetalheDTO> findAllGoals(Pageable pageable);

	@Query(value="select * from goal g where g.id = :goalId", nativeQuery = true)
	Optional<Goal> findGoalById(@Param("goalId") Long goalId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO goal ( amount, description, end_date, start_date, user_id ,type_spent_id)"
		+ " VALUES (:amount, :description, :end_date, :start_date, :user_id , :type_spent_id);",nativeQuery = true)
	void saveGoal(@Param("amount") Double amount, @Param("description") String description,
		@Param("end_date") LocalDateTime endDate, @Param("start_date") LocalDateTime startDate,
		@Param("user_id") Long userId, @Param("type_spent_id") Long typeSpentId);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM goal g WHERE g.id = :goalId",nativeQuery = true)
	void deleteGoal(@Param("goalId") Long goalId);

	@Query(value = "SELECT new com.money.model.dto.GoalDetalheDTO(g.id, t.category ,u.name, g.amount, g.startDate, g.endDate, g.description) from Goal g inner join TypeSpent t on t.id = g.typeSpentId inner join User u on u.id = g.userId AND u.id = :userId")
	Page<GoalDetalheDTO> findGoalByUserId(@Param("userId") Long userId, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value="UPDATE goal g SET amount = :amount, description = :description, end_date = :endDate WHERE g.id = :goalId ",nativeQuery = true)
	void update(@Param("amount") Double amount, @Param("description") String description, @Param("endDate") LocalDateTime endDate, @Param("goalId") Long goalId);
}
