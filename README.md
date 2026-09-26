# ReceptiApp

ReceptiApp je web aplikacija za upravljanje ličnim receptima.

Aplikacija omogućava korisnicima da se registruju i prijave, nakon čega mogu da dodaju, pregledaju, menjaju, brišu i pretražuju svoje recepte.

Svaki korisnik ima svoje recepte i ne može da vidi recepte drugih korisnika.

## Funkcionalnosti

- Registracija korisnika
- Prijava korisnika
- Odjava korisnika
- Dodavanje recepta
- Izmena recepta
- Brisanje recepta
- Prikaz recepata
- Pretraga recepata po nazivu
- Odvojeni recepti za svakog korisnika
- Čuvanje podataka u bazi podataka

## Tehnologije

Za izradu aplikacije korišćeni su:

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- H2 baza podataka
- HTML
- CSS
- Maven

## Arhitektura aplikacije

Aplikacija je organizovana kroz više slojeva:

- Model - predstavlja podatke aplikacije
- Repository - omogućava komunikaciju sa bazom podataka
- Service - sadrži poslovnu logiku
- Controller - obrađuje korisničke zahteve i povezuje korisnički interfejs sa ostatkom aplikacije

## Pokretanje aplikacije

1. Otvoriti projekat u IntelliJ IDEA.
2. Pokrenuti klasu `ReceptiAppApplication`.
3. Otvoriti internet pregledač.
4. Pristupiti aplikaciji preko `localhost:8080`.
5. Registrovati korisnički nalog i prijaviti se.

## Autor

Boris Surovac

## Licenca

Projekat je objavljen pod MIT licencom.