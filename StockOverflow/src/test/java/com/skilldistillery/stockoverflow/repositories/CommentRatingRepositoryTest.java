package com.skilldistillery.stockoverflow.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.stockoverflow.entities.CommentRating;

@SpringBootTest
public class CommentRatingRepositoryTest {

	@Autowired
	private CommentRatingRepository commentRatingRepo;
	
	@Test
	@DisplayName("find all Comment Rating")
	void test() {
		List<CommentRating> commentRatings = commentRatingRepo.findAll();
		assertNotNull(commentRatings);
		assertTrue(commentRatings.size() > 0);
	}
	
//	@Test
//	@DisplayName("find a single comment rating")
//	void test1() {
//		CommentRating commentRating = commentRatingRepo.findById();
//	}
}
