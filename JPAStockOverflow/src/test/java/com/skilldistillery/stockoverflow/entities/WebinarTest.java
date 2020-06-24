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

class WebinarTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Webinar webinar;

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
		webinar = em.find(Webinar.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		webinar = null;
		em.close();
	}

	@Test
	void test() {
		assertEquals("Day Trading 101", webinar.getTitle());
		assertEquals("Don't even know what questions to ask? This is the place for you!", webinar.getDescription());
		assertEquals("2020-07-15T08:00", webinar.getDateTime().toString());
		assertEquals("2020-06-05T09:26", webinar.getCreatedAt().toString());
		assertEquals("https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09", webinar.getMeetingLink());
		assertEquals(30, webinar.getMaxAttendees());
		assertTrue(webinar.getEnabled());
		
	}

}
