package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.entities.Webinar;
import com.skilldistillery.stockoverflow.services.WebinarService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4205" })
public class WebinarController {

	@Autowired
	WebinarService webinarSvc;

	@GetMapping("webinars")
	public List<Webinar> getAllWebinars() {
		return webinarSvc.allWebinars();
	}

	@GetMapping("webinars/{wId}")
	public Webinar getWebinar(HttpServletRequest req, HttpServletResponse res, @PathVariable int wId,
			Principal principal) {
		Webinar webinar = webinarSvc.show(wId, principal.getName());
		if (webinar == null) {
			res.setStatus(404);
		}
		return webinar;
	}

	@PostMapping("webinars")
	public Webinar create(HttpServletRequest req, HttpServletResponse res, @RequestBody Webinar webinar,
			Principal principal) {
		try {
			webinar = webinarSvc.create(principal.getName(), webinar);
			if (webinar == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(webinar.getId());
				res.addHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			webinar = null;
		}
		return webinar;
	}

	@PutMapping("webinars/{webinarId}")
	public Webinar update(HttpServletRequest req, HttpServletResponse res, @PathVariable int webinarId,
			@RequestBody Webinar webinar, Principal principal) {
		try {
			webinar = webinarSvc.update(principal.getName(), webinarId, webinar);
			if (webinar == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			webinar = null;
		}
		return webinar;

	}
	
	@PutMapping("webinars/delete/{webinarId}")
	public void disableWebinar(@PathVariable int webinarId, Principal principal,
			HttpServletResponse res) {
		try {
			if (webinarSvc.disable(principal.getName(), webinarId)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@PutMapping("webinars/{webinarId}/signUp")
	public List<User> signUpForWebinar(@PathVariable int webinarId, Principal principal, HttpServletResponse res){
		List<User> attendees = null;
		try {
			attendees = webinarSvc.addUserToAttendees(principal.getName(), webinarId);
				if(attendees == null) {
					res.setStatus(400);
				}
		} catch (Exception e) {
			res.setStatus(404);
			e.printStackTrace();
		}
		return attendees;
	}
	
	@PutMapping("webinars/{webinarId}/withdraw")
	public List<User> withdrawromrWebinar(@PathVariable int webinarId, Principal principal, HttpServletResponse res){
		List<User> attendees = null;
		try {
			attendees = webinarSvc.removeUserToAttendees(principal.getName(), webinarId);
			if(attendees == null) {
				res.setStatus(400);
			}
		} catch (Exception e) {
			res.setStatus(404);
			e.printStackTrace();
		}
		return attendees;
	}
	
	
	
	
	
	
	
	
	
	
}