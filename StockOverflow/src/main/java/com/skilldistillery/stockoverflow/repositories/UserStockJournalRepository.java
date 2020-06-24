package com.skilldistillery.stockoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.UserStockJournal;

public interface UserStockJournalRepository extends JpaRepository<UserStockJournal, Integer> {

}
