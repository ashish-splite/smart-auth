package com.smart.smartauth.smartauth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.smart.smartauth.smartauth.entities.RoleUserMapping;

import jakarta.transaction.Transactional;

public interface RoleUserMappingRepository extends CrudRepository<RoleUserMapping, Integer> {
    @Query("SELECT ur FROM RoleUserMapping ur WHERE ur.userid = :userid")
    Optional<RoleUserMapping> findByUserid(Integer userid);

    @Query("DELETE FROM RoleUserMapping rm WHERE rm.userid = :userid")
    @Modifying
    @Transactional
    void deleteByUserid(Integer userid);

    @Modifying
    @Transactional
    @Query("DELETE FROM RoleUserMapping rm WHERE rm.userid IN :userIds")
    void deleteAllByUserids(List<Integer> userIds);
}
