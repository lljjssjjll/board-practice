package com.practice.board.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.board.entity.AppUser;
import com.practice.board.formbean.AppUserForm;

@Repository
@Transactional
public class AppUserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//사용자명으로 계정 조회
	public AppUser findUserAccountByUserName(String userName) {
		String sql = ""
				+ "select au "
				+ "from " + AppUser.class.getName() + " au "
				+ "where au.userName = :userName";
		Query query = entityManager.createQuery(sql, AppUser.class);
		query.setParameter("userName", userName);
		try {
			return (AppUser) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	//이메일로 계정 조회
	public AppUser findUserAccountByEmail(String email) {
		String sql = ""
				+ "select au "
				+ "from " + AppUser.class.getName() + " au "
				+ "where au.email = :email";
		Query query = entityManager.createQuery(sql, AppUser.class);
		query.setParameter("email", email);
		try {
			return (AppUser) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	//전체 계정 조회
	public List<AppUser> findUserAccounts() {
		String sql = ""
				+ "select au "
				+ "from " + AppUser.class.getName() + " au ";
		Query query = entityManager.createQuery(sql, AppUser.class);
		try {
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	//계정 생성
	public AppUser createAccount(AppUserForm form) {
		String encPassword = bCryptPasswordEncoder.encode(form.getPassword());
		
		AppUser appUser = new AppUser(
				null, form.getUserName(), encPassword,
				false, form.getGender(), form.getFirstName(),
				form.getLastName(), form.getEmail(), form.getCountryCode());
		//entityManager.getTransaction().begin();
		entityManager.persist(appUser);
		//entityManager.getTransaction().commit();
		
		return appUser;
	}
	
}
