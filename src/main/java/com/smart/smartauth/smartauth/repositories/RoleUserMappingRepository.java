package com.smart.smartauth.smartauth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.smart.smartauth.smartauth.entities.RoleUserMapping;

public interface RoleUserMappingRepository extends CrudRepository<RoleUserMapping, Integer> {
    @Query("SELECT ur FROM RoleUserMapping ur WHERE ur.userid = :userid")
    Optional<RoleUserMapping> findByUserid(Integer userid);
}
