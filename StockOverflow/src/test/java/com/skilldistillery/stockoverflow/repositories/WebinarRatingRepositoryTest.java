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

import com.skilldistillery.stockoverflow.entities.WebinarRating;
import com.skilldistillery.stockoverflow.entities.WebinarRatingId;

@SpringBootTest
class WebinarRatingRepositoryTest {

	@Autowired
	private WebinarRatingRepository webinarRatingRepo;
	
	@Test
	@DisplayName("find all Webinar Rating")
	void test() {
		List<WebinarRating> webinarRatings = webinarRatingRepo.findAll();
		assertNotNull(webinarRatings);
		assertTrue(webinarRatings.size() > 0);
	}
	
	@Test
	@DisplayName("find a single webinar rating")
	void test1() {
		WebinarRatingId wid = new WebinarRatingId(1,1);
		Optional<WebinarRating> webinarRating = webinarRatingRepo.findById(wid);
		assertTrue(webinarRating.isPresent());
		assertEquals("Very cool Rich thank you", webinarRating.get().getRatingNote());
		
	}

}
