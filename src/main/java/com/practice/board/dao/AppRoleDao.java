package com.practice.board.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.board.entity.UserRole;

@Repository
@Transactional
public class AppRoleDao {

	@Autowired
	private EntityManager entityManager;
	
	public List<String> getRoleNames(Long userId) {
		String sql = ""
				+ "select ur.appRole.roleName "
				+ "from " + UserRole.class.getName() + " ur "
				+ "where ur.appUser.userId = :userId";
		Query query = entityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
}
