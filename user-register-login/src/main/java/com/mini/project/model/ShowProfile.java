package com.mini.project.model;

//Template class for fetching data from multiple Classes
public class ShowProfile {
	private long id;

	private String name;
	private String email;
//	private String password;
	private String fullname;
	private String pnumber;
	private String address;

	private String bio;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	public ShowProfile(long id, String name, String email, String fullname, String pnumber, String address,
			String bio) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.fullname = fullname;
		this.pnumber = pnumber;
		this.address = address;
		this.bio = bio;
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
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the pnumber
	 */
	public String getPnumber() {
		return pnumber;
	}

	/**
	 * @param pnumber the pnumber to set
	 */
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

}