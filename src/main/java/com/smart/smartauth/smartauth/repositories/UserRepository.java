package com.smart.smartauth.smartauth.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.smart.smartauth.smartauth.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<User> findByUsername(String username);

}
