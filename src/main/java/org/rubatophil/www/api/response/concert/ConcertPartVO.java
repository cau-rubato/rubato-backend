package org.rubatophil.www.api.response.concert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class ConcertPartVO {

    private String name;
    private List<ConcertMemberVO> members = new ArrayList<>();
}
