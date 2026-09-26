package com.recepti.model;

import jakarta.persistence.*;

// Ova klasa predstavlja korisnika aplikacije
@Entity
public class Korisnik {

    // Jedinstveni ID korisnika
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Korisnicko ime
    private String korisnickoIme;

    // Lozinka korisnika
    private String lozinka;

    // Email korisnika
    private String email;

    // Prazan konstruktor potreban za JPA
    public Korisnik() {
    }

    // Getteri i setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}