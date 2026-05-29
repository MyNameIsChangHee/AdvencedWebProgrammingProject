package com.example.teamproject.controller;

import com.example.teamproject.Repository.MemberRepository;
import com.example.teamproject.dto.MemberDto;
import com.example.teamproject.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/items/login")
    public String login() {
        return "login";
    }

    @GetMapping("/items/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupcheck(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
        return "redirect:/items/login";
    }

    @PostMapping("/logincheck")
    public String logincheck(String username, String password, HttpSession session) {
        Member member = memberRepository.findByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/items";
        }
        return "redirect:/items/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/items";
    }
}