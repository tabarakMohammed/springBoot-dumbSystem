package com.example.Uponinon.Model.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Uponinon.Model.profile;
import com.example.Uponinon.Model.user;

@Repository
public interface profileJpa extends JpaRepository<profile, Long> {
	
	boolean existsCarByUserId(Long id);
	public profile findByUser(Optional<user> userObject);
	public Optional<profile> findById(Long id);
}
