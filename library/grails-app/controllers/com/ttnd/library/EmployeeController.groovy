package com.ttnd.library

import com.ttnd.library.dto.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class EmployeeController {
    @Autowired
    DiscoveryClient discoveryClient;

    def clients() {
        respond discoveryClient.getServices().collectEntries() { String serviceName ->
            [
                    (serviceName): discoveryClient.getInstances(serviceName).collect { ServiceInstance serviceInstance ->
                        ["Host": serviceInstance.host, port: serviceInstance.port, serviceID: serviceInstance.serviceId]
                    }
            ]
        }
    }

    @Autowired
    @LoadBalanced
    RestTemplate loadBalanced;

    public List<Employee> employees() {
        ResponseEntity<List<Employee>> exchange =
                loadBalanced.exchange(
                        "http://employees-server/employee",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Employee>>() {
                        },
                        (Object) "mstine");

        respond exchange.getBody();
    }
}