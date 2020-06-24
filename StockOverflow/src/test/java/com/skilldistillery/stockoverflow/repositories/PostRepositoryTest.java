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

import com.skilldistillery.stockoverflow.entities.Post;

@SpringBootTest
class PostRepositoryTest {
	
	@Autowired
	private PostRepository postRepo;

	@Test
	@DisplayName("find all posts")
	void test() {
		List<Post> posts = postRepo.findAll();
		assertNotNull(posts);
		assertTrue(posts.size() > 0);
	}
	@Test
	@DisplayName("find one post")
	void test1() {
		Optional<Post> postOpt = postRepo.findById(1);
		assertTrue(postOpt.isPresent());
		assertEquals("Should I buy FakeStock?",postOpt.get().getTitle());
	}

}
