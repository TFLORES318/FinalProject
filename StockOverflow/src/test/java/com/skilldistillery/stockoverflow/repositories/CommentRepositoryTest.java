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

import com.skilldistillery.stockoverflow.entities.Comment;

@SpringBootTest
class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepo;
	
	@Test
	@DisplayName("find all Comments")
	void test() {
		List<Comment> comments = commentRepo.findAll();
		assertNotNull(comments);
		assertTrue(comments.size() > 0);
	}
	
	@Test
	@DisplayName("find a single comment")
	void test1() {
		Optional<Comment> comment = commentRepo.findById(1);
		assertTrue(comment.isPresent());
		assertEquals("IDK lol", comment.get().getContent());
		
	}
	
	@Test
	@DisplayName("find comments by post ID")
	void test2() {
		List<Comment> comments = commentRepo.findByPost_Id(1);
		assertNotNull(comments);
		assertTrue(comments.size() > 0);
		assertEquals("IDK lol", comments.get(0).getContent());
	}

}
