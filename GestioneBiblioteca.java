/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionebiblioteca;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class GestioneBiblioteca{

    public static void main(String[] args) throws DuplicateTitleAuthorException, IOException {
        Scanner input = new Scanner(System.in);
        Map<String, Libro> archivioLibri;
        int scelta;
        boolean flag;
        Archivio archivio = new Archivio(); 
        
        // 20 libri finti
        try{
            archivio.inserimento(new Libro("L001", "Il nome della rosa", "Umberto Eco", 15.90, 512, 1));
            archivio.inserimento(new Libro("L002", "I promessi sposi", "Alessandro Manzoni", 12.50, 720, 1));
            archivio.inserimento(new Libro("L003", "Divina Commedia", "Dante Alighieri", 18.00, 432, 1));
            archivio.inserimento(new Libro("L004", "La chiamata dei tre", "Stephen King", 19.00, 357, 2));
            archivio.inserimento(new Libro("L005", "Shining", "Stephen King", 17.50, 450, 3));
            archivio.inserimento(new Libro("L006", "1984", "George Orwell", 14.00, 328, 1));
            archivio.inserimento(new Libro("L007", "Il signore degli anelli", "J.R.R. Tolkien", 22.00, 1216, 2));
            archivio.inserimento(new Libro("L008", "Lo Hobbit", "J.R.R. Tolkien", 13.50, 310, 1));
            archivio.inserimento(new Libro("L009", "Fahrenheit 451", "Ray Bradbury", 11.00, 249, 1));
            archivio.inserimento(new Libro("L010", "Il piccolo principe", "Antoine de Saint-Exupery", 10.00, 120, 4));
            archivio.inserimento(new Libro("L011", "Orgoglio e pregiudizio", "Jane Austen", 12.00, 279, 1));
            archivio.inserimento(new Libro("L012", "Dracula", "Bram Stoker", 16.00, 488, 1));
            archivio.inserimento(new Libro("L013", "Frankenstein", "Mary Shelley", 14.50, 280, 1));
            archivio.inserimento(new Libro("L014", "Moby Dick", "Herman Melville", 18.50, 635, 1));
            archivio.inserimento(new Libro("L015", "Il vecchio e il mare", "Ernest Hemingway", 9.50, 127, 2));
            archivio.inserimento(new Libro("L016", "Il processo", "Franz Kafka", 13.00, 304, 1));
            archivio.inserimento(new Libro("L017", "Delitto e castigo", "Fedor Dostoevskij", 17.00, 671, 1));
            archivio.inserimento(new Libro("L018", "Guerra e pace", "Lev Tolstoj", 24.00, 1225, 1));
            archivio.inserimento(new Libro("L019", "Il gattopardo", "Giuseppe Tomasi di Lampedusa", 12.90, 230, 1));
            archivio.inserimento(new Libro("L020", "Siddharta", "Hermann Hesse", 10.50, 152, 3));
            archivio.inserimento(new Libro("L021", "Neuromante", "William Gibson", 14.90, 320, 1));
            archivio.inserimento(new Libro("L022", "Il barone rampante", "Italo Calvino", 13.90, 256, 1));
            archivio.inserimento(new Libro("L023", "La coscienza di Zeno", "Italo Svevo", 14.50, 412, 1));
            archivio.inserimento(new Libro("L024", "Il deserto dei Tartari", "Dino Buzzati", 12.00, 320, 1));
            archivio.inserimento(new Libro("L025", "Il fu Mattia Pascal", "Luigi Pirandello", 11.50, 304, 1));
            archivio.inserimento(new Libro("L026", "La metamorfosi", "Franz Kafka", 9.90, 112, 2));
            archivio.inserimento(new Libro("L027", "Il ritratto di Dorian Gray", "Oscar Wilde", 10.90, 224, 1));
            archivio.inserimento(new Libro("L028", "Il maestro e Margherita", "Michail Bulgakov", 16.50, 480, 1));
            archivio.inserimento(new Libro("L029", "Sulla strada", "Jack Kerouac", 14.00, 320, 1));
            archivio.inserimento(new Libro("L030", "Il giovane Holden", "J.D. Salinger", 12.50, 277, 1));
            archivio.inserimento(new Libro("L031", "Cronache marziane", "Ray Bradbury", 13.00, 256, 1));
            archivio.inserimento(new Libro("L032", "Il mondo nuovo", "Aldous Huxley", 14.20, 288, 1));
            archivio.inserimento(new Libro("L033", "La fattoria degli animali", "George Orwell", 9.50, 144, 3));
            archivio.inserimento(new Libro("L034", "Il conte di Montecristo", "Alexandre Dumas", 21.00, 1312, 1));
            archivio.inserimento(new Libro("L035", "Notre-Dame de Paris", "Victor Hugo", 18.90, 940, 1));
            archivio.inserimento(new Libro("L036", "Il visconte dimezzato", "Italo Calvino", 10.50, 144, 2));
            archivio.inserimento(new Libro("L037", "Il pendolo di Foucault", "Umberto Eco", 19.90, 656, 1));
            archivio.inserimento(new Libro("L038", "Il castello", "Franz Kafka", 14.00, 352, 1));
            archivio.inserimento(new Libro("L039", "Il lupo della steppa", "Hermann Hesse", 12.90, 256, 1));
            archivio.inserimento(new Libro("L040", "Il giardino segreto", "Frances Hodgson Burnett", 11.00, 288, 2));
            archivio.inserimento(new Libro("L041", "Il richiamo della foresta", "Jack London", 9.90, 192, 3));
            archivio.inserimento(new Libro("L042", "Il nome della rosa nera", "Umberto Eco", 16.50, 540, 1));
            archivio.inserimento(new Libro("L043", "La luna e i falo", "Cesare Pavese", 12.00, 224, 1));
            archivio.inserimento(new Libro("L044", "Se questo e' un uomo", "Primo Levi", 11.90, 208, 2));
            archivio.inserimento(new Libro("L045", "Il giorno della civetta", "Leonardo Sciascia", 10.50, 160, 1));
            archivio.inserimento(new Libro("L046", "Il giardino dei Finzi-Contini", "Giorgio Bassani", 13.00, 304, 1));
            archivio.inserimento(new Libro("L047", "La tregua", "Primo Levi", 12.50, 300, 1));
            archivio.inserimento(new Libro("L048", "Il dottor Zivago", "Boris Pasternak", 17.90, 592, 1));
            archivio.inserimento(new Libro("L049", "La svastica sul sole", "Philip K. Dick", 14.50, 320, 1));
            archivio.inserimento(new Libro("L050", "Ubik", "Philip K. Dick", 13.90, 240, 1));
            archivio.inserimento(new Libro("L051", "Il colore della magia", "Terry Pratchett", 12.50, 288, 2));
            archivio.inserimento(new Libro("L052", "American Gods", "Neil Gaiman", 18.50, 624, 1));
            archivio.inserimento(new Libro("L053", "Coraline", "Neil Gaiman", 10.00, 192, 3));
            archivio.inserimento(new Libro("L054", "La strada", "Cormac McCarthy", 14.00, 256, 1));
            archivio.inserimento(new Libro("L055", "Non lasciarmi", "Kazuo Ishiguro", 13.50, 304, 1));
            archivio.inserimento(new Libro("L056", "Il cacciatore di aquiloni", "Khaled Hosseini", 15.00, 371, 1));
            archivio.inserimento(new Libro("L057", "Mille splendidi soli", "Khaled Hosseini", 16.00, 432, 1));
            archivio.inserimento(new Libro("L058", "La ragazza del treno", "Paula Hawkins", 14.90, 400, 2));
            archivio.inserimento(new Libro("L059", "L'ombra del vento", "Carlos Ruiz Zafon", 17.50, 576, 1));
            archivio.inserimento(new Libro("L060", "Il gioco dell'angelo", "Carlos Ruiz Zafon", 18.00, 672, 1));
            archivio.inserimento(new Libro("L061", "La cattedrale del mare", "Ildefonso Falcones", 19.00, 672, 1));
        }
        catch(DuplicateTitleAuthorException e){
            System.out.println(e.getMessage());
        }
       
        do{
            flag = true;
            System.out.println("\nPremi 1 per caricare il file archivio libri da disco");
            System.out.println("Premi 2 per salvare il file archivio libri sul disco");
            System.out.println("Premi 3 per inserire un nuovo libro da tastiera");
            System.out.println("Premi 4 per eliminare un libro");
            System.out.println("Premi 5 per caricare il file dei libri prestati da disco");
            System.out.println("Premi 6 per salvare il file dei libri prestati su disco");
            System.out.println("Premi 7 per inserire un libro prestato da codice");
            System.out.println("Premi 8 per eliminare un libro prestato");
            System.out.println("Premi 9 per stampare l'archivio");
            System.out.println("Premi 10 per stampare l'archivio dei libri prestati ");
            System.out.println("Premi 0 per uscire");
            scelta = Integer.parseInt(input.nextLine());
            
            switch(scelta){
                case 1 -> {
                    //Caricamento da file
                    try{
                        archivio.caricaLibriDaFile("archivioLibriDisponibili.csv");
                    }
                    catch(IOException e){
                        System.out.println("Nessun file trovato " + e.getMessage());
                    }
                }
                case 2 -> { 
                    //Salvataggio su file
                    try{
                        archivio.salvaLibriSuFile("archivioLibriDisponibili.csv");
                    }
                    catch(IOException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try{
                        archivio.inserimentoDaTastiera();
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Valore/i errato/i " + e.getMessage());
                    }
                }
                case 4 -> archivio.eliminaLibro();
                case 5 -> { 
                    //Caricamento libri prestati
                    try{
                        archivio.caricaLibriPrestati("libriPrestati.csv");
                    }
                    catch(IOException e){ 
                        System.out.println("Nessun file trovato " + e.getMessage());
                    }
                }
                case 6 -> //Salvataggio libri prestati 
                    archivio.salvaLibriPrestatisuFile("libriPrestati.csv");
                case 7 -> {
                    try{
                        archivio.inserimentoLibriPrestatiDaCodice();
                    }
                    catch(DuplicateTitleAuthorException | IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 8 -> archivio.eliminaLibroPrestato();
                case 9 -> { 
                    try{
                        archivioLibri = archivio.getLibriArchivio();
                        archivio.sceltastampaArchivio(archivioLibri);
                    }
                    catch(DuplicateTitleAuthorException e){
                        System.out.println("Scelta non valida " + e.getMessage());
                    }
                }
                case 10 -> { 
                    try{
                        archivio.sceltaStampaArchivioPrestiti();
                    }
                    catch(DuplicateTitleAuthorException e){
                        System.out.println("Scelta non valida " + e.getMessage());
                    }
                }
                case 0 -> flag = false;
                default -> System.out.println("Scelta non valida");
            }
        }
        while(flag);
    }
}