package com.example.Uponinon.Model.jpa;

import  java.time.LocalDateTime;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uponinon.Model.user;
import com.example.Uponinon.Model.userForgotPassword;

@Repository
public interface forgetPasswordJpa extends JpaRepository<userForgotPassword, Long> {
	
	public userForgotPassword findBytoken(String token);
	public userForgotPassword findByUser(user UserForgot);


    @Transactional
	@Modifying
	@Query("update userForgotPassword a set a.token =:token, a.tokenDate =:tokenDate where user_id =:Id")
	 int update(@Param("Id") Long Id, @Param("token") String token,@Param("tokenDate") LocalDateTime tokenDate);
	
}

