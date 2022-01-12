package com.example.Uponinon.Model.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uponinon.Model.role;

public interface roleJpa extends JpaRepository<role, Integer> {
	
	@Transactional
 public role findByAuthority(String authority);



}
