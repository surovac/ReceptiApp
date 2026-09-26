package com.recepti.repository;

// Uvoz klase Recept
import com.recepti.model.Recept;

// JpaRepository nam daje gotove metode za rad sa bazom
import org.springframework.data.jpa.repository.JpaRepository;

// Repository sloj sluzi za komunikaciju sa bazom podataka
public interface ReceptRepository extends JpaRepository<Recept, Long> {

    // JpaRepository nam automatski omogucava metode kao sto su:
    // save()       - cuvanje recepta
    // findAll()    - prikaz svih recepata
    // findById()   - pronalazenje recepta po ID-u
    // deleteById() - brisanje recepta po ID-u
}