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

}
