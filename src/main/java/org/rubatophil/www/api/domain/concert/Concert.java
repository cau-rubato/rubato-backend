package org.rubatophil.www.api.domain.concert;

import lombok.*;
import org.rubatophil.www.api.domain.mapping.ConcertPamphlet;
import org.rubatophil.www.api.domain.mapping.concertMember.ConcertMember;
import org.rubatophil.www.api.domain.type.ApplyStatus;
import org.rubatophil.www.api.domain.type.Location;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONCERT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Concert {

    @Id @GeneratedValue
    @Column(name = "concert_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    private String name;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private String posterUrl;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<ConcertMember> concertMembers = new ArrayList<>();

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<ConcertPamphlet> concertPamphlets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ApplyStatus applyStatus;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    public Concert(String name, LocalDateTime date, Location location, String posterUrl) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.posterUrl = posterUrl;
    }

    public void addConcertMember(ConcertMember concertMember) {
        this.concertMembers.add(concertMember);
        concertMember.setConcert(this);
    }

    public void addConcertPamphlet(ConcertPamphlet concertPamphlet) {
        this.concertPamphlets.add(concertPamphlet);
        concertPamphlet.setConcert(this);
    }

    @PrePersist
    public void PrePersist() {
        this.applyStatus = this.applyStatus == null ? ApplyStatus.CLOSED : this.applyStatus;
    }
}
