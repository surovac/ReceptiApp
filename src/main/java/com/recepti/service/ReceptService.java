package com.recepti.service;

import com.recepti.model.Korisnik;
import com.recepti.model.Recept;
import com.recepti.repository.ReceptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service sloj sadrzi poslovnu logiku za recepte
@Service
public class ReceptService {

    private final ReceptRepository receptRepository;

    public ReceptService(ReceptRepository receptRepository) {
        this.receptRepository = receptRepository;
    }

    // Vraca recepte prijavljenog korisnika
    public List<Recept> pronadjiSve(Korisnik korisnik) {
        return receptRepository.findByKorisnik(korisnik);
    }

    // Pretraga recepata prijavljenog korisnika
    public List<Recept> pretraziPoNazivu(Korisnik korisnik, String naziv) {
        return receptRepository
                .findByKorisnikAndNazivContainingIgnoreCase(korisnik, naziv);
    }

    // Cuvanje recepta
    public Recept sacuvaj(Recept recept) {
        return receptRepository.save(recept);
    }

    // Pronalazenje recepta
    public Recept pronadjiPoId(Long id) {
        return receptRepository.findById(id).orElse(null);
    }

    // Brisanje recepta
    public void obrisi(Long id) {
        receptRepository.deleteById(id);
    }
}