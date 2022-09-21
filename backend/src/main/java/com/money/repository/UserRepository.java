package com.money.repository;

import com.money.model.User;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>
{
	@Query(value = "SELECT * FROM user u where u.email=?1 ", nativeQuery = true)
	Optional<User> findUserByEmail(String email);

	@Query(value = "SELECT * FROM user u where u.id=?1 ", nativeQuery = true)
	Optional<User> findUserById(Long userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE user u SET name = :name, email = :email, password = :password where u.id = :userId",nativeQuery = true)
	void update(@Param("name") String name, @Param("email") String email,@Param("password") String password, @Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM user u where u.id = :userId",nativeQuery = true)
	void delete(@Param("userId") Long userId);

}
