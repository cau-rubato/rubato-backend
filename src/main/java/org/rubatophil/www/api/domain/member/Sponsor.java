package org.rubatophil.www.api.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SPONSOR")
@Getter @Setter
public class Sponsor extends Member {}
