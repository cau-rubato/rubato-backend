package org.rubatophil.www.api.controller;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.type.ManagerStatus;
import org.rubatophil.www.api.domain.type.ManagerType;
import org.rubatophil.www.api.request.NewManager;
import org.rubatophil.www.api.response.ManagerResponse;
import org.rubatophil.www.api.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("v1/managers")
    public List<ManagerResponse> getCurrentManagerInfo() {
        List<ManagerResponse> managerList = new ArrayList<>();

        List<Manager> managerResult = this.managerService.getAllCurrentManagers();

        for (Manager result : managerResult) {

            List<String> instrumentList = new ArrayList<>();

            for (MemberInstrument memberInstrument : result.getClubMember().getMemberInstruments()) {
                instrumentList.add(memberInstrument.getInstrument().toString());
            }

            String memberDepartment = result.getClubMember().getDepartment().getDepartment();
            if (memberDepartment == null) {
                memberDepartment = result.getClubMember().getDepartment().getSchool();
                if (memberDepartment == null) {
                    memberDepartment = result.getClubMember().getDepartment().getCollege();
                }
            }

            managerList.add(ManagerResponse.builder()
                    .managerType(result.getManagerType().toString())
                    .name(result.getClubMember().getName())
                    .profileImage(result.getClubMember().getProfileImage())
                    .instrument(instrumentList)
                    .generation(result.getClubMember().getGeneration())
                    .department(memberDepartment)
                    .admissionYear(result.getClubMember().getStudentId().substring(2, 4))
                    .build()
            );
        }

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
