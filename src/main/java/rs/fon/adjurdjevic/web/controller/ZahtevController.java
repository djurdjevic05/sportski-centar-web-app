package rs.fon.adjurdjevic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.adjurdjevic.db.model.Usluga;
import rs.fon.adjurdjevic.db.model.Zahtev;
import rs.fon.adjurdjevic.service.UslugaService;
import rs.fon.adjurdjevic.service.ZahtevService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@Controller
public class ZahtevController {
    private ZahtevService zahtevService;
    private UslugaService uslugaService;

    @Autowired
    public ZahtevController(ZahtevService zahtevService, UslugaService uslugaService) {
        this.zahtevService = zahtevService;
        this.uslugaService = uslugaService;
    }

    @GetMapping("/zahtevi")
    public String showAllKorisnici(@ModelAttribute("messageOk") String messageOk, @ModelAttribute("messageProblem") String messageProblem, Model model, @AuthenticationPrincipal User user) {
        boolean isAdmin = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if ("ADMIN".equalsIgnoreCase(authority.getAuthority())) {
                isAdmin = true;
            }
        }
        if (isAdmin) {
            model.addAttribute("zahtevi", zahtevService.findAllRequests());
        } else {
            model.addAttribute("zahtevi", zahtevService.findAllRequests(user.getUsername()));
        }
        model.addAttribute("messageOk", messageOk);
        model.addAttribute("messageProblem", messageProblem);
        return"zahtevi";
    }

    @PostMapping("/zahtevi/{id}/approve")
    public String approveRequest(@PathVariable Long id, Model model, @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        //zahtevService.approveRequest(id, user.getUsername());
    	redirectAttributes.addFlashAttribute("messageProblem", "Odgovor na zahtev nije sacuvan! Zahtev nije azuriran!");
//        model.addAttribute("usluge", uslugaService.findAllServices());
        return "redirect:/zahtevi";
    }

    @PostMapping("/zahtevi/{id}/decline")
    public String declineRequest(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        zahtevService.declineRequest(id, user.getUsername());
//        model.addAttribute("usluge", uslugaService.findAllServices());
        return "redirect:/zahtevi";
    }

    @GetMapping("/zahtevi/nov-zahtev")
    public String showZahtevCreationForm(Model model) {
        Zahtev zahtev = new Zahtev();
        model.addAttribute("zahtev", zahtev);
        model.addAttribute("sveusluge", uslugaService.findAllServices());
        return "nov-zahtev";
    }

    @PostMapping(value = "/zahtevi/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewUsluga(@Valid @ModelAttribute Zahtev zahtev, BindingResult result, Model model, @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "nov-zahtev";
        }
        zahtev.setDatumKreiranja(new Date());
        zahtev.setStatusZahteva("PODNET");
        zahtevService.saveRequest(zahtev, user.getUsername());
//        model.addAttribute("usluge", uslugaService.findAllServices());
        redirectAttributes.addFlashAttribute("messageOk", "Zahtev sacuvan!");
        return "redirect:/zahtevi";
    }
}
