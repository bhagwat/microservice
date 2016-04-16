package com.ttnd.employee.controller

import com.ttnd.employee.entity.Employee
import com.ttnd.employee.entity.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeId}")
    public Employee get(@PathVariable String employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> list() {
        return employeeRepository.findAll();
    }
}