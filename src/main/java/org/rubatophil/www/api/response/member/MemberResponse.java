package org.rubatophil.www.api.response.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class MemberResponse {

    private String name;
    private Integer generation;
    private String instrument;
    private String department;
    private String studentId;

}
