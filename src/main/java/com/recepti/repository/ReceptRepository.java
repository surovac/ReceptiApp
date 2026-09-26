package com.recepti.repository;

import com.recepti.model.Korisnik;
import com.recepti.model.Recept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository sloj za rad sa receptima u bazi
public interface ReceptRepository extends JpaRepository<Recept, Long> {

    // Vraca samo recepte odredjenog korisnika
    List<Recept> findByKorisnik(Korisnik korisnik);

    // Pretrazuje po nazivu, ali samo recepte prijavljenog korisnika
    List<Recept> findByKorisnikAndNazivContainingIgnoreCase(
            Korisnik korisnik,
            String naziv
    );
}