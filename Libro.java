/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionebiblioteca;

/**
 *
 * @author User
 */
public class Libro implements Comparable<Libro>{
    private final String codice;
    private final String titolo;
    private final String autore;
    private double prezzo;
    private int numeroPagine;
    private int copie;
    private String dataOra;
    private String nome; 
    private String cognome; 
    private String indirizzo; 

    // Costruttore
    public Libro(String codice, String titolo, String autore, double prezzo, int numeroPagine, int copie) {
        this.codice = codice;
        this.titolo = titolo;
        this.autore = autore;
        this.prezzo = prezzo;
        this.numeroPagine = numeroPagine;
        this.copie = copie;
    }
    
    //Costruttore arrayList libri prestati
    public Libro(String codice, String titolo, String autore, String nome, String cognome, String indirizzo, int copie, String dataOra){
        this.codice = codice; 
        this.titolo = titolo; 
        this.autore = autore; 
        this.nome = nome; 
        this.cognome = cognome; 
        this.indirizzo = indirizzo; 
        this.copie = copie;
        this.dataOra = dataOra; 
    }
    
    public String getNome(){
        return this.nome; 
    }
    
    public String getCognome(){
        return this.cognome; 
    }
    
    public String getIndirizzo(){
        return this.indirizzo; 
    } 
    
    public String getDataOra(){
        return this.dataOra;
    }
    
    public String getCodice(){
        return this.codice;
    }
    
    public String getAutore(){
        return this.autore;
    }
    
    public int getNumeroPagine(){
        return this.numeroPagine;
    }
    
    public String getTitolo(){
        return this.titolo;
    }
    
    public void setCopie(int numero){
        this.copie = numero;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getCopie() {
        return copie;
    }
    
    @Override
    public int compareTo(Libro altro){
        return this.titolo.compareTo(altro.titolo);
    }
    
    // Metodo per stampare le informazioni del libro
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codice ISBN: ").append(this.codice);
        sb.append("Titolo: ").append(this.titolo);
        sb.append(", Autore: ").append(this.autore);
        sb.append(", Prezzo: ").append(this.prezzo);
        sb.append("Euro, Pagine: ").append(this.numeroPagine);
        sb.append(" , numero copie ").append(this.copie);
        
        return sb.toString();
    }
}