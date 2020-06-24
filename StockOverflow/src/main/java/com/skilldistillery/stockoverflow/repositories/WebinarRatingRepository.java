package com.skilldistillery.stockoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.stockoverflow.entities.WebinarRating;
import com.skilldistillery.stockoverflow.entities.WebinarRatingId;

public interface WebinarRatingRepository extends JpaRepository<WebinarRating, WebinarRatingId> {

}
