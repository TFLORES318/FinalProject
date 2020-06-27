package com.skilldistillery.stockoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.stockoverflow.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {
	
	Stock findByCompanyName(String companyName);
	@Modifying
	@Query(value="INSERT INTO `user_stock` (`user_id`, `stock_symbol`) VALUES (:userId, :stockId)", nativeQuery=true)
	void queryAddStock(@Param("userId")int userId, @Param("stockId") String stockId);

}
