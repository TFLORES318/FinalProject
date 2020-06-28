package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.Webinar;

public interface WebinarRepository extends JpaRepository<Webinar, Integer> {
	
	List<Webinar> findByEnabled(Boolean enabled);
	
	List<Webinar> findByUsersAttendingUsername(String username);
	
	
	List<Webinar> findByUserCreatorUsername(String username);
	
	Webinar findByIdAndUsersAttendingUsername(int webinarId, String username);
	
	Webinar findByIdAndUserCreatorUsername(int webinarId, String username);
}
