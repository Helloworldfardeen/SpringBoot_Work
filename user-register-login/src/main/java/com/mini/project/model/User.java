package com.mini.project.model;

import java.util.Base64;
import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private AboutUser aboutUser;


	/**
	 * @return the aboutUser
	 */
	public AboutUser getAboutUser() {
		return aboutUser;
	}

	/**
	 * @param aboutUser the aboutUser to set
	 */
	public void setAboutUser(AboutUser aboutUser) {
		this.aboutUser = aboutUser;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normal = password;
		String encode = encoder.encodeToString(normal.getBytes());
		this.password = encode;
//		this.password = password;
	}

	

}
