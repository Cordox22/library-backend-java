/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionebiblioteca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Archivio implements Iterable<Map.Entry<String, Libro>>{
    Scanner input = new Scanner(System.in);
    private Map<String, Libro> libri;
    private List<Libro> libriPrestati; 
    
    public Archivio(){
        this.libri = new HashMap<>();
        this.libriPrestati = new ArrayList();
    }
    
    public Map<String, Libro> getLibriArchivio(){
        return this.libri; 
    }
    
    public List<Libro> getLibriPrestati(){
        return this.libriPrestati;
    }
    
    public void inserimentoDaTastiera() throws DuplicateTitleAuthorException{
        String codice, titolo, autore;
        double prezzo;
        int numeroPagine, copie;
        
        System.out.print("\nInserisci il codice: ");
        codice = this.input.nextLine();
        System.out.print("Inserisci il titolo: ");
        titolo = this.input.nextLine();
        System.out.print("Inserisci l'autore: ");
        autore = this.input.nextLine();
        System.out.print("Inserisci il prezzo: ");
        prezzo = Double.parseDouble(this.input.nextLine());
        System.out.print("Inserisci il numero di pagine: ");
        numeroPagine = Integer.parseInt(this.input.nextLine());
        System.out.print("Inserisci il numero di copie: ");
        copie = Integer.parseInt(this.input.nextLine());
        
        this.inserimento(new Libro(codice, titolo, autore, prezzo, numeroPagine, copie));
        System.out.println("Libro inserito con successo!");
    }
    
    public void inserimento(Libro libro) throws DuplicateTitleAuthorException{
        for(Libro l : this.libri.values()){
            if((l.getAutore().equals(libro.getAutore())) && (!l.getCodice().equals(libro.getCodice()))
                    && (l.getTitolo().equals(libro.getTitolo()))){
                throw new DuplicateTitleAuthorException("Il libro " + l.getTitolo() + " Autore: " + l.getAutore() 
                + " esiste gia' con codice: " + l.getCodice());
            }
            if((l.getAutore().equals(libro.getAutore())) && (l.getCodice().equals(libro.getCodice()))
                    && (l.getTitolo().equals(libro.getTitolo()))){
                l.setCopie(l.getCopie() + 1);
                throw new DuplicateTitleAuthorException("Aggiunta copia al libro " + l.getTitolo() + " Autore: " + l.getAutore());
            }
        }
            
        this.libri.put(libro.getCodice(), libro);
    }
    
    public void eliminaLibro(){
        System.out.print("\nInserisci il codice del libro da eliminare dall'archivio: ");
        String codice = this.input.nextLine();
        
        if(this.libri.containsKey(codice)){
            this.libri.remove(codice);
            System.out.println("Libro rimosso con successo!");
            System.out.println("Salva il file per completare l'operazione");
        }
        else{
            System.out.println("Codice non trovato e/o sbagliato ");
        }
    }
    
    public void salvaLibriSuFile(String nomeFile) throws IOException{
        if(Files.exists(Paths.get("archivioLibriDisponibili.bak2"))){
            Files.delete(Paths.get("archivioLibriDisponibili.bak2"));
        }
        if(Files.exists(Paths.get("archivioLibriDisponibili.bak"))){
            Files.move(Paths.get("archivioLibriDisponibili.bak"), Paths.get("archivioLibriDisponibili.bak2"), StandardCopyOption.REPLACE_EXISTING);
        }
        if(Files.exists(Paths.get("archivioLibriDisponibili.csv"))){
            Files.move(Paths.get("archivioLibriDisponibili.csv"), Paths.get("archivioLibriDisponibili.back"), StandardCopyOption.REPLACE_EXISTING);
        }
        
        String titolo = "Codice ISBN;Titolo;Autore;Prezzo;Numero Pagine;Copie;";
        try(PrintWriter pw = new PrintWriter(new FileWriter(nomeFile))){
            pw.println(titolo);
            for(Libro l : this.libri.values()){
                pw.println(
                    l.getCodice() + ";" +
                    l.getTitolo() + ";" + 
                    l.getAutore() + ";" +
                    l.getPrezzo() + ";" + 
                    l.getNumeroPagine() + ";" +
                    l.getCopie()
                );
            }
            System.out.println("File salvato con successo!");
        }
        catch(IOException e){
            System.out.println("Errore durante il salvataggio" + e.getMessage());
        }
    }
    
    public void caricaLibriDaFile(String nomeFile) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(nomeFile))){
            String riga;
            while((riga = br.readLine()) != null){
                String[] campi = riga.split(";");
                String codice = campi[0];
                String titolo = campi[1];
                String autore = campi[2];
                double prezzo = Double.parseDouble(campi[3]);
                int pagine = Integer.parseInt(campi[4]);
                int copie = Integer.parseInt(campi[5]);
                try{
                    this.inserimento(new Libro(codice, titolo, autore, prezzo, pagine, copie));
                }
                catch(DuplicateTitleAuthorException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("File caricato con successo!");
    }
    
    public void inserimentoLibriPrestatiDaCodice() throws DuplicateTitleAuthorException{
        String nome = "", cognome = "", indirizzo = "";
        System.out.print("\nCodice del libro da inserire nell'archivio dei libri prestati: ");
        String codice = this.input.nextLine();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        
        if(this.libri.containsKey(codice)){
            Libro valori = this.libri.get(codice);
            if(valori.getCopie() == 0){
                System.out.println("Terminate le copie del libro: " + valori.getTitolo() + " codice: " + valori.getCodice());
                throw new DuplicateTitleAuthorException("Non e' possibile prestare questo libro");
            }
            try{
                System.out.print("Inserisci il nome: ");
                nome = this.input.nextLine();
                System.out.print("Inserisci il cognome: ");
                cognome = this.input.nextLine(); 
                System.out.print("Inserisci l'indirizzo: ");
                indirizzo = this.input.nextLine();
            }
            catch(IllegalArgumentException e){
                System.out.println("Inserire un campo valido " + e.getMessage());
            }
            
            //Copia del libro con aggiunta di data e ora 
            Libro copiaLibro = new Libro(codice, valori.getTitolo(), valori.getAutore(), nome, cognome, indirizzo, 1, timestamp);
            this.libriPrestati.add(copiaLibro);
            
            System.out.println("Libro aggiunto all'archivio prestiti con successo! " + "\nCopie in archivio: " +copiaLibro.getCopie());
            valori.setCopie(valori.getCopie() - 1);
        }
    }
    
    public void eliminaLibroPrestato(){
        boolean flag = true; 
        System.out.print("Codice del libro da eliminare dall'archivio dei libri prestati: ");
        String codice = this.input.nextLine();
        
        for(Libro l : this.libriPrestati){
            if(l.getCodice().equals(codice)){
                this.libriPrestati.remove(l);
                System.out.println("Libro rimosso con successo!");
                System.out.println("Salva il file per completare l'operazione");
                flag = false;
                l.setCopie(l.getCopie() + 1);
            }
        }
        if(flag){
            System.out.println("Libro non trovato, controllare il codice inserito " + codice);
        }
    }
    
    //Metodo per inserire i libri prestati 
    private void inserimentoLibriPrestati(Libro libro) throws DuplicateTitleAuthorException{
        
        for(Libro l : this.libriPrestati){
            if(l.getCodice().equals(libro.getCodice()) && (l.getAutore().equals(libro.getAutore())
                && (l.getTitolo().equals(libro.getTitolo()) && l.getNome().equals(libro.getNome())
                && (l.getCognome().equals(libro.getCognome()))))){
                throw new DuplicateTitleAuthorException("Libro gia' inserito");
            }
        }
        
        this.libriPrestati.add(libro);
    }
    
    //Salvataggio Libri Prestati
    public void salvaLibriPrestatisuFile(String nomeFile) throws IOException{
        if(Files.exists(Paths.get("archivioLibriPrestati.bak2"))){
            Files.delete(Paths.get("archivioLibriPrestati.bak2"));
        }
        if(Files.exists(Paths.get("archivioLibriPrestati.bak"))){
            Files.move(Paths.get("archivioLibriPrestati.bak"), Paths.get("archivioLibriPrestati.bak2"), StandardCopyOption.REPLACE_EXISTING);
        }
        if(Files.exists(Paths.get("archivioLibriPrestati.csv"))){
            Files.move(Paths.get("archivioLibriPrestati.csv"), Paths.get("archivioLibriPrestati.bak"), StandardCopyOption.REPLACE_EXISTING);
        }
        
        String titolo = "Codice ISBN;Titolo;Autore;Nome;Cognome;Indirizzo;Copie;Data e Ora";
        try(PrintWriter pw = new PrintWriter(new FileWriter(nomeFile))){
            pw.println(titolo);
            
            for(Libro l : this.libriPrestati){
                pw.println(
                    l.getCodice() + ";" +
                    l.getTitolo() + ";" + 
                    l.getAutore() + ";" +
                    l.getNome() + ";" + 
                    l.getCognome() + ";" +
                    l.getIndirizzo() + ";" + 
                    l.getCopie() + ";" + 
                    l.getDataOra()
                );
            }
            System.out.println("\nFile salvato con successo");
        }
        catch(IOException e){
            System.out.println("Errore durante il salvataggio " + e.getMessage());
        }
    }
    
    //Caricamento dei Libri Prestati
    public void caricaLibriPrestati(String codice) throws FileNotFoundException, IOException{
        
        try(BufferedReader br = new BufferedReader(new FileReader("archivioLibriPrestati.csv"))){
            String riga;
            while((riga = br.readLine()) != null){
                String[] campi = riga.split(";");
                if(campi[0].equals(codice)){
                    codice = campi[0];
                    String titolo = campi[1];
                    String autore = campi[2];
                    String nome = campi[3];
                    String cognome = campi[4];
                    String indirizzo = campi[5];
                    int copie = Integer.parseInt(campi[6]);
                    String dataOra = campi[7];
                    try{
                        this.inserimentoLibriPrestati(new Libro(codice, titolo, autore, nome, cognome, indirizzo, copie, dataOra));
                    }
                    catch(DuplicateTitleAuthorException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
    
    public void sceltastampaArchivio(Map<String, Libro> libri) throws DuplicateTitleAuthorException{
        int scelta; 
        List<Libro> lista; 
        System.out.println("Come vuoi stampare l'archivio? ");
        System.out.println("Premi 1 per stampare l'archivio senza ordinamento");
        System.out.println("Premi 2 per stampare l'archivio ordinato per numero di pagine");
        System.out.println("Premi 3 per stampare l'archivio ordinato per autore");
        scelta = Integer.parseInt(this.input.nextLine());
        
        if((scelta < 1)||(scelta > 3)){
            throw new DuplicateTitleAuthorException("Scelta non valida. Inserire un numero valido");
        }
                
        switch(scelta){
            case 1 -> this.stampaMap(libri);
            case 2 -> { 
                lista = this.getLibriOrdinati((Comparator<Libro>) new OrdinamentoNumeroPagine(), this.libri);
                this.stampaLista(lista);
            }
            case 3 -> { 
                lista = this.getLibriOrdinati((Comparator<Libro>) new OrdinamentoPerAutore(), this.libri);
                this.stampaLista(lista);
            } 
        }
    }
    
    public void sceltaStampaArchivioPrestiti() throws DuplicateTitleAuthorException{
        int scelta; 
        System.out.println("Come vuoi stampare l'archivio? ");
        System.out.println("Premi 1 per stampare l'archivio senza ordinamento");
        System.out.println("Premi 2 per stampare l'archivio ordinato per numero di pagine");
        System.out.println("Premi 3 per stampare l'archivio ordinato per autore");
        scelta = Integer.parseInt(this.input.nextLine());
        
        if((scelta < 1)||(scelta > 3)){
            throw new DuplicateTitleAuthorException("Scelta non valida. Inserire un numero valido");
        }
                
        switch(scelta){
            case 1 -> this.stampaLista(this.libriPrestati);
            case 2 -> { 
                this.getLibriOrdinati((Comparator<Libro>) new OrdinamentoNumeroPagine());
                this.stampaLista(this.libriPrestati);
            }
            case 3 -> { 
                this.getLibriOrdinati((Comparator<Libro>) new OrdinamentoPerAutore());
                this.stampaLista(this.libriPrestati);
            } 
        }
    }
    
    private void getLibriOrdinati(Comparator<Libro> comp){
        this.libriPrestati.sort(comp);
    }
    
    private List<Libro> getLibriOrdinati(Comparator<Libro> comp, Map<String, Libro> l){
        List<Libro> lista = new ArrayList<>(l.values());
        lista.sort(comp);
        
        return lista;
    }
    
    private void stampaLista(List<Libro> lista){
        for(Libro l : lista){
            System.out.println(l);
        }
    }
    
    private void stampaMap(Map<String, Libro> m){
        for(Map.Entry<String, Libro> entry : m.entrySet()){
            String chiave = entry.getKey();
            Libro l = entry.getValue(); 
            System.out.println("Codice " + chiave + " Titolo " + l.getTitolo() + " Autore " + l.getAutore() 
                + " Prezzo " + l.getPrezzo() + "euro  Pagine " + l.getNumeroPagine() + " Copie " + l.getCopie());
        }
    }
    
    @Override
    public Iterator<Map.Entry<String, Libro>> iterator(){
        return new IteratoreLibro(this.libri);
    }
}