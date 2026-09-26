package com.recepti.service;

import com.recepti.model.Recept;
import com.recepti.repository.ReceptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service sloj sadrzi poslovnu logiku aplikacije
@Service
public class ReceptService {

    // Repository koristimo za pristup bazi podataka
    private final ReceptRepository receptRepository;

    // Konstruktor preko kojeg Spring povezuje Repository sa Service slojem
    public ReceptService(ReceptRepository receptRepository) {
        this.receptRepository = receptRepository;
    }

    // Vraca sve recepte iz baze
    public List<Recept> pronadjiSve() {
        return receptRepository.findAll();
    }

    // Cuva novi recept ili izmene postojeceg recepta
    public Recept sacuvaj(Recept recept) {
        return receptRepository.save(recept);
    }

    // Pronalazi jedan recept na osnovu njegovog ID-a
    public Recept pronadjiPoId(Long id) {
        return receptRepository.findById(id).orElse(null);
    }

    // Brise recept iz baze na osnovu ID-a
    public void obrisi(Long id) {
        receptRepository.deleteById(id);
    }
}