package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skilldistillery.stockoverflow.entities.CommentRating;
import com.skilldistillery.stockoverflow.entities.CommentRatingId;

@Repository
public interface CommentRatingRepository extends JpaRepository<CommentRating, CommentRatingId> {
//	List<CommentRating> findByComment_User_Id(int userId);
	
	@Query("select cr from CommentRating cr where cr.comment.user.id = :userId")
	List<CommentRating> queryForRatingsForUser(@Param ("userId") int userId);
}
