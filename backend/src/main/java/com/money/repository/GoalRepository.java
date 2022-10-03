package com.money.repository;

import com.money.model.Goal;
import com.money.model.User;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.GoalDetalheDTO;
import com.money.model.dto.GoalOldenDTO;
import com.money.model.dto.GoalStatusDTO;
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
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface GoalRepository extends JpaRepository<Goal,Long>
{
	@Query(value="select new com.money.model.dto.GoalDTO(g.id, p.title, t.category, p.amount) from Goal g inner join TypeSpent t on t.id = g.typeSpent.id inner join PlanSpent p on p.id = g.planSpent.id inner join User u on u.id = g.user.id and u.userName = :userName")
	List<GoalDTO> findGoalsByUserName(@Param("userName") String userName);

	@Query(value="select new com.money.model.dto.GoalDetalheDTO(g.id, p.title, t.category ,u.name, p.amount, p.startDate, p.endDate, p.description) from Goal g inner join PlanSpent p on p.id = g.planSpent.id inner join TypeSpent t on t.id = g.typeSpent.id inner join User u on u.id = g.user.id and g.id = :goalId")
	GoalDetalheDTO findGoalDetalheById(@Param("goalId") Long goalId);

	@Query(value="select * from goal g where g.id = :goalId", nativeQuery = true)
	Optional<Goal> findGoalById(@Param("goalId") Long goalId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO goal (user_id ,type_spent_id, plan_spent)"
		+ " VALUES (:type_spent_id, :user_id, :planSpentId);", nativeQuery = true)
	void saveGoal(@Param("planSpentId") Long planSpentId, @Param("user_id") Long userId,
		@Param("type_spent_id") Long typeSpentId);

	@Query(value =
		"select new com.money.model.dto.GoalStatusDTO(SUM(t.amountSpent), p.amount, p.startDate, p.endDate) from Transfer t"
			+ " inner join Goal g on t.typeSpent.id = g.typeSpent.id inner join PlanSpent p on p.id = g.planSpent.id inner join User u on u.id = t.user.id and g.user.id = u.id and u.id = :userId")
	GoalStatusDTO verifyGoalStatus(@Param("goalId") Long goalId, @Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM goal g WHERE g.id = :goalId",nativeQuery = true)
	void deleteGoal(@Param("goalId") Long goalId);


	@Modifying
	@Transactional
	@Query(value="UPDATE goal g SET g.type_spent_id = :typeSpentId WHERE g.id = :goalId ",nativeQuery = true)
	void update( @Param("typeSpentId") Long typeSpentId, @Param("goalId") Long goalId);

	@Modifying
	@Transactional
	@Query(value="UPDATE goal g SET amount = :amount, description = :description, end_date = :endDate WHERE g.id = :goalId ",nativeQuery = true)
	void update(@Param("amount") Double amount, @Param("description") String description, @Param("endDate") LocalDateTime endDate, @Param("goalId") Long goalId);

	@Query(value = " select g.id, p.amount,  p.amount - SUM(t.amountSpent), ts.category, p.startDate, p.endDate from Goal g "
		+ "inner join Transfer t on t.typeSpent.id = g.typeSpent.id inner join TypeSpent ts on ts.id = g.typeSpent.id "
		+ "inner join PlanSpent p on p.id = g.planSpent.id "
		+ "inner join User u on  u.id = t.user.id and u.id = g.user.id and u.userName = :userName "
		+ "where p.endDate < :nowDate and t.transferDate between p.startDate and p.endDate")
	List<GoalOldenDTO> findOldenGoalsByUserName(@Param("userName") String userName,
		@Param("noWDate") LocalDateTime nowDate);
}
