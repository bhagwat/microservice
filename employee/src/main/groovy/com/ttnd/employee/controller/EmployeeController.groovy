package com.ttnd.employee.controller

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
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

    @Autowired
    Random random

    @HystrixCommand(fallbackMethod = "defaultEmployeeList")
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> list() {
        if (random.nextBoolean()) {
            println "list::: Returning employees"
            return employeeRepository.findAll();
        } else {
            println "list::: Throwing NPE"
            throw new NullPointerException("Null pointer exception thrown explicitly")
        }
    }

    public List<Employee> defaultEmployeeList() {
        println "defaultList::: Default list is returned"
        []
    }
}