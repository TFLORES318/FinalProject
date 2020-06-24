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

public class PostTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post post;
	
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
		post = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		post = null;
		em.close();
	}

	@Test
	void test_Post_entity_mapping() {
		assertNotNull(post);
		assertEquals("Should I buy FakeStock?", post.getTitle());
		assertEquals("I dunno they are blowing up but I only have $10...", post.getDescription());
		assertEquals(6, post.getCreatedAt().getMonthValue());
		assertEquals("2020-06-05T09:26", post.getCreatedAt().toString());
		assertEquals(true, post.isEnabled());
	}
	
	@Test
	void test_user_mapping() {
		assertNotNull(post);
		assertEquals("admin", post.getUser().getUsername());
		
	}
	
	@Test
	void test_comments_mapping() {
		assertNotNull(post);
		assertEquals("IDK lol", post.getComments().get(0).getContent());
		
	}
	
}
