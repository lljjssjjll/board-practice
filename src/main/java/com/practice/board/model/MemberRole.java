package com.practice.board.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "member_role")
public class MemberRole implements Serializable {
	private static final long serialVersionUID = -2959227427341972299L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(targetEntity = Member.class)
	@Column(name = "id_member")
	@JoinColumn(name = "id")
	private Long idMember;
	
	@Column(name = "role")
	private String role;
	
	public MemberRole() {
		super();
	}
	
	public MemberRole(Long idMember, String role) {
		super();
		this.idMember = idMember;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return String.format("MemberRole[id=%d, idMember=\"%d\", role=\"%s\"]", id, idMember, role);
	}
}
