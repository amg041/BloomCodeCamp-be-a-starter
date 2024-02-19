package com.hcc.repositories;

import com.hcc.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(AuthorityEnum name);
}
