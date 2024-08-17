package com.tourandtraveller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourandtraveller.model.Location;
import com.tourandtraveller.repository.LocationRepository;


@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	
	public List<Location> saveDetails(List<Location> l) {
		return locationRepository.saveAll(l);
	}

	public List<Location> showDetails() {
		return locationRepository.findAll();
	}

	public Optional<Location> showDetailsById(long id) {
		return locationRepository.findById(id);
	}

	

}
