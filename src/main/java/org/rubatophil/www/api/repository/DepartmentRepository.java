package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
