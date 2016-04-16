package com.ttnd.library

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.grails.boot.internal.EnableAutoConfiguration
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@EnableDiscoveryClient
@EnableEurekaClient
@EnableAutoConfiguration
@Configuration
@ComponentScan
class Application extends GrailsAutoConfiguration {

    @Bean
    @LoadBalanced
    RestOperations loadBalanced() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    RestOperations restTemplate() {
        return new RestTemplate();
    }

    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}