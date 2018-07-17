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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_generator")
	@SequenceGenerator(
			name = "country_id_generator",
			sequenceName = "country_country_id_seq",
			initialValue = 1,
			allocationSize = 1)
	@Column(name = "country_id", nullable = false)
	private Long countryId;

	@Column(name = "country_code", length = 2, nullable = false)
	private String countryCode;
	
	@Column(name = "country_name", length = 100, nullable = false)
	private String countryName;
	
}
