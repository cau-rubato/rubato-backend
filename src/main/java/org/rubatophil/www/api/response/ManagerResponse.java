package org.rubatophil.www.api.response;

import lombok.*;
import org.rubatophil.www.api.domain.Manager;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagerResponse {

    private String name;
    private String profileImage;
    private List<String> instrument;
    private Integer generation;
    private String department;
    private String admissionYear;

    @Builder
    public ManagerResponse(String name, String profileImage, List<String> instrument, Integer generation, String department, String admissionYear) {
        this.name = name;
        this.profileImage = profileImage;
        this.instrument = instrument;
        this.generation = generation;
        this.department = department;
        this.admissionYear = admissionYear;
    }

    public ManagerResponse(Manager manager) {

        List<String> instrumentList = new ArrayList<>();

        for (MemberInstrument memberInstrument : manager.getClubMember().getMemberInstruments()) {
            instrumentList.add(memberInstrument.getInstrument().toString());
        }

        String memberDepartment = manager.getClubMember().getDepartment().getDepartment();
        if (memberDepartment == null) {
            memberDepartment = manager.getClubMember().getDepartment().getSchool();
            if (memberDepartment == null) {
                memberDepartment = manager.getClubMember().getDepartment().getCollege();
            }
        }

        this.name = manager.getClubMember().getName();
        this.profileImage = manager.getClubMember().getProfileImage();
        this.instrument = instrumentList;
        this.generation = manager.getClubMember().getGeneration();
        this.department = memberDepartment;
        this.admissionYear = manager.getClubMember().getStudentId().substring(2, 4);
    }
}
