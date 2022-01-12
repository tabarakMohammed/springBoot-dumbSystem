package com.example.Uponinon.Model.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.example.Uponinon.Model.message;
import com.example.Uponinon.Model.profile;;

@Repository
public interface messageJpa extends JpaRepository<message, Long>{
	
  public  Page<message> findAllByProfile( Optional<profile> opw, Pageable pageable); 
  public  List<message> findAllByProfileId(Long id, Pageable pageable);
//  @Query("update message m set m.messageContent = newMessage where m.id = id")
//  public message updateMessageContaint(@Param("id") long id, @Param("newMessage") String newMasseage );
//  @Procedure()
//  int messsagesReportedUpdate(String new_message , long id);
  
  @Query( value ="{call messsagesReportedUpdate(:new_message,:id)}" , nativeQuery = true)
  public message messsagesReportedUpdate(@Param("new_message") String new_message , @Param("id") long id);

}
