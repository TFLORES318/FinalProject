package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilldistillery.stockoverflow.entities.CommentRating;
import com.skilldistillery.stockoverflow.entities.CommentRatingId;

@Repository
public interface CommentRatingRepository extends JpaRepository<CommentRating, CommentRatingId> {
}
