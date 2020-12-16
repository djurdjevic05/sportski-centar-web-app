package rs.fon.adjurdjevic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.adjurdjevic.db.model.Korisnik;
import rs.fon.adjurdjevic.service.KorisnikService;

import javax.validation.Valid;

@Controller
public class KorisnikController {

    private KorisnikService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public KorisnikController(KorisnikService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/korisnici")
    public String showAllKorisnici(@ModelAttribute("messageOk") String messageOk, @ModelAttribute("messageProblem") String messageProblem,Model model) {
        model.addAttribute("korisnici", userService.findAllUsera());
        model.addAttribute("messageOk", messageOk);
        model.addAttribute("messageProblem", messageProblem);
        return "korisnici";
    }

    @GetMapping("/korisnici/nov-korisnik")
    public String showKorisnikCreationForm(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        model.addAttribute("sverole", userService.getRoles());
        model.addAttribute("svamesta", userService.findAllPlaces());
        return "nov-korisnik";
    }

    @GetMapping("/korisnici/{id}")
    public String showKorisnikById(@PathVariable Long id, Model model) {
        model.addAttribute("korisnik", userService.findUser(id));
        model.addAttribute("sverole", userService.getRoles());
        model.addAttribute("svamesta", userService.findAllPlaces());
        return "izmeni-korisnika";
    }

    @PostMapping(value = "/korisnici/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewKorisnik(@Valid @ModelAttribute Korisnik korisnik, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "nov-korisnik";
        }
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        userService.saveUser(korisnik);
        redirectAttributes.addFlashAttribute("messageOk", "Korisnik uspesno kreiran.");
        return "redirect:/korisnici";
    }

    @PostMapping("/korisnici/{id}/update")
    public String updateKorisnik(@PathVariable Long id, @Valid @ModelAttribute Korisnik korisnik, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "izmeni-korisnika";
        }
        try {
            userService.updateUser(id, korisnik);
            redirectAttributes.addFlashAttribute("messageOk", "Kokrisnik uspesno snimljen");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageProblem", "Kokrisnik nije uspesno snimljen");
        }

        return "redirect:/korisnici";
    }

    @PostMapping("/korisnici/{id}/delete")
    public String deleteKorisnik(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("messageOk", "Kokrisnik uspesno izbrisan");
        return "redirect:/korisnici";
    }
}
