package com.recepti.repository;

import com.recepti.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository sloj za rad sa korisnicima u bazi podataka
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    // Pronalazi korisnika na osnovu korisnickog imena
    Korisnik findByKorisnickoIme(String korisnickoIme);
}