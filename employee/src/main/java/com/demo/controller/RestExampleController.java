package com.demo.controller;

import com.demo.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestExampleController {

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list() {
        // use the "smart" Eureka-aware RestTemplate
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
}
