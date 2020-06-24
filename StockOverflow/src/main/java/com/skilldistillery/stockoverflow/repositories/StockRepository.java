package com.skilldistillery.stockoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
	
	Stock findByCompanyName(String companyName);

}
