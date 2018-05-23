package com.practice.board.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member implements Serializable {
	private static final long serialVersionUID = -4959187993246955981L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	public Member() {
		super();
	}
	
	public Member(Member member) {
		super();
		this.name = member.name;
		this.email = member.email;
		this.password = member.password;
	}
	
	@Override
	public String toString() {
		return String.format("Member[id=%d, name=\"%s\", email=\"%s\", password=\"%s\"]", id, name, email, password);
	}
}
