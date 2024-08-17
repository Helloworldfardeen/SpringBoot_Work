package com.practise.dms.Model;
//Template class for fetching data from multiple Classes
public class DealerAndAddressProjection {
	
	private long id;
	private String fname;
	private String lname;
	private String phonenumber;
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
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	/**
	 * @param phonenumber the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	public DealerAndAddressProjection() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DealerAndAddressProjection(long id, String fname, String lname, String phonenumber, String email,
			String street, String city, int pincode) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the pincode
	 */
	public int getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	private String email;
	private String street;
	private  String city;
	private  int pincode;


}
