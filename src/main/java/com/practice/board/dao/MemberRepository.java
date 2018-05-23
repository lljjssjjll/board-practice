package com.practice.board.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.practice.board.model.Member;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
	List<Member> findAll(Sort sort);
}
