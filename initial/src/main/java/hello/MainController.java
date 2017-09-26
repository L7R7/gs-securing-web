package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/")
    String getMainPage() {
        return "home";
    }

    @GetMapping(path = "/home")
    String getHomePage() {
        return "home";
    }

    @GetMapping(path = "/hello")
    String getHelloPage() {
        return "hello";
    }

    @GetMapping(path = "/admin")
    String getAdminPage() {
        return "admin";
    }

    @GetMapping(path = "/login")
    String getLoginPage() {
        return "login";
    }
}
