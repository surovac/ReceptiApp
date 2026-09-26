package com.recepti.controller;

import com.recepti.model.Korisnik;
import com.recepti.model.Recept;
import com.recepti.service.ReceptService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller predstavlja prezentacioni sloj za recepte
@Controller
public class ReceptController {

    private final ReceptService receptService;

    public ReceptController(ReceptService receptService) {
        this.receptService = receptService;
    }

    // Pocetna stranica
    @GetMapping("/")
    public String pocetna(HttpSession session, Model model) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        // Ako korisnik nije prijavljen, saljemo ga na prijavu
        if (korisnik == null) {
            return "redirect:/prijava";
        }

        // Prikazujemo samo recepte prijavljenog korisnika
        model.addAttribute(
                "recepti",
                receptService.pronadjiSve(korisnik)
        );

        model.addAttribute("korisnik", korisnik);

        return "index";
    }

    // Pretraga recepata
    @GetMapping("/pretraga")
    public String pretraga(
            @RequestParam String naziv,
            HttpSession session,
            Model model) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        if (naziv == null || naziv.trim().isEmpty()) {

            model.addAttribute(
                    "recepti",
                    receptService.pronadjiSve(korisnik)
            );

        } else {

            model.addAttribute(
                    "recepti",
                    receptService.pretraziPoNazivu(korisnik, naziv)
            );
        }

        model.addAttribute("nazivPretrage", naziv);
        model.addAttribute("korisnik", korisnik);

        return "index";
    }

    // Otvara formu za novi recept
    @GetMapping("/novi")
    public String noviRecept(
            HttpSession session,
            Model model) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        model.addAttribute("recept", new Recept());

        return "forma";
    }

    // Cuva novi ili izmenjeni recept
    @PostMapping("/sacuvaj")
    public String sacuvajRecept(
            @ModelAttribute Recept recept,
            HttpSession session) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        // Recept pripada trenutno prijavljenom korisniku
        recept.setKorisnik(korisnik);

        receptService.sacuvaj(recept);

        return "redirect:/";
    }

    // Otvara formu za izmenu recepta
    @GetMapping("/izmeni/{id}")
    public String izmeniRecept(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        Recept recept = receptService.pronadjiPoId(id);

        // Korisnik moze menjati samo svoj recept
        if (recept == null ||
                recept.getKorisnik() == null ||
                !recept.getKorisnik().getId().equals(korisnik.getId())) {

            return "redirect:/";
        }

        model.addAttribute("recept", recept);

        return "forma";
    }

    // Brisanje recepta
    @GetMapping("/obrisi/{id}")
    public String obrisiRecept(
            @PathVariable Long id,
            HttpSession session) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        Recept recept = receptService.pronadjiPoId(id);

        // Korisnik moze obrisati samo svoj recept
        if (recept != null &&
                recept.getKorisnik() != null &&
                recept.getKorisnik().getId().equals(korisnik.getId())) {

            receptService.obrisi(id);
        }

        return "redirect:/";
    }
}