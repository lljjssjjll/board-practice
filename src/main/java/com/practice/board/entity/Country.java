package com.practice.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name = "country",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "country_country_code_country_name_key",
			columnNames = {
				"country_code",
				"country_name"
			}
		)
	}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "country_code", length = 2, nullable = false)
	private String countryCode;
	
	@Column(name = "country_name", length = 100, nullable = false)
	private String countryName;
	
}
