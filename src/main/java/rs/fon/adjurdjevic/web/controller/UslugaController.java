package rs.fon.adjurdjevic.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rs.fon.adjurdjevic.db.model.Korisnik;
import rs.fon.adjurdjevic.db.model.Usluga;
import rs.fon.adjurdjevic.service.UslugaService;

import javax.validation.Valid;

@Controller
public class UslugaController {

    private UslugaService uslugaService;

    public UslugaController(UslugaService uslugaService) {
        this.uslugaService = uslugaService;
    }

    @GetMapping("/usluge")
    public String showAllUsluge(@ModelAttribute("messageOk") String messageOk, @ModelAttribute("messageProblem") String messageProblem, Model model) {
        model.addAttribute("usluge", uslugaService.findAllServices());
        model.addAttribute("messageOk", messageOk);
        model.addAttribute("messageProblem", messageProblem);
        return "usluge";
    }

    @GetMapping("/usluge/nova-usluga")
    public String showUslugaCreationForm(Model model) {
        Usluga usluga = new Usluga();
        usluga.setAktivna(true);
        model.addAttribute("usluga", usluga);
        model.addAttribute("svevrsteusluga", uslugaService.findAllServiceTypes());
        return "nova-usluga";
    }

    @GetMapping("/usluge/{id}")
    public String showUslugaById(@PathVariable Long id, Model model) {
        model.addAttribute("usluga", uslugaService.findService(id));
        model.addAttribute("svevrsteusluga", uslugaService.findAllServiceTypes());
        return "izmeni-uslugu";
    }

    @PostMapping(value = "/usluge/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewUsluga(@Valid @ModelAttribute Usluga usluga, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "nova-usluga";
        }
        //uslugaService.saveService(usluga);
//        model.addAttribute("usluge", uslugaService.findAllServices());
        redirectAttributes.addFlashAttribute("messageProblem", "Usluga nije sacuvana!");
        return "redirect:/usluge";
    }

    @PostMapping("/usluge/{id}/update")
    public String updateUsluga(@PathVariable Long id, @Valid @ModelAttribute Usluga usluga, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "izmeni-uslugu";
        }
        uslugaService.updateService(id, usluga);
//        model.addAttribute("usluge", uslugaService.findAllServices());
        return "redirect:/usluge";
    }

    @PostMapping("/usluge/{id}/deactivate")
    public String deactivateUsluga(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        uslugaService.deactivateService(id);
        redirectAttributes.addFlashAttribute("messageOk", "Korisnik uspesno deaktiviran");
        return "redirect:/usluge";
    }

    @PostMapping("/usluge/{id}/activate")
    public String activateUsluga(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        uslugaService.activateService(id);
        model.addAttribute("usluge", uslugaService.findAllServices());
        redirectAttributes.addFlashAttribute("messageOk", "Korisnik uspesno aktiviran");
        return "redirect:/usluge";
    }
}
