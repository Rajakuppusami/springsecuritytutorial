package com.rks.spring.springsecuritytutorial.controller;

import com.rks.spring.springsecuritytutorial.constant.ApplicationConstant;
import com.rks.spring.springsecuritytutorial.modal.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(ApplicationConstant.REST_PATH)
public class RestEndPointController {

    private static final String template = "Hello, %s! welcome to security tutorials";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(ApplicationConstant.HELLO_WORLD_END_POINT)
    public Greeting helloWorldEndPoint(@RequestParam(name="name", required=false, defaultValue="Stranger") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
