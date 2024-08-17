package com.practise.dms.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.dms.Model.Dealer;
import com.practise.dms.Model.DealerAndAddressProjection;

public interface DealerRepsitory extends JpaRepository<Dealer, Long> {
	// Custom Method to fetch record/object based on email field - non id field.
	public Optional<Dealer> findByEmail(String email);
	
	
   //may be the problem into the phone number fields
//	  @Query("SELECT new com.practise.dms.Model.DealerAndAddressProjection"
//			  + "(d.id, d.fname,d.lname,d.phonenumber," +
//			 "d.email, a.street, a.city,a.pincode) " + "FROM Dealer d JOIN d.address a")
//	public List<DealerAndAddressProjection> findSelectedFieldsFromDealerAndAddress();
//	 List<DealerAndAddressProjection> findSelectedFieldsFromDealerAndAddress();

}
