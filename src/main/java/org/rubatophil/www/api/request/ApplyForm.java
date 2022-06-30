package org.rubatophil.www.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class ApplyForm {

    private String name;
    private String studentId;
    private String birth;
    private String phoneNumber;
    private String address;
    private String introduction;

}
