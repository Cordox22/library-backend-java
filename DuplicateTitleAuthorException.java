/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.gestionebiblioteca;

/**
 *
 * @author User
 */
public class DuplicateTitleAuthorException extends Exception {

    /**
     * Creates a new instance of <code>DuplicateTitleAuthorException</code>
     * without detail message.
     */
    public DuplicateTitleAuthorException() {
    }

    /**
     * Constructs an instance of <code>DuplicateTitleAuthorException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateTitleAuthorException(String msg) {
        super(msg);
    }
}
