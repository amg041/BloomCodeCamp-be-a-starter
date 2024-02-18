package com.hcc.repositories;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
Assignment findFirstById (Long id);
}