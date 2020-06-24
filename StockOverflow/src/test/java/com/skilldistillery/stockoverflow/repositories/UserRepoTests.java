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

import com.skilldistillery.stockoverflow.entities.User;

@SpringBootTest
public class UserRepoTests {
	
	@Autowired
	private UserRepository userRepo;

	@Test
	@DisplayName("find user by username")
	void test() {
		User user = userRepo.findByUsername("rwasek");
		assertNotNull(user);
		assertEquals("rwasek", user.getUsername());
	}

	@Test
	@DisplayName("find by ID")
	void test1() {
		Optional<User> user = userRepo.findById(2);
		User userTest = user.get();
		assertEquals("rwasek", userTest.getUsername());
	}
	
	@Test
	@DisplayName("find users attending webinar")
	void test2() {
		List<User> attendees = userRepo.findByWebinarsAttending_Id(2);
		assertTrue(attendees.size() > 0);
		assertEquals("admin", attendees.get(0).getUsername());
	}
	
	@Test
	@DisplayName("keyword search test")
	void test3() {
		List<User> searchMatches = userRepo.findByUsernameLike("admin");
		assertTrue(searchMatches.size() > 0);
		assertEquals("admin", searchMatches.get(0).getUsername());
	}
}
