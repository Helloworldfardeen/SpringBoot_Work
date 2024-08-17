package com.training.cdac.pms.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.cdac.pms.exception.ResourcesNotFoundException;
import com.training.cdac.pms.model.Student;
import com.training.cdac.pms.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentControler {
	@Autowired
	StudentService studService;

	@PostMapping("/student")
	public ResponseEntity<Student> saveStudent(@Validated @RequestBody Student stud) {
		try {
			Student s = studService.saveDetails(stud);
			return ResponseEntity.status(HttpStatus.CREATED).body(s);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/student")
	public ResponseEntity<List<Student>> showStudentDetails(Student stud) {
		try {
			List<Student> s = studService.showDetails();

			return ResponseEntity.status(HttpStatus.OK).body(s);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> showDetailsById(@PathVariable(value = "id") int id)
			throws ResourcesNotFoundException {
		try {
			Student s = studService.showDetailsById(id)
					.orElseThrow(() -> new ResourcesNotFoundException("Product is Not found By this Id"));
			if (s.toString().isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.OK).body(s);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<Map<String, Boolean>> removeDataById(@PathVariable(value = "id") int id)
			throws ResourcesNotFoundException {
		Student s = studService.showDetailsById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Product Id Not Found"));
		studService.DeleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

//  withoutLambaExpression
	@PutMapping("student/{id}")
	public ResponseEntity<Student> updatebyId(@PathVariable(value = "id") int id, @Validated @RequestBody Student s)
			throws ResourcesNotFoundException {
		try {
			Optional<Student> optional = studService.showDetailsById(id);
			if (optional.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
			Student store = optional.get();
			// Do not update the studentID
//		store.setStudentID(s.getStudentID());
			store.setStudentName(s.getStudentName());
			store.setStudentGrade(s.getStudentGrade());
			store.setStudentMarks(s.getStudentMarks());

			final Student updatedstudent = studService.saveDetails(store);
			return ResponseEntity.status(HttpStatus.OK).body(updatedstudent);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

//	@GetMapping("student/{grade}")
//	public ResponseEntity<List<Student>> searchByGradeController(@PathVariable(value = "grade") char g) {
//		try {
//			List<Student> gr = studService.evaluteByMarks(g);
//			if (gr.isEmpty()) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//			}
//			return ResponseEntity.status(HttpStatus.ACCEPTED).body(gr);
//		} catch (Exception e) {
//			e.getMessage();
//			return ResponseEntity.internalServerError().body(null);
//		}
//	}

	/*
	 * @Autowired private StudentService studentService;
	 * 
	 * @PostMapping("/student") public ResponseEntity<List<Student>>
	 * createStudents(@RequestBody List<Student> students) { List<Student>
	 * createdStudents = studentService.createStudents(students); return
	 * ResponseEntity.ok(createdStudents); }
	 */

	/*
	 * without lambda expression finding id using url.
	 * 
	 * @GetMapping("/student/{id}") public ResponseEntity<Student>
	 * showDetailsById(@PathVariable(value = "id") int id) throws
	 * ResourcesNotFoundException { try { Optional<Student> optionalStudent =
	 * studService.showDetailsById(id); if (!optionalStudent.isPresent()) { throw
	 * new ResourcesNotFoundException("Product is Not found By this Id"); } Student
	 * s = optionalStudent.get(); return
	 * ResponseEntity.status(HttpStatus.OK).body(s); } catch (Exception e) {
	 * e.printStackTrace(); return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); } }
	 */

}
