package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.rubatophil.www.api.request.NewManager;
import org.rubatophil.www.api.response.ManagerResponse;
import org.rubatophil.www.api.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("v1/managers")
    public Map<String, ManagerResponse> getCurrentManagerInfo() {
        Map<String, ManagerResponse> managerList = new LinkedHashMap<>();

        managerList.put("president", managerService.getPresident() == null ? null : new ManagerResponse(managerService.getPresident()));
        managerList.put("vice president", managerService.getVicePresident() == null ? null : new ManagerResponse(managerService.getVicePresident()));
        managerList.put("secretary", managerService.getSecretary() == null ? null : new ManagerResponse(managerService.getSecretary()));

        return managerList;
    }

    @PostMapping("v1/managers")
    public void postManagerInfo(@Valid @RequestBody NewManager newManager) {

        String[] startedAt = newManager.getStartedAt().split("\\.");

        Manager manager = Manager.builder()
                .managerType(ManagerType.valueOf(newManager.getManagerType().toUpperCase()))
                .startedAt(LocalDate.of(Integer.parseInt(startedAt[0]), Integer.parseInt(startedAt[1]), Integer.parseInt(startedAt[2])))
                .status(newManager.getStatus() == null ? null : ManagerStatus.valueOf(newManager.getStatus()))
                .build();

        managerService.addNewManager(manager);
    }
}