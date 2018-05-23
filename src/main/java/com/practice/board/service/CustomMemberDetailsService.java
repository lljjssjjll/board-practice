package com.practice.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.board.dao.MemberRepository;
import com.practice.board.dao.MemberRoleRepository;
import com.practice.board.model.Member;

@Service("customMemberDetailsService")
public class CustomMemberDetailsService implements UserDetailsService {
	private final MemberRepository memberRepository;
	private final MemberRoleRepository memberRoleRepository;
	
	@Autowired
	public CustomMemberDetailsService(MemberRepository memberRepository, MemberRoleRepository memberRoleRepository) {
		this.memberRepository = memberRepository;
		this.memberRoleRepository = memberRoleRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username);
		if(member == null) {
			throw new UsernameNotFoundException("No user with email : " + username);
		}
		List<String> memberRoles = memberRoleRepository.findRoleByEmail(username);
		return new CustomMemberDetails(member, memberRoles);
	}

}
