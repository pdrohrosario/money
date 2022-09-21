package com.money.repository;

import com.money.model.Goal;
import com.money.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal,Long>
{
	Page<Goal> findGoalByUser(Optional<User> user, Pageable pageable);
}
