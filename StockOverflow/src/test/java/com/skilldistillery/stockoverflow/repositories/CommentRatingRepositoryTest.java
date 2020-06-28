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

import com.skilldistillery.stockoverflow.entities.CommentRating;
import com.skilldistillery.stockoverflow.entities.CommentRatingId;

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
	
	@Test
	@DisplayName("find a single comment rating")
	void test1() {
		CommentRatingId cid = new CommentRatingId(1,1);
		Optional<CommentRating> commentRating = commentRatingRepo.findById(cid);
		assertTrue(commentRating.isPresent());
		assertEquals("IDK lol", commentRating.get().getComment().getContent());
		
	}
	
	@Test
	@DisplayName("find all rating for user")
	void test2() {
		List<CommentRating> commentRatings = commentRatingRepo.queryForRatingsForUser(1);
		assertNotNull(commentRatings);
		assertEquals("IDK lol", commentRatings.get(0).getComment().getContent());
		
	}
}
