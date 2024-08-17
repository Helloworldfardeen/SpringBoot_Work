package com.practise.dms.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practise.dms.Model.Dealer;
import com.practise.dms.Model.DealerAndAddressProjection;
import com.practise.dms.Repository.DealerRepsitory;

@Service
public class DealerService {
	@Autowired
	private DealerRepsitory dRepository;
	public Dealer saveDetails (Dealer d)
	{
		return dRepository.save(d);
	}
	public Optional<Dealer> findByEmail(String email)
	{
		return dRepository.findByEmail(email);
	}
//	public List<DealerAndAddressProjection> showDealerDetails()
//	{
//		return dRepository.findSelectedFieldsFromDealerAndAddress();
//	}
}
