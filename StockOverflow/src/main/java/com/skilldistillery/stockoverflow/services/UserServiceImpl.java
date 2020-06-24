package com.skilldistillery.stockoverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User findById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User user = null;
		if(userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public User findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}

	@Override
	public List<User> findUsersAttendingWebinar(int webinarId) {
		List<User> attendees = userRepo.findByWebinarsAttending_Id(webinarId);
		return attendees;
	}

	@Override
	public User updateUser(int userId, User user) {
		Optional<User> userOpt = userRepo.findById(userId);
		User managedUser = null;
		if(userOpt.isPresent()) {
			managedUser = userOpt.get();
//			managedUser.setCommentRatings(user.getCommentRatings());
			managedUser.setComments(user.getComments());
			managedUser.setEmail(user.getEmail());
			managedUser.setEnabled(user.getEnabled());
			managedUser.setFirstName(user.getFirstName());
//			managedUser.setFlair(user.getFlair());
			managedUser.setJournalEntries(user.getJournalEntries());
			managedUser.setLastName(user.getLastName());
			managedUser.setPassword(user.getPassword());
			managedUser.setPosts(user.getPosts());
			managedUser.setProfilePicture(user.getProfilePicture());
			managedUser.setRole(user.getRole());
			managedUser.setStocks(user.getStocks());
			managedUser.setUsername(user.getUsername());
//			managedUser.setWebinarRatings(user.getWebinarRatings());
			managedUser.setWebinarsAttending(user.getWebinarsAttending());
			managedUser.setWebinarsUserIsHosting(user.getWebinarsUserIsHosting());
		}
		return managedUser;
	}

	@Override
	public void disableUser(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if(userOpt.isPresent()) {
			User toDisable = userOpt.get();
			toDisable.setEnabled(false);
			userRepo.saveAndFlush(toDisable);
		}
		
	}

	@Override
	public List<User> findUsersUsernameSearch(String keyword) {
		List<User> searchMatches = userRepo.findByUsernameLike(keyword);
		return searchMatches;
	}
}
