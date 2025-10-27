package com.management.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.dto.MemberRequest;
import com.management.dto.MemberResponse;
import com.management.model.Member;
import com.management.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    public MemberResponse addMember(MemberRequest dto) {
        Member member = modelMapper.map(dto, Member.class);
        Member saved = memberRepository.save(member);
        return modelMapper.map(saved, MemberResponse.class);
    }

    // READ ALL
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(member -> modelMapper.map(member, MemberResponse.class))
                .collect(Collectors.toList());
    }

    // READ BY ID
    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return modelMapper.map(member, MemberResponse.class);
    }

    // UPDATE
    public MemberResponse updateMember(Long id, MemberRequest dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        Member updated = memberRepository.save(member);
        return modelMapper.map(updated, MemberResponse.class);
    }

    // DELETE
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // VIEW BORROWED BOOKS
    public List<String> getBorrowedBooksByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return member.getBorrowedbooks()
                .stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }
}