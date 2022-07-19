package org.rubatophil.www.api.response;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagerResponse {

    private String managerType;
    private String name;
    private String profileImage;
    private List<String> instrument;
    private Integer generation;
    private String department;
    private String admissionYear;

    @Builder
    public ManagerResponse(String managerType, String name, String profileImage, List<String> instrument, Integer generation, String department, String admissionYear) {
        this.managerType = managerType;
        this.name = name;
        this.profileImage = profileImage;
        this.instrument = instrument;
        this.generation = generation;
        this.department = department;
        this.admissionYear = admissionYear;
    }
}
