package com.ttnd.employee

import com.ttnd.employee.entity.Employee
import com.ttnd.employee.entity.EmployeeRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestTemplate

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
public class EmployeeBoot {
    @Bean
    @LoadBalanced
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(EmployeeBoot.class)
        bootstrap(applicationContext.getBean(EmployeeRepository));
    }

    public static void bootstrap(EmployeeRepository employeeRepository) {
        if (employeeRepository.count() < 1) {
            10.times { index ->
                Employee employee = new Employee(
                        email: "bhagwat.kumar+${index}@gmail.com",
                        fullName: "Name $index",
                        managerEmail: "notknown@xyz.com"
                )
                employeeRepository.save(employee)
            }
        }
    }
}