package com.example.teamproject.dto;

import com.example.teamproject.entity.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString @Slf4j
public class MemberDto {
    private String username;
    private String password;

    public Member toEntity(){
        return new Member(username, password);
    }
}
