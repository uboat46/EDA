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
            NodoSkip aux = this.der;
            aux.izq = der;
            this.der = der;
            der.der = aux;
            der.izq = this;
            
        }
        else
        {
            this.der = der;
        }
    }

    public void setIzq(NodoSkip izq) {
        if( this.izq != null)
        {
            NodoSkip aux = this.izq;
            aux.der = izq;
            this.izq = izq;
            izq.izq = aux;
            izq.der = this;
        }
        else
        {
            this.izq = izq;
        }
    }
    
    public void setFder(NodoSkip der)
    {
        if( this.der != null)
        {
            der.izq = this;
            this.der = der;
        }
        else
        {
            this.der = der;
        }
    }
    public void setFizq(NodoSkip izq)
    {
        if( this.izq != null)
        {
            izq.der = this;
            this.izq = izq;
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
        if(top != null)
            top.setAba(this);
    }

    void agregaTop(T elem,NodoSkip start,NodoSkip end)
    {           
                NodoSkip nuevo;
                this.setTop(new NodoSkip(elem));
                nuevo = this.top;
                NodoSkip anterior = this;
                anterior = anterior.izq;
                while( anterior.elem != null && anterior.top == null)
                {
                    anterior = anterior.izq;
                }
                if(anterior.getElem() != null)// es un elemento no un extrenmo
                {
                    NodoSkip antTop = anterior.top;
                    antTop.setDer(nuevo);
                }
                else//es un extremo
                {
                    start.setTop(new NodoSkip(null));
                    start = start.getTop();
                    end.setTop(new NodoSkip(null));
                    end = end.getTop();
                    start.setDer(end);
                    start.setDer(nuevo);
                }
    }
  
}
