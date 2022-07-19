package org.rubatophil.www.api.repository;

import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    List<Manager> findAllByStatus(ManagerStatus status);
}
