package com.skilldistillery.stockoverflow.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
		em.close();
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("admin", user.getPassword());
		assertEquals("Admin", user.getFlair());
		assertEquals("Admin", user.getFirstName());
		assertEquals("McAdmin", user.getLastName());
		assertEquals(Role.ADMIN, user.getRole());
		assertEquals("2020-06-05T09:26", user.getCreateDate().toString());
		assertTrue(user.getEnabled());
	}
	
	@Test
	void test_User_creator_of_webinar() {
		assertNotNull(user);
		assertEquals(1, user.getWebinarsUserIsHosting().size());
		assertEquals("Day Trading 101", user.getWebinarsUserIsHosting().get(0).getTitle());
	}
	
	@Test
	void test_User_webinars_attending() {
		assertNotNull(user);
		assertEquals("Getting Started: How to Stock", user.getWebinarsAttending().get(0).getTitle());
	}
	
	@Test
	void test_User_comment() {
		assertNotNull(user);
		assertEquals(1, user.getComments().size());
		assertEquals("I do not love this post!", user.getComments().get(0).getContent());
	}
	
	@Test
	void test_User_post() {
		assertNotNull(user);
		assertEquals(1, user.getPosts().size());
		assertEquals("I dunno they are blowing up but I only have $10...", user.getPosts().get(0).getDescription());
	}
	
	@Test
	void test_User_comment_rating() {
		assertNotNull(user);
		assertEquals(1, user.getCommentRatings().size());
		assertEquals(2, user.getCommentRatings().get(0).getRating());
		assertEquals("Not very helpful Rich", user.getCommentRatings().get(0).getNote());
	}
	
	@Test
	void test_User_webinar_rating() {
		assertNotNull(user);
		assertEquals(1, user.getWebinarRatings().size());
		assertEquals("Very cool Rich thank you", user.getWebinarRatings().get(0).getRatingNote());
	}
	

	@Test
	void test_User_user_stock_journal() {
		assertNotNull(user);
		assertEquals(1, user.getJournalEntries().size());
		assertEquals("I like this stock and this company seems solid.", user.getJournalEntries().get(0).getContent());
		assertEquals("AA", user.getJournalEntries().get(0).getStock().getSymbol());
	}

	@Test
	void test_User_stocks() {
		assertNotNull(user);
		assertEquals(1, user.getStocks().size());
		assertEquals("Arthur Aardvark Inc.", user.getStocks().get(0).getCompanyName());
	}
}
