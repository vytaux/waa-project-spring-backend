package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PublicController {

    @RequestMapping("/properties")
    public String index() {
        return "Greetings from PublicController!";
    }
}
