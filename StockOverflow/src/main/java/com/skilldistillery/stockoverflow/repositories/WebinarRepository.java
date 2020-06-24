package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.Webinar;

public interface WebinarRepository extends JpaRepository<Webinar, Integer> {
	List<Webinar> findByEnabled(boolean enabled);
}
