package com.practice.board.dao;

import org.springframework.data.repository.CrudRepository;

import com.practice.board.model.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
	Member findByEmail(String email);
}
