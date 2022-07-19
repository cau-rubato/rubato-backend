package org.rubatophil.www.api.service;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public List<Manager> getAllCurrentManagers() { return this.managerRepository.findAllByStatus(ManagerStatus.ACTIVATED); }

    public void addNewManager(Manager manager) { this.managerRepository.save(manager); }
}
