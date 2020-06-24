package com.skilldistillery.stockoverflow.services;

import java.util.List;

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
		return webinarRepo.findAll();
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
			updatedWebinar.setEnabled(webinar.getEnabled());
			
			webinarRepo.saveAndFlush(updatedWebinar);
			return updatedWebinar;
		
		}
		return null;
	}

}
