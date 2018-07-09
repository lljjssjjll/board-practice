package com.practice.board.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.board.entity.Country;

@Repository
@Transactional
public class CountryDao {

	@Autowired
	private EntityManager entityManager;
	
	//국가코드로 국가 조회
	public Country findCountryByCode(String countryCode) {
		String sql = ""
				+ "select cc "
				+ "from " + Country.class.getName() + " cc "
				+ "where cc.countryCode = :countryCode";
		Query query = entityManager.createQuery(sql, Country.class);
		query.setParameter("countryCode", countryCode);
		try {
			return (Country) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	//국가 전체 조회
	public List<Country> findCountrys() {
		String sql = ""
				+ "select cc "
				+ "from " + Country.class.getName() + " cc ";
		Query query = entityManager.createQuery(sql, Country.class);
		try {
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}
	
}
