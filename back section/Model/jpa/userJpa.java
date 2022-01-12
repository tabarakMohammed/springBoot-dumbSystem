package com.example.Uponinon.Model.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Uponinon.Model.user;

@Repository
public interface userJpa extends JpaRepository<user, Long> {
 
	
 public Optional<user> findById(Long id);
 public user findByEmail(String email);
 public user findByFirstName(String firstName);
 public user findByUsername(String username);
 
 @Query( value ="{call `allDisabledUser`()}" , nativeQuery = true)
 public List<user> disabledUsers();


 
}
