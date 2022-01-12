package com.example.Uponinon.Model.jpa;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.userVerifyEmail;

@Repository
public interface verfiyEmailJpa extends JpaRepository<userVerifyEmail, Long> {


	public userVerifyEmail findBytoken(String token);
	public userVerifyEmail findByUser(user userEmail);
	
	@Transactional
	@Modifying
	@Query("update userVerifyEmail a set a.token =:token, a.tokenDate =:tokenDate where user_id =:Id")
    int update(@Param("Id") Long Id, @Param("token") String token,@Param("tokenDate") LocalDateTime tokenDate);
		
}
