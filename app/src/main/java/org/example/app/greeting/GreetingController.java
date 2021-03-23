package org.example.app.greeting;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rahul Chaubey
 */
@RestController
public class GreetingController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}
