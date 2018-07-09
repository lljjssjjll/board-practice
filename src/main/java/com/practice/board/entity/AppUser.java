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
	name = "app_user",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "app_user_user_name_key",
			columnNames = "user_name"
		)
	}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
	@SequenceGenerator(name = "user_id_generator", sequenceName = "app_user_user_id_seq")
	@Column(name = "user_id", nullable = false)
	private Long userId;
	
	@Column(name = "user_name", length = 100, nullable = false)
	private String userName;
	
	@Column(name = "enc_password", length = 200, nullable = false)
	private String encPassword;
	
	@Column(name = "enabled", length = 1, nullable = false)
	private boolean enabled;
	
	@Column(name = "gender", length = 1, nullable = false)
	private String gender;
	
	@Column(name = "first_name", length = 100, nullable = true)
	private String firstName;
	
	@Column(name = "last_name", length = 100, nullable = true)
	private String lastName;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "country_code", length = 2, nullable = false)
	private String countryCode;
	
}
