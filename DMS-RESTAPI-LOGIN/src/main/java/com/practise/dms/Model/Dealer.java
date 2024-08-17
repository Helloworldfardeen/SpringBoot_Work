package com.practise.dms.Model;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Dealers")
public class Dealer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * { "email": "deep1@gmail.com", "fname": "Deepak", "lname": "Malhotra",
	 * "password": "Hello123", "dateofbirth": "2017-02-04", "phonenumber":
	 * "9247230764", "address": { "street": "Park Street", "city": "Kolatta",
	 * "pincode": "360041" } }
	 * 
	 */
	@Column(name = "dealer_id")
	private long id;
	@Column(name = "first_name")
	private String fname;
	@Column(name = "last_name")
	private String lname;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateofbirth;
	@Column(name = "phone_number", unique = true)
	private String phonenumber;
	@OneToOne(mappedBy = "dealer", cascade = CascadeType.ALL)
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLastname() {
		return lname;
	}

	public void setLastname(String lastname) {
		this.lname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = password;
		String encodedString;
		encodedString = encoder.encodeToString(normalString.getBytes(StandardCharsets.UTF_8));
		this.password = encodedString;
		// technique for secureing the password
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(password);
//		this.password = hashedPassword;
//		this.password = password;
	}

	public Date getDateOfbirth() {
		return dateofbirth;
	}

	public void setDateOfbirth(Date dateOfbirth) {
		this.dateofbirth = dateOfbirth;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

}
