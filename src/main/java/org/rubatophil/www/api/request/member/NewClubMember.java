package org.rubatophil.www.api.request.member;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class NewClubMember {

    @NotNull
    private String name;
    @NotNull
    private String birth;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String zipcode;
    @NotNull
    private String state;        // 시, 도
    @NotNull
    private String city;        // 시, 군, 구
    @NotNull
    private String town;        // 읍, 면, 동
    @NotNull
    private String fullAddress;
    @NotNull
    private Integer generation;
    @NotNull
    private List<String> instruments;
    private String college;
    private String school;
    private String departmentId;
    @NotNull
    private String studentId;

    @Builder
    public NewClubMember(String name, String birth, String phoneNumber, String zipcode, String state, String city, String town, String fullAddress, Integer generation, List<String> instruments, String college, String school, String departmentId, String studentId) {
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.town = town;
        this.fullAddress = fullAddress;
        this.generation = generation;
        this.instruments = instruments;
        this.college = college;
        this.school = school;
        this.departmentId = departmentId;
        this.studentId = studentId;
    }
}
