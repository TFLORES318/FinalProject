package com.skilldistillery.stockoverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.entities.Webinar;
import com.skilldistillery.stockoverflow.repositories.UserRepository;
import com.skilldistillery.stockoverflow.repositories.WebinarRepository;

@Service
public class WebinarServiceImpl implements WebinarService {

	@Autowired
	private WebinarRepository webinarRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Webinar> allWebinars() {
		List<Webinar> allWebinars = webinarRepo.findAll();
		List<Webinar> activeWebinars = new ArrayList<>();
		for (Webinar webinar : allWebinars) {
			if (webinar.getEnabled()) {
				activeWebinars.add(webinar);
			}
			else activeWebinars.remove(webinar);
		}
		return activeWebinars;
	}

	@Override
	public List<Webinar> index(String username) {
		return webinarRepo.findByUsersAttendingUsername(username);
	}

	@Override
	public Webinar show(int webinarId, String username) {
		return webinarRepo.findByIdAndUsersAttendingUsername(webinarId, username);
	}

	@Override
	public Webinar create(String username, Webinar webinar) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			webinar.setEnabled(true);
			webinar.setUserCreator(user);
			webinarRepo.saveAndFlush(webinar);
			return webinar;
		}
		return null;
	}

	@Override
	public Webinar update(String username, int webinarId, Webinar webinar) {
		Webinar updatedWebinar = webinarRepo.findByIdAndUserCreatorUsername(webinarId, username);
		if (updatedWebinar != null) {
			updatedWebinar.setTitle(webinar.getTitle());
			updatedWebinar.setDescription(webinar.getDescription());
			updatedWebinar.setDateTime(webinar.getDateTime());
			updatedWebinar.setPicture(webinar.getPicture());
			updatedWebinar.setMeetingLink(webinar.getMeetingLink());
			updatedWebinar.setMaxAttendees(webinar.getMaxAttendees());
			updatedWebinar.setEnabled(true);

			webinarRepo.saveAndFlush(updatedWebinar);
			return updatedWebinar;

		}
		return null;
	}

	@Override
	public Boolean disable(String username, int webinarId) {
		boolean disabledWebinar = false;
		Webinar webinar = webinarRepo.findByIdAndUserCreatorUsername(webinarId, username);
		if (webinar != null) {
			webinar.setEnabled(false);
			webinarRepo.saveAndFlush(webinar);
			disabledWebinar = true;
		}
		return disabledWebinar;
	}

	@Override
	public List<User> addUserToAttendees(String username, int webinarId) {
		User attendee = userRepo.findByUsername(username);
		Optional<Webinar> webinarOpt = webinarRepo.findById(webinarId);
		if (webinarOpt.isPresent()) {
			Webinar webinar = webinarOpt.get();
			List<User> attendees = webinar.getUsersAttending();
			if (!attendees.contains(attendee)) {
				attendees.add(attendee);
				attendee.getWebinarsAttending().add(webinar);
				userRepo.saveAndFlush(attendee);
				webinarRepo.saveAndFlush(webinar);
			}
			return attendees;
		}
		return null;
	}

	@Override
	public List<User> removeUserToAttendees(String username, int webinarId) {
		User userToRemove = userRepo.findByUsername(username);
		Optional<Webinar> webinarOpt = webinarRepo.findById(webinarId);
		if(webinarOpt.isPresent()) {
			Webinar webinar = webinarOpt.get();
			List<User> attendees = webinar.getUsersAttending();
			if(attendees.contains(userToRemove)) {
				attendees.remove(userToRemove);
				userToRemove.getWebinarsAttending().remove(webinar);
				userRepo.saveAndFlush(userToRemove);
				webinarRepo.saveAndFlush(webinar);
			}
			return attendees;
		}
		return null;
	}

}
