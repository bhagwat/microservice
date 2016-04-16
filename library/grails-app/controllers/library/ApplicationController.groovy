package library

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.web.client.RestOperations

import javax.inject.Inject

class ApplicationController {
    public final String SERVICE_URL = "http://EMPLOYEES-SERVER"

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

    @Inject
    @LoadBalanced
    RestOperations restOperations;

    def employee() {
        log.info "Getting employee"
        respond restOperations.getForObject(SERVICE_URL + "/employee", LinkedHashMap.class, [:])
    }
}