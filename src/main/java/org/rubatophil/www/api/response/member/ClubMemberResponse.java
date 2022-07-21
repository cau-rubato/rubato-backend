package org.rubatophil.www.api.response.member;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMemberResponse {

    private Long id;
    private String name;
    private Integer generation;
    private List<String> instruments;
    private String department;
    private String studentId;

    @Builder
    public ClubMemberResponse(Long id, String name, Integer generation, List<String> instruments, String department, String studentId) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.instruments = instruments;
        this.department = department;
        this.studentId = studentId;
    }
}
