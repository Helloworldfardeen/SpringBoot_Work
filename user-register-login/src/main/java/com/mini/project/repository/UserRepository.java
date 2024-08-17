package com.mini.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini.project.model.ShowProfile;
import com.mini.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	@Query("select new com.mini.project.model.ShowProfile(u.id, u.name, u.email, au.fullname, au.pnumber, au.address, au.bio) from User u join u.aboutUser au")
	List<ShowProfile> showingDetails();

}
