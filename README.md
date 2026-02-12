# library-backend-java
Applicazione Java per la gestione di un archivio librario: inserimento, rimozione, ordinamento, prestiti, restituzioni e salvataggio su file. Include validazioni, gestione delle copie, tracciamento della data delle operazioni e architettura modulare orientata agli oggetti.

# Gestione Biblioteca — applicazione console Java

![Build](https://github.com/Cordox22/library-backend-java/actions/workflows/maven.yml/badge.svg)
![License](https://img.shields.io/badge/license-MIT-blue)
![Java](https://img.shields.io/badge/Java-17%2B-orange)

**Applicazione console Java per la gestione di un catalogo libri e dei prestiti.**  
Persistenza **file‑based** in formato **CSV** con **due file di backup** per ogni archivio; salvataggi **atomici**, validazione completa dei campi e gestione delle eccezioni per ogni operazione. Architettura modulare progettata per una futura migrazione verso persistenza su database relazionale (H2 / MySQL).

---

## Caratteristiche principali

- **Persistenza ridondante:** file principale CSV + 2 backup sovrascrivibili per ogni archivio; salvataggi atomici tramite file temporanei e rename.  
- **Popolamento iniziale automatico:** il `main` della classe **GestioneBiblioteca** esegue un seed che inserisce **20 libri** nel catalogo e li salva nel file di archivio, consentendo l’avvio immediato delle operazioni di prestito.  
- **Operazioni CRUD:** inserimento libro da console, rimozione per ISBN, registrazione e restituzione prestiti.  
- **Strutture dati ottimizzate:** `HashMap<String, Libro>` per il catalogo (lookup O(1)); `ArrayList<Prestito>` per i prestiti (consente duplicati).  
- **Ordinamento flessibile:** `Comparable` per l’ordinamento naturale (numero di pagine) e `Comparator` per ordinamenti alternativi (autore, titolo).  
- **Iterazione sicura:** API che espongono `Iterable` e uso di `Iterator` per rimozioni durante la traversata evitando `ConcurrentModificationException`.  
- **Validazione e gestione eccezioni:** controlli per ogni campo (formato ISBN, pagine > 0, copie non negative, campi anagrafici non vuoti); eccezioni dedicate per input non valido, errori I/O e condizioni anomale; strategie di rollback e ripristino da backup.  
- **Timestamp leggibile:** uso di `java.time` per data/ora nei record di prestito.  
- **Progettato per migrazione a DB:** livello di persistenza astratto (Repository/DAO) per sostituire facilmente l’implementazione file‑based con JDBC/Hibernate.

---

## Dettagli tecnici

**Persistenza e I/O**  
- Lettura/scrittura CSV con gestione atomica: scrittura su file temporaneo e rename finale.  
- Backup automatici: due file di backup sovrascrivibili per ogni archivio; in caso di errore si tenta il ripristino dal backup più recente.  

**Modelli e collezioni**  
- **Libro** — campi: `isbn`, `titolo`, `autore`, `pagine`, `copie`. Implementa `Comparable<Libro>` (ordinamento naturale per numero di pagine).  
- **Prestito** — campi: `isbn`, `autore`, `titolo`, `copie`, `nome`, `cognome`, `indirizzo`, `timestamp`.  
- **Persona** — campi: `idTessera` (UUID o identificatore univoco), `nome`, `cognome`, `dataNascita`, `indirizzo`, `email`, `telefono` (opzionale). `equals()` e `hashCode()` basati su `idTessera`.  
- Catalogo: `HashMap<String, Libro>`; Prestiti: `ArrayList<Prestito>`.  

**Ordinamento e iterazione**  
- `Comparable` definisce l’ordinamento naturale; `Comparator` per ordinamenti alternativi (autore, titolo, data prestito).  
- API espongono `Iterable` e utilizzano `Iterator.remove()` per eliminazioni sicure durante la traversata.

**Gestione eccezioni**  
- Eccezioni dedicate: `InvalidFieldException`, `PersistenceException`, `BackupException`.  
- Strategie di recovery: rollback locale, ripristino da backup, messaggi utente chiari e logging.

---

## Esempi CSV

**Archivio libri (CSV)** — formato: `ISBN,Titolo,Autore,Pagine,Copie`  
```csv
9781234567897,Il titolo dell'opera,Rossi Mario,320,3
9789876543210,Programmazione Java,Cordone Simone,240,5
```
Archivio prestiti (CSV) — formato: ISBN,Autore,Titolo,Copie,Nome,Cognome,Indirizzo,Timestamp

```csv
9781234567897,Rossi Mario,Il titolo dell'opera,1,Giulia,Rossi,Via Roma 1,2026-02-11T21:34:00
