package net.croz.owasp.goodexample.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testAuth")
public class TestProtectedController {


    @GetMapping
    private String testAuth() {
        return "test";
    }
}
