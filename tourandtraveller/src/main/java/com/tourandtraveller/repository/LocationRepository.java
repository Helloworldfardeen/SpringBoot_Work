package com.tourandtraveller.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.mini.project.model.User;
import com.tourandtraveller.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	public Optional<Location> findByName(String Name);


}

