package com.ttnd.employee.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Value('${app.message}')
    private String message

    @RequestMapping(method = RequestMethod.GET)
    public Map getConfig() {
        [message: message]
    }
}