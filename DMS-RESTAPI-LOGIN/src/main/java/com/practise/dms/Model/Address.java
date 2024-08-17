package com.practise.dms.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressid;
	private String street;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private long pincode;
	@OneToOne
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	public long getAddressid() {
		return addressid;
	}

	public void setAddressid(long addressid) {
		this.addressid = addressid;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}
