package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ConcertMemberVO {
    private String name;
    private Integer episode;
    private String department;
    private String studentId;
}
