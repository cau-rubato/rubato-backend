package org.rubatophil.www.api.domain.concert;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.rubatophil.www.api.domain.mapping.concertMember.ConcertMember;
import org.rubatophil.www.api.domain.type.ApplyStatus;
import org.rubatophil.www.api.domain.type.Location;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONCERT")
@Getter @Setter
public class Concert {

    @Id @GeneratedValue
    @Column(name = "concert_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private String posterUrl;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
    private List<ConcertMember> concertMembers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    private ApplyStatus applyStatus;

    @LastModifiedDate
    @NotNull
    private LocalDateTime modifiedAt;
    @CreatedDate
    @NotNull
    private LocalDateTime createdAt;

    @PrePersist
    public void PrePersist() {
        this.applyStatus = this.applyStatus == null ? ApplyStatus.OPENED : this.applyStatus;
    }
}
