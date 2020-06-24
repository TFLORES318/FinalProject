package com.skilldistillery.stockoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilldistillery.stockoverflow.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	List<User> findByWebinarsAttending_Id(int webinarId);
	List<User> findByUsernameLike(String keyword);
}
