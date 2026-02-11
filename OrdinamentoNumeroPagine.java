/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionebiblioteca;

/**
 *
 * @author User
 */
public class OrdinamentoNumeroPagine implements Comparable<Libro>{
    private int numeroPagine;
    
    @Override
    public int compareTo(Libro altro){
        return Integer.compare(this.numeroPagine, altro.getNumeroPagine());
    }
}
