package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
