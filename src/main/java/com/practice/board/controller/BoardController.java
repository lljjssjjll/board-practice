package com.practice.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.board.dao.MemberRepository;
import com.practice.board.model.Member;

@RestController
public class BoardController {
	@Autowired
	private MemberRepository memRep;
	
//	@RequestMapping("/home")
//	public String home() {
//		return "home";
//	}
//	
//	@RequestMapping("/user")
//	public String user() {
//		return "user";
//	}
//	
//	@RequestMapping("/admin")
//	public String admin() {
//		return "admin";
//	}
//	
//	@RequestMapping("/login")
//	public String login() {
// 		return "login";
//	}
//	
//	@RequestMapping("/403")
//	public String error403() {
//		return "403";
//	}
	
	@RequestMapping("/allMembers")
	public String allMembers() {
		String result = "";
		List<Member> memberList = memRep.findAll(new Sort(Direction.DESC, "id"));
		for(Member member : memberList) {
			result += member.toString() + "<br />";
		}
		return result;
	}
	
}
