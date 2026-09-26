package com.recepti.controller;

import com.recepti.model.Korisnik;
import com.recepti.model.Recept;
import com.recepti.service.ReceptService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReceptController {

    private final ReceptService receptService;

    public ReceptController(ReceptService receptService) {
        this.receptService = receptService;
    }

    // Pocetna stranica
    @GetMapping("/")
    public String pocetna(HttpSession session) {

        // Proveravamo da li je korisnik prijavljen
        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        // Pocetna vise ne prikazuje recepte
        return "index";
    }

    // Prikazuje sve recepte prijavljenog korisnika
    @GetMapping("/recepti")
    public String prikaziSve(
            HttpSession session,
            Model model) {

        Korisnik korisnik =
                (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return "redirect:/prijava";
        }

        // Ucitavamo samo recepte prijavljenog korisnika
        model.addAttribute(
                "recepti",
                receptService.pronadjiSve(korisnik)
        );

        return "recepti";
    }

    // Otvara formu za dodavanje novog recepta
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

        // Recept povezujemo sa trenutno prijavljenim korisnikom
        recept.setKorisnik(korisnik);

        receptService.sacuvaj(recept);

        // Nakon cuvanja prikazujemo sve recepte
        return "redirect:/recepti";
    }

    // Pretraga recepata po nazivu
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

        // Ako je pretraga prazna, prikazujemo sve recepte
        if (naziv == null || naziv.trim().isEmpty()) {

            model.addAttribute(
                    "recepti",
                    receptService.pronadjiSve(korisnik)
            );

        } else {

            // Pretrazujemo samo recepte prijavljenog korisnika
            model.addAttribute(
                    "recepti",
                    receptService.pretraziPoNazivu(korisnik, naziv)
            );
        }

        model.addAttribute("nazivPretrage", naziv);

        return "recepti";
    }

    // Otvara formu za izmenu postojeceg recepta
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

            return "redirect:/recepti";
        }

        model.addAttribute("recept", recept);

        return "forma";
    }

    // Brise recept
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

        return "redirect:/recepti";
    }
}