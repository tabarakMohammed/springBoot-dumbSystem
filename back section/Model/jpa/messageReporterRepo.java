package com.example.Uponinon.Model.jpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Uponinon.Model.messageReporter;

@Transactional
@Repository
public interface messageReporterRepo extends JpaRepository<messageReporter, Long> {
	 
	public  Page<messageReporter> findAll(Pageable pageable); 

}
