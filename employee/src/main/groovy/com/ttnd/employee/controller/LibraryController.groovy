package com.ttnd.employee.controller

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.ttnd.employee.dto.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    @HystrixCommand(fallbackMethod = "defaultBookList")
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list() {
        ResponseEntity<List<Book>> exchange =
                this.loadBalanced.exchange(
                        "http://library-server/book/index",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Book>>() {
                        },
                        (Object) "mstine");

        return exchange.getBody();
    }

    public List<Book> defaultBookList() {
        println "Library server is now DOWN"
        return []
    }
}
