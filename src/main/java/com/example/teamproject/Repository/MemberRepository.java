package com.example.teamproject.Repository;

import com.example.teamproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByUsername(String username);
}
