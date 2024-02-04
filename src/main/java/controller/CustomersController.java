package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

    @RequestMapping("/offers")
    public String offers() {
        return "Greetings from Customers offers!";
    }
}
