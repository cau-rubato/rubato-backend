package org.rubatophil.www.api.service.member;

import lombok.RequiredArgsConstructor;
import org.rubatophil.www.api.domain.Department;
import org.rubatophil.www.api.domain.mapping.MemberInstrument;
import org.rubatophil.www.api.domain.member.ClubMember;
import org.rubatophil.www.api.repository.DepartmentRepository;
import org.rubatophil.www.api.repository.mapping.MemberInstrumentRepository;
import org.rubatophil.www.api.repository.member.ClubMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final MemberInstrumentRepository memberInstrumentRepository;
    private final DepartmentRepository departmentRepository;

    public List<ClubMember> getAllClubMembers() { return this.clubMemberRepository.findAll(); }

    public void addNewClubMember(ClubMember clubMember, Long departmentId, List<MemberInstrument> memberInstruments) {
        //TODO: NoSuchElementException -> Department save
        Department dbDepartment = this.departmentRepository.findById(departmentId).get();
        dbDepartment.addClubMember(clubMember);
        this.clubMemberRepository.save(clubMember);
        this.memberInstrumentRepository.saveAll(memberInstruments);
    }
}
