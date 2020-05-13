package com.cesi.seatingAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesi.seatingAPI.exception.ResourceNotFoundException;
import com.cesi.seatingAPI.model.Member;
import com.cesi.seatingAPI.repository.MemberRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping("/members")
    public Member createMember(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable(value = "id") Integer memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", memberId));
    }

    @PutMapping("/members/{id}")
    public Member updateMember(@PathVariable(value = "id") Integer memberId,
                                           @Valid @RequestBody Member memberDetails) {

    	Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", memberId));

    	member.setName(memberDetails.getName());
    	member.setSurname(memberDetails.getSurname());

        Member updatedroom = memberRepository.save(member);
        return updatedroom;
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteroom(@PathVariable(value = "id") Integer memberId) {
    	Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("room", "id", memberId));

        memberRepository.delete(member);

        return ResponseEntity.ok().build();
    }
}