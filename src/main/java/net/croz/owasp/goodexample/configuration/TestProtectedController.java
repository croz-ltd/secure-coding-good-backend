package net.croz.owasp.goodexample.configuration;

import net.croz.owasp.goodexample.annotation.CurrentUser;
import net.croz.owasp.goodexample.entity.UserSeller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testAuth")
public class TestProtectedController {


    @GetMapping
    private String testAuth(@CurrentUser UserSeller userSeller) {
        System.out.println("AAAAAAAA");
        return "test";
    }
}
