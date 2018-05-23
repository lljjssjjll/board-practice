package com.practice.board.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.practice.board.model.Member;

public interface MemberRoleRepository extends CrudRepository<Member, Long> {
	@Query("select mr.role from MemberRole mr, Member m where m.email = ?1 and mr.idMember = m.id")
	public List<String> findRoleByEmail(String email);
}
