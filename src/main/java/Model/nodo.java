/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author macbookpro
 */
public class nodo {
    int id;
    nodo sig;
    nodo ant;

    public nodo() {
    }

    public nodo(int id) {
        this.id = id;
    }

    public nodo(int id, nodo sig, nodo ant) {
        this.id = id;
        this.sig = sig;
        this.ant = ant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public nodo getSig() {
        return sig;
    }

    public void setSig(nodo sig) {
        this.sig = sig;
    }

    public nodo getAnt() {
        return ant;
    }

    public void setAnt(nodo ant) {
        this.ant = ant;
    }
    
}
