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

import com.skilldistillery.stockoverflow.entities.UserStockJournal;

@SpringBootTest
class UserStockRepositoryTest {

	@Autowired
	private UserStockJournalRepository usjRepo;
	
	@Test
	@DisplayName("find all userstockjournals")
	void test() {
		List<UserStockJournal> usjs = usjRepo.findAll();
		assertNotNull(usjs);
		assertTrue(usjs.size() > 0);
	}
	@Test
	@DisplayName("find a single one")
	void test1() {
		Optional<UserStockJournal> usjOpt = usjRepo.findById(1);
		assertTrue(usjOpt.isPresent());
		assertEquals("I like this stock and this company seems solid.", usjOpt.get().getContent());
	}

}
