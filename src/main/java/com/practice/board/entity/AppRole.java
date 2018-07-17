package com.practice.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name = "app_role",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "app_role_role_name_key",
			columnNames = "role_name"
		)
	}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rold_id_generator")
	@SequenceGenerator(
			name = "rold_id_generator",
			sequenceName = "app_role_rold_id_seq",
			initialValue = 1,
			allocationSize = 1)
	@Column(name = "role_id", nullable = false)
	private Long roleId;
	
	@Column(name = "role_name", length = 100, nullable = false)
	private String roleName;
	
}
