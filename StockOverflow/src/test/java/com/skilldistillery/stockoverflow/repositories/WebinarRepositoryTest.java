package com.skilldistillery.stockoverflow.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.stockoverflow.entities.Webinar;

@SpringBootTest
class WebinarRepositoryTest {

	@Autowired
	private WebinarRepository webinarRepo;
	
	@Test
	@DisplayName("find Webinar Enabled")
	void test() {
		List<Webinar> webinars = webinarRepo.findByEnabled(true);
		assertNotNull(webinars);
		assertTrue(webinars.size()>0);
	}
	
	@Test
	@DisplayName("find Webinar by id")
	void test1() {
		Optional<Webinar> webinarOptional = webinarRepo.findById(2);
		Webinar webinarTest = webinarOptional.get();
		assertEquals("Getting Started: How to Stock", webinarTest.getTitle());
		assertEquals("The basics of the basics. Please join us for an open forum discussion where you get your questions answered!", webinarTest.getDescription());
		assertEquals(100, webinarTest.getMaxAttendees());
		assertEquals("https://us04web.zoom.us/j/78138276632?pwd=K2RKSFFIOHltU0loV1hVRWhuaVcyUT09", webinarTest.getMeetingLink());
		assertEquals("2020-07-15T10:00", webinarTest.getDateTime().toString());
	}

}
