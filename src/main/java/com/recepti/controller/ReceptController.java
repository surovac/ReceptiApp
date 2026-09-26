package com.recepti.controller;

import com.recepti.model.Recept;
import com.recepti.service.ReceptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


// Controller predstavlja prezentacioni sloj aplikacije
// Prima zahteve korisnika i povezuje stranice sa Service slojem
@Controller
public class ReceptController {

    private final ReceptService receptService;

    // Povezivanje Controller sloja sa Service slojem
    public ReceptController(ReceptService receptService) {
        this.receptService = receptService;
    }

    // Pocetna stranica - prikazuje sve recepte
    @GetMapping("/")
    public String pocetna(Model model) {
        model.addAttribute("recepti", receptService.pronadjiSve());
        return "index";
    }

    // Otvara formu za dodavanje novog recepta
    @GetMapping("/novi")
    public String noviRecept(Model model) {
        model.addAttribute("recept", new Recept());
        return "forma";
    }

    // Cuva recept koji je korisnik uneo u formu
    @PostMapping("/sacuvaj")
    public String sacuvajRecept(@ModelAttribute Recept recept) {
        receptService.sacuvaj(recept);
        return "redirect:/";
    }

    // Otvara formu za izmenu postojeceg recepta
    @GetMapping("/izmeni/{id}")
    public String izmeniRecept(@PathVariable Long id, Model model) {
        model.addAttribute("recept", receptService.pronadjiPoId(id));
        return "forma";
    }

    // Brise recept iz baze
    @GetMapping("/obrisi/{id}")
    public String obrisiRecept(@PathVariable Long id) {
        receptService.obrisi(id);
        return "redirect:/";
    }
}