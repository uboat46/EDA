/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skiplist;

/**
 *
 * @author uboat46
 * @param <T>
 */
public class NodoSkip <T extends Comparable<T>>{
    
    private NodoSkip der;
    private NodoSkip izq;
    private NodoSkip aba;
    private NodoSkip top;

    
    private T elem;

    public NodoSkip(T elem) {
        this.elem = elem;
    }

    public NodoSkip(NodoSkip izq, NodoSkip der, T elem) {
        this.izq = izq;
        this.der = der;
        this.elem = elem;
    }

    public NodoSkip getDer() {
        return der;
    }

    public NodoSkip getIzq() {
        return izq;
    }

    public T getElem() {
        return elem;
    }
    

  

    public NodoSkip getAba() {
        return aba;
    }

    public NodoSkip getTop() {
        return top;
    }
    
      public void setDer(NodoSkip der) {
        if( this.der != null)
        {
            this.der.izq = der;
        }
        else
        {
            this.der = der;
        }
    }

    public void setIzq(NodoSkip izq) {
        if( this.izq != null)
        {
            this.izq.der = izq;
        }
        else
        {
            this.izq = izq;
        }
    }

    public void setAba(NodoSkip aba) {
        this.aba = aba;
    }

    public void setTop(NodoSkip top) {
        this.top = top;
    }
  
}
