package org.rubatophil.www.api.domain.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.type.Address;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private LocalDateTime birth;
    @NotNull
    private String phoneNumber;

    @Embedded
    @NotNull
    private Address address;
    @NotNull
    private String profileImage;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;
}
