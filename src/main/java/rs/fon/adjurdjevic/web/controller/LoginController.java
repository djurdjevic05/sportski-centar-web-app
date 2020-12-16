package rs.fon.adjurdjevic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.fon.adjurdjevic.service.SecurityService;

@Controller
public class LoginController {

    private SecurityService securityService;

    @Autowired
    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Korisničko ime i šifra nisu ispravni.");

        if (logout != null)
            model.addAttribute("message", "Uspešno ste odjavljeni iz sistema.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "index";
    }
}
