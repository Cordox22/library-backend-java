/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionebiblioteca;

import java.util.Iterator;
import java.util.Map;

/**
 *Un iteratore è un oggetto che:
    tiene traccia di dove sei in una collezione
    ti dice se ci sono altri elementi
    ti dà l’elemento successivo
 *  nella fattispecie Map.Entry<String, Libro>
 * @author User
 */
public class IteratoreLibro implements Iterator<Map.Entry<String, Libro>>{
    private final Iterator<Map.Entry<String, Libro>> iteratore;
    
   public IteratoreLibro(Map<String, Libro> libri){
        this.iteratore = libri.entrySet().iterator();
        ////libri.entrySet()
        //Trasforma la mappa in un insieme di coppie:
        //Ogni entry contiene:
        //getKey() → la chiave (String)
        //getValue() → il libro (Libro)
        //.iterator() invece
        //Crea un iteratore su quell’insieme.
        //Quindi il tuo iteratore interno è:
        //[entry1] → [entry2] → [entry3] → ... → [entryN]
    }
   
    @Override
    public boolean hasNext(){
        return this.iteratore.hasNext();
    }
    
    @Override 
    public Map.Entry<String, Libro> next(){
        return this.iteratore.next();
    }
}