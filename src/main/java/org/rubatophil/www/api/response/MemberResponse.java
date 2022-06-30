package org.rubatophil.www.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class MemberResponse {

    private String name;
    private Integer generation;
    private String instrument;
    private String department;
    private String studentId;

}
