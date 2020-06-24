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

class CommentRatingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private CommentRating commentRating;

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
		commentRating = em.find(CommentRating.class, new CommentRatingId(1,1));
	}

	@AfterEach
	void tearDown() throws Exception {
		commentRating = null;
		em.close();
	}

	@Test
	void test_Stock_entity_mapping() {
		assertNotNull(commentRating);
		assertEquals("IDK lol", commentRating.getComment().getContent());
	}

}
