package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnersController {

    @RequestMapping("/properties")
    public String properties() {
        return "Greetings from Owners properties!";
    }
}
