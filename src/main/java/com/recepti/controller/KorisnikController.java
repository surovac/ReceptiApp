package com.recepti.controller;

import com.recepti.model.Korisnik;
import com.recepti.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller za registraciju, prijavu i odjavu
@Controller
public class KorisnikController {

    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    // Otvara registraciju
    @GetMapping("/registracija")
    public String registracija(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        return "registracija";
    }

    // Registruje korisnika
    @PostMapping("/registracija")
    public String registruj(@ModelAttribute Korisnik korisnik) {

        // Ne dozvoljava isto korisnicko ime dva puta
        if (korisnikService.pronadjiPoKorisnickomImenu(
                korisnik.getKorisnickoIme()) != null) {
            return "redirect:/registracija?greska";
        }

        korisnikService.registruj(korisnik);
        return "redirect:/prijava";
    }

    // Otvara prijavu
    @GetMapping("/prijava")
    public String prijava() {
        return "prijava";
    }

    // Proverava podatke i pamti prijavljenog korisnika
    @PostMapping("/prijava")
    public String prijavi(
            @RequestParam String korisnickoIme,
            @RequestParam String lozinka,
            HttpSession session,
            Model model) {

        if (korisnikService.proveriPrijavu(korisnickoIme, lozinka)) {

            Korisnik korisnik =
                    korisnikService.pronadjiPoKorisnickomImenu(korisnickoIme);

            // Cuvamo korisnika u sesiji
            session.setAttribute("korisnik", korisnik);

            return "redirect:/";
        }

        model.addAttribute(
                "greska",
                "Pogresno korisnicko ime ili lozinka."
        );

        return "prijava";
    }

    // Odjava korisnika
    @GetMapping("/odjava")
    public String odjava(HttpSession session) {

        // Brise podatke trenutne sesije
        session.invalidate();

        return "redirect:/prijava";
    }
}