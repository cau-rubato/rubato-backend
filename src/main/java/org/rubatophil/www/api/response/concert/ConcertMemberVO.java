package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ConcertMemberVO {
    private String name;
    private Integer episode;
    private String department;
    private String studentId;
}
