package com.money.repository;

import com.money.model.KeepMoney;
import com.money.model.dto.GoalDTO;
import com.money.model.dto.KeepMoneyDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeepMoneyRepository
{
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO keep_money ( plan_spent_id, user_id ) VALUES ( :plan_spent_id, :user_id);", nativeQuery = true)
	void saveKeepMoney(@Param("plan_spent_id") Long plan_spent_id, @Param("user_id") Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE keep_money set  plan_spent_id = :planSpentId, user_id = :userId ) VALUES ( :plan_spent_id, :user_id);", nativeQuery = true)
	void update(@Param("planSpentId") Long planSpentId, @Param("userId") Long userId);

	@Query(value = "select new com.money.model.dto.KeepMoneyDTO(k.id, plan.title, plan.amount) from KeepMoney k inner join PlanSpent plan on plan.id = k.planSpent.id inner join User u on u.userName = :userName")
	List<KeepMoneyDTO> findKeepMoneyByUserName(@Param("userName") String userName);

	@Query(value = "select new com.money.model.dto.KeepMoneyDTO(k.id, plan.title, plan.amount) from KeepMoney k inner join PlanSpent plan on plan.id = k.planSpent.id inner join User u on u.id = k.user.id")
	Optional<KeepMoney> findKeepMoneyById(Long goalId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM keep_money k WHERE k.id = :id", nativeQuery = true)
	void deleteKeepMoney(Long id);

	@Query(value = "SELECT * FROM keep_money k inner join user u on k.user_id = u.id and u.user_name = :userName inner join plan_spent p on p.id = k.plan_spent_id and :transferDate between p.start_date and p.end_date", nativeQuery = true)
	KeepMoney findByUsernameAndDate(@Param("userName") String userName, @Param("transferDate") LocalDateTime transferDate);
}
