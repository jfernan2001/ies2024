package com.example.demo;


// https://www.baeldung.com/dockerizing-spring-boot-application

@RestController
public class DockerMessageController {
    @GetMapping("/messages")
    public String getMessage() {
        return "Hello from Docker!";
    }
}