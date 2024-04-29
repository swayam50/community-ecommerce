package com.ecommerce.rest.resource.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class HealthResource {

    @GetMapping("/ping")
    public String healthCheck() {
        return "pong";
    }


}
