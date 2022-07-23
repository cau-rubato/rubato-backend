package org.rubatophil.www.api.service;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.rubatophil.www.api.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public Manager getPresident() { return this.managerRepository.findByManagerType(ManagerType.PRESIDENT); }
    public Manager getVicePresident() { return this.managerRepository.findByManagerType(ManagerType.VICE_PRESIDENT); }
    public Manager getSecretary() { return this.managerRepository.findByManagerType(ManagerType.SECRETARY); }

    public void addNewManager(Manager manager) { this.managerRepository.save(manager); }
}
