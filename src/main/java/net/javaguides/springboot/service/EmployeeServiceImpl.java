package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();//these are inbuilt methods which comes when you extend JPARepository interface
		
		
	}


	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
		
	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional=employeeRepository.findById(id);
		Employee employee=null;
		if(optional.isPresent()) {
			employee=optional.get();
		}else {
			throw new RuntimeException("Employee not found for id ::"+id);
		}
		
		return employee;
	}


	@Override
	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
		
	}


	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable=PageRequest.of(pageNo-1, pageSize);//PageRequest is a class which implements pageable interface
		return this.employeeRepository.findAll(pageable);
	}
	
	
	
	
}
