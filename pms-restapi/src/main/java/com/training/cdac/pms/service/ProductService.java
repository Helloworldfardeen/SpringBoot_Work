package com.training.cdac.pms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.cdac.pms.model.Product;
import com.training.cdac.pms.repository.ProductRepository;

@Service
public class ProductService  {
	@Autowired//we need to perfome dependency injection
	private ProductRepository PR;

    //creating
	public Product saveProduct(Product p) {
		return PR.save(p); // Invokes save() method predefined in JPA repo
	}

	// reading
	public List<Product> showAll() {
		return PR.findAll(); //Defined in JPA repo.
	}

	// searching
	public Optional<Product> getSingleObject(long id) {
		return PR.findById(id);
	}

	//delete
	public void deleteProduct(long id) {
		PR.deleteById(id);
	}
	//searching product with customQuery
//	public List<Product> searchProduct(String name)
//	{
//		return PR.findProductsWithFull(name);
//	}
	public List<Product>searchProduct(String name)
	{
		return PR.findProductsByNameContainingIgnoreCase(name);
	}
	
	
}
