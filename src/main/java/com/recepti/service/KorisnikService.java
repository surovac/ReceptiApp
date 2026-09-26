package com.recepti.service;

import com.recepti.model.Korisnik;
import com.recepti.repository.KorisnikRepository;
import org.springframework.stereotype.Service;

// Service sloj za rad sa korisnicima
@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;

    // Povezivanje Service sloja sa Repository slojem
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    // Registracija novog korisnika
    public Korisnik registruj(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    // Pronalazenje korisnika po korisnickom imenu
    public Korisnik pronadjiPoKorisnickomImenu(String korisnickoIme) {
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }

    // Provera podataka prilikom prijave
    public boolean proveriPrijavu(String korisnickoIme, String lozinka) {

        Korisnik korisnik =
                korisnikRepository.findByKorisnickoIme(korisnickoIme);

        return korisnik != null &&
                korisnik.getLozinka().equals(lozinka);
    }
}