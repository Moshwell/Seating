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
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/api/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/members")
    public Member createMember(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/idPerMember/{id}")
    public Member getIdPerMember(@PathVariable Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/members/{id}")
    public Member updateMember(@PathVariable Integer id,
                                           @Valid @RequestBody Member memberDetails) {

    	Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", id));

    	member.setName(memberDetails.getName());
    	member.setSurname(memberDetails.getSurname());

        Member updatedroom = memberRepository.save(member);
        return updatedroom;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/members/{id}")
    public ResponseEntity<?> deleteroom(@PathVariable Integer id) {
    	Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("member", "id", id));

        memberRepository.delete(member);

        return ResponseEntity.ok().build();
    }
}