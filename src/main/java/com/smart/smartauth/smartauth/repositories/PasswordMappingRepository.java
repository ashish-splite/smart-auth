package com.smart.smartauth.smartauth.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.smart.smartauth.smartauth.entities.PasswordMapping;

public interface PasswordMappingRepository extends CrudRepository<PasswordMapping, Integer> {
    @Query("SELECT pm FROM PasswordMapping pm WHERE pm.username = :username")
    Optional<PasswordMapping> findByUsername(String username);
}
