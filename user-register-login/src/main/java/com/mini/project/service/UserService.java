package com.mini.project.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.project.model.ShowProfile;
import com.mini.project.model.User;
import com.mini.project.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository ur;
	public User saveingDetails (User u)
	{
		return ur.save(u);
	}
	public Optional<User> getbyEmail(String email)
	{
		return ur.findByEmail(email);
	}
	public List<ShowProfile> showingDetailsService(){
		return ur.showingDetails();
	}
}
