package com.ecommerce.rest.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/health")
public class HealthCheckRoute {

    @GetMapping("/ping")
    public String healthCheck() {
        return "pong";
    }

}
