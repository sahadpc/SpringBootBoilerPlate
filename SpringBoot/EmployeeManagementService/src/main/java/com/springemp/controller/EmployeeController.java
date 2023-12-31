package com.springemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.springemp.entity.EmployeeDependents;
import com.springemp.entity.EmployeeDet;
import com.springemp.modal.DependentModal;
import com.springemp.modal.EmployeeReq;
import com.springemp.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// <summary>
	// API to add employee
	// </summary>
	
	@PostMapping("/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeReq employeeReq) {
		employeeService.saveEmployee(employeeReq);
		return new ResponseEntity<String>("Employee Added Successfully",HttpStatus.CREATED);
	}
	
	// <summary>
	// API to list all employees
	// </summary>
	
	@GetMapping("/employee")
	public ResponseEntity<?> findAllEmployee(){
		 List<EmployeeDet> list=employeeService.getAllEmployee();
		 return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// <summary>
	// API to add Dependent to employee
	// </summary>
	
	@PostMapping("/addDepById/{id}")
	public ResponseEntity<String> addDepended(@PathVariable ("id")  int id,@RequestBody DependentModal dependentModal) {
		employeeService.addDepDetails(dependentModal, id);
		return new ResponseEntity<String>("Dependend Added Successfully",HttpStatus.OK);
	}
	
	// <summary>
	// API to get list of dependent corresponding to a employee
	// </summary>
	
	@GetMapping("/showDepById/{id}")
	public ResponseEntity<?> showAllDep(@PathVariable ("id")  int id){
		List<EmployeeDependents> list =employeeService.findAllDep(id);
		return new ResponseEntity<>(list, HttpStatus.OK) ;
	}
	
	// <summary>
	// API to delete employee
	// </summary>
	
	@GetMapping("/deleteById/{id}")
	public ResponseEntity<String>  deleteEmployee(@PathVariable ("id") int id) {
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee Deleted Successfully",HttpStatus.OK);
		
	}	
	
	// <summary>
	// API to update employee details
	// </summary>
	
	@PostMapping("/updateEmployee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable ("id") int id,@RequestBody EmployeeReq employeeReq )
	{
		employeeService.updateEmployee(employeeReq,id);
		return new ResponseEntity<String>("Employ Updated Successfully",HttpStatus.OK);
	}
	
	// <summary>
	// API to sort employee list by Salary	
	// </summary>
	
	@GetMapping("/sortBySalary")
	public ResponseEntity<?> sortEmployee() {
		List<EmployeeDet> list=employeeService.sortAllEmployee();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}	
	
	// <summary>
	// API to get paginated  list of employee
	// </summary>
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<?> findPaginated(@PathVariable("pageNo") int pageNo) {
		int pageSize=3;
		Page<EmployeeDet> page=employeeService.findPaginated(pageNo, pageSize);
		List<EmployeeDet> employees=page.getContent();
		return new ResponseEntity<>(employees,HttpStatus.OK);
	}	
	
	// <summary>
	// API to get paginated  list of employee sorted by salary
	// </summary>
	
	@GetMapping("/pagee/{pageNoo}")
	public List<EmployeeDet> findPaginatedd(@PathVariable("pageNoo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDirec") String sortDirec
			) {
		int pageSize=3;
		Page<EmployeeDet> page=employeeService.findPaginatedd(pageNo, pageSize,sortField,sortDirec);
		List<EmployeeDet> employees=page.getContent();
		return employees;
	}
		
	
	
}
