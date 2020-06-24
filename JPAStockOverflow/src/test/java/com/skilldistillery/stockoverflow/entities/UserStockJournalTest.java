package com.skilldistillery.stockoverflow.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserStockJournalTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserStockJournal userStockJournal;

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
		userStockJournal = em.find(UserStockJournal.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		userStockJournal = null;
		em.close();
	}

	@Test
	void test() {
		assertEquals("I like this stock and this company seems solid.", userStockJournal.getContent());
		assertEquals("2020-06-05T09:26", userStockJournal.getCreatedAt().toString());
	}

}
