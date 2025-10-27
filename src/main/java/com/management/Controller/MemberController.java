package com.management.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.management.Service.MemberService;
import com.management.dto.MemberRequest;
import com.management.dto.MemberResponse;



@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // CREATE
    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest dto) {
        return memberService.addMember(dto);
    }

    // READ ALL
    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.getAllMembers();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public MemberResponse getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public MemberResponse updateMember(@PathVariable Long id, @RequestBody MemberRequest dto) {
        return memberService.updateMember(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "Member deleted successfully.";
    }

    // VIEW BORROWED BOOKS
    @GetMapping("/{id}/borrowed-books")
    public List<String> getBorrowedBooksByMember(@PathVariable Long id) {
        return memberService.getBorrowedBooksByMember(id);
    }
}