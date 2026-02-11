/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionebiblioteca;

/**
 *
 * @author User
 */
public class OrdinamentoPerAutore implements Comparable<Libro>{
    private String autore; 
    
    @Override
    public int compareTo(Libro altro){
        return this.autore.compareTo(altro.getAutore());
    }
}