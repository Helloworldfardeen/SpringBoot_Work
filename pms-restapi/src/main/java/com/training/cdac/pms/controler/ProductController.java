package com.training.cdac.pms.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.training.cdac.pms.exception.ResourcesNotFoundException;
import com.training.cdac.pms.model.Product;
import com.training.cdac.pms.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/*Spring RestController annotation is used to create RESTful web services using Spring MVC. 
 * Spring RestController takes care of mapping request data to the defined request handler method. 
 * Once response body is generated from the handler method, it converts it to JSON or XML response. 
 * @RestController indicates that this class handles HTTP requests and automatically 
 * serializes the results to JSON.
 * @RequestMapping - maps HTTP request with a path to a controller 
 * */
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
//base URL for this controller's endpoints.
//we are making so many controller 
//so imagine there will the one who can manage to whom we can executed
public class ProductController {
	@Autowired
	private ProductService PS;
	// Use returntype as ResponseEntity<T> for controller methods
	// because client making request so server will give the response
	// that s why...

	/*
	 * ResponseEntity represents an HTTP response, including headers, body, and
	 * status.
	 * 
	 * @RequestBody annotation automatically deserializes the JSON into a Java type
	 */

	// Open PostMan, make a POST Request - http://localhost:8082/pms/api/products
	// Select body -> raw -> JSON
	// Insert JSON product object.
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product) {
		/*
		 * Why we use ResponseEntity because we want status also no the object we can
		 * return Product also so but it will not return the full information as the
		 * developer ResponseEntity Will return more information
		 * 
		 * @RequestBody= used to convert json Object into Java Object
		 * 
		 */
		try {
			Product p = PS.saveProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(p);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() {
		try {
			List<Product> p = PS.showAll();
			return ResponseEntity.ok(p);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductbyId(@PathVariable(value = "id") long pid)
			throws ResourcesNotFoundException {
		Product p = PS.getSingleObject(pid)
				.orElseThrow(() -> new ResourcesNotFoundException("Your Id Is Not Found" + pid));
		return ResponseEntity.ok(p);
	}

//   updateing
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProductbyId(@PathVariable(value = "id") long pid,
			@Validated @RequestBody Product product) throws ResourcesNotFoundException {
		Product p = PS.getSingleObject(pid)
				.orElseThrow(() -> new ResourcesNotFoundException("Product Not Found for this Id " + pid));
		p.setName(product.getName());
		p.setBrand(product.getBrand());
		p.setMadein(product.getMadein());
		p.setPrice(product.getPrice());

		final Product updateedproduct = PS.saveProduct(p);
		return ResponseEntity.ok().body(updateedproduct);
	}

	// delete_product_remaining
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProductByID(@PathVariable(value = "id") long pid)
			throws ResourcesNotFoundException {
		Product p = PS.getSingleObject(pid)
				.orElseThrow(() -> new ResourcesNotFoundException("Product Not Found for this Id" + pid));
		PS.deleteProduct(pid);
		Map<String, Boolean> response = new HashMap<>();

		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}

	// searchproduct Remaining...
//	@GetMapping("/products/search") // http://localhost:8082/pms/api/products/search?name=Cell%20Phone
//	public ResponseEntity<?> searchByFullName(@RequestParam(value = "name") String name) {
//		List<Product> p;
//		try {
//			p = PS.searchProduct(name);
//			if (p.isEmpty()) {
//				return new ResponseEntity<>("No product Foound With this Name", HttpStatus.NOT_FOUND);
//			}
//			return new ResponseEntity<>(p, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("DataBase Error");
//			return new ResponseEntity<>("An error occurred while searching for products.",
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	@GetMapping("/products/search")
	public ResponseEntity<?> searchProductByName(@PathVariable(value ="name") String name)
	throws ResourcesNotFoundException
	{
		try {
		List<Product> p = PS.searchProduct(name);
		if(p.isEmpty())
		{
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//			return new ResponseEntity<>(p,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(p,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>("An error occurred while searching for products",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	
	/*
	 * @GetMapping("/products/{id}") public ResponseEntity<Product>
	 * getProductById(@PathVariable(value = "id") Long pId) throws
	 * ResourcesNotFoundException { Product p = PS.getSingleObject(pId)
	 * .orElseThrow(() -> new
	 * ResourcesNotFoundException("Product Not Found for this Id :" + pId));
	 * 
	 * return ResponseEntity.ok().body(p); }
	 * 
	 * /* these all are jugard because they are Handle return exception
	 * 
	 * @GetMapping("/products/{id}") public ResponseEntity<Optional<Product>>
	 * getProductbyId(@PathVariable(value = "id") long pid) { try {
	 * Optional<Product> product = PS.getSingleObject(pid); return
	 * ResponseEntity.ok(product);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); } }
	 *
	 * @GetMapping("/products/{id}") public ResponseEntity<Optional<Product>>
	 * getProductId(@PathVariable(value ="id")Long pid) { return
	 * ResponseEntity.ok(PS.getSingleObject(pid)); }
	 * 
	 * @GetMapping("/products/{id}") public ResponseEntity<Product>
	 * getProductById(@PathVariable(value = "id") long pId) throws
	 * ResourcesNotFoundException { try { Product p = PS.getSingleObject(pId)
	 * .orElseThrow(() -> new
	 * ResourcesNotFoundException("Product Not Found for this Id :" + pId)); return
	 * ResponseEntity.ok().body(p); } catch (ResourcesNotFoundException e) {
	 * e.printStackTrace(); System.out.println(("Product Not Found for this Id :" +
	 * pId)); return ResponseEntity.ok().body(null); }
	 * 
	 * }
	 */

}
