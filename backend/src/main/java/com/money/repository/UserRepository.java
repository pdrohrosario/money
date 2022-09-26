package com.money.repository;

import com.money.model.User;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	@Query(value = "SELECT * FROM user u where u.user_name= :userName", nativeQuery = true)
	Optional<User> findUserByUserName(@Param("userName") String userName);

	@Query(value = "SELECT * FROM user u where u.id=?1 ", nativeQuery = true)
	Optional<User> findUserById(Long userId);

	@Query(value = "SELECT COUNT(u.email) FROM user u", nativeQuery = true)
	Integer countIdUser();

	@Modifying
	@Transactional
	@Query(value = "UPDATE user u SET name = :name, email = :email, password = :password where u.id = :userId",nativeQuery = true)
	void update(@Param("name") String name, @Param("email") String email,@Param("password") String password, @Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM user u where u.id = :userId",nativeQuery = true)
	void delete(@Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO user (email, name, password , user_name )VALUES (:email, :name, :password, :userName);",nativeQuery = true)
	void saveUser(String userName, String email,String name,String password);
}
