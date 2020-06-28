package com.skilldistillery.stockoverflow.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Stock stock;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("StockOverflowPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		stock = em.find(Stock.class, "AAPL");
	}

	@AfterEach
	void tearDown() throws Exception {
		stock = null;
		em.close();
	}

	@Test
	void test_Stock_entity_mapping() {
		assertNotNull(stock);
		assertEquals("AAPL", stock.getSymbol());
		assertEquals("Apple", stock.getCompanyName());
		assertEquals("NASDAQ", stock.getExchange());
		assertEquals(6408, stock.getChartId());
	}
	
	
}
