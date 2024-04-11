package com.smart.smartauth.smartauth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.smart.smartauth.smartauth.entities.PasswordMapping;

import jakarta.transaction.Transactional;

public interface PasswordMappingRepository extends CrudRepository<PasswordMapping, Integer> {
    @Query("SELECT pm FROM PasswordMapping pm WHERE pm.userid = :userid")
    Optional<PasswordMapping> findByUserid(Integer userid);

    @Query("DELETE FROM PasswordMapping pm WHERE pm.userid = :userid")
    @Modifying
    @Transactional
    void deleteByUserid(Integer userid);

    @Modifying
    @Transactional
    @Query("DELETE FROM PasswordMapping pm WHERE pm.userid IN :userIds")
    void deleteAllByUserids(List<Integer> userIds);
}
