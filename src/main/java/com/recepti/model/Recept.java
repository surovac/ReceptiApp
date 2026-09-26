package com.recepti.model;

import jakarta.persistence.*;

// Klasa predstavlja recept u bazi podataka
@Entity
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String kategorija;
    private String sastojci;

    @Column(length = 2000)
    private String priprema;

    // Vise recepata moze pripadati jednom korisniku
    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

    public Recept() {
    }

    public Recept(String naziv, String kategorija,
                  String sastojci, String priprema) {
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.sastojci = sastojci;
        this.priprema = priprema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public String getPriprema() {
        return priprema;
    }

    public void setPriprema(String priprema) {
        this.priprema = priprema;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}