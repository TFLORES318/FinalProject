package com.skilldistillery.stockoverflow.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.stockoverflow.entities.Stock;

@SpringBootTest
class StockRepositoryTest {
	
	@Autowired
	private StockRepository stockRepo;

	@Test
	void test_findById() {
		Optional <Stock> stock = stockRepo.findById("AA");
		Stock stockTest = stock.get();
		assertEquals("NASDAQ", stockTest.getExchange());
	}
	
	@Test
	void test_findByCompanyName() {
		Stock stock = stockRepo.findByCompanyName("Arthur Aardvark Inc.");
		assertNotNull(stock);
		assertEquals("AA", stock.getSymbol());
	}

}
