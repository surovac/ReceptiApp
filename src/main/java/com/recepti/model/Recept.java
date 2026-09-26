package com.recepti.model;

// Uvoz anotacija koje koristimo za rad sa bazom podataka
import jakarta.persistence.*;

// Oznacava da ova klasa predstavlja tabelu u bazi podataka
@Entity
public class Recept {

    // Primarni kljuc tabele
    @Id
    // ID se automatski generise za svaki novi recept
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Osnovni podaci o receptu
    private String naziv;
    private String kategorija;
    private String sastojci;

    // Dozvoljavamo duzi tekst za opis pripreme
    @Column(length = 2000)
    private String priprema;

    // Prazan konstruktor potreban za rad JPA
    public Recept() {
    }

    // Konstruktor za pravljenje novog recepta
    public Recept(String naziv, String kategorija,
                  String sastojci, String priprema) {
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.sastojci = sastojci;
        this.priprema = priprema;
    }

    // Getter i setter za ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter i setter za naziv
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    // Getter i setter za kategoriju
    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    // Getter i setter za sastojke
    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    // Getter i setter za pripremu
    public String getPriprema() {
        return priprema;
    }

    public void setPriprema(String priprema) {
        this.priprema = priprema;
    }
}