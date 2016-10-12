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
public class SkipList <T extends Comparable<T>> {

    private final NodoSkip cabeza,cola,start,end;

    public SkipList() {
        cabeza = new NodoSkip(null);
        cola = new NodoSkip(null);
        cabeza.setDer(cola);
        cola.setIzq(cabeza);
        start = cabeza;
        end = cola;
    }
    
    public void add(T elem){
        
    }
    
    private NodoSkip busca(T elem,NodoSkip n)
    {
        NodoSkip aux = null;
        if(n.getDer() != null)
        {
            aux = n.getDer();
            while( aux.getElem() != null && aux.getElem().compareTo(elem) < 0)
            {
                aux = aux.getDer();
            }
            if( aux.getElem().compareTo(elem) == 0)
            {
                return aux;
            }
            else
            {
                aux = aux.getIzq();
                if( aux.getAba() != null)
                {
                    aux = aux.getAba();
                    aux = busca(elem,aux);
                }
                else
                {
                    return null;
                }
            }
        }
        return aux;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
