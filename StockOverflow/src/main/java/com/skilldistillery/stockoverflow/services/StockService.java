package com.skilldistillery.stockoverflow.services;

import java.util.List;

import com.skilldistillery.stockoverflow.entities.Stock;

public interface StockService {
	
	List<Stock> findAllStocks();
	Stock findById(String stockSymbol);
	Stock findByCompanyName(String companyName);
	Stock createStock(String username, Stock stock);
	Stock updateStock(String stockSymbol, Stock stock);

}
