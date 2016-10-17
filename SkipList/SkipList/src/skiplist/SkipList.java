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

    private NodoSkip cabeza,cola,start,end;

    public SkipList() {
        cabeza = new NodoSkip(null);
        cola = new NodoSkip(null);
        cabeza.setDer(cola);
        cola.setIzq(cabeza);
        start = cabeza;
        end = cola;
    }
    
    public void add(T elem){
        NodoSkip aux = busca(elem);
        aux.setDer(new NodoSkip(elem));
        aux = aux.getDer();
        boolean volado = volado();
        while(volado)
        {
            if(volado)
            {
                aux.setTop(new NodoSkip(elem));
                aux = aux.getTop();
                NodoSkip anterior = aux.getAba();
                anterior = anterior.getIzq();
                while( anterior.getElem()!= null && anterior.getAba() == null)
                {
                    anterior = anterior.getIzq();
                }
                if(anterior.getElem() != null)// es un elemento no un extrenmo
                {
                    anterior.getAba().setDer(aux);
                }
                else//es un extremo
                {
                    start.setTop(new NodoSkip(null));
                    start = start.getTop();
                    end.setTop(new NodoSkip(null));
                    end = end.getTop();
                    start.setDer(end);
                    start.setDer(aux);
                    break;
                }
            }
            volado = volado();
        }
        
    }
    
    public NodoSkip busca(T elem)
    {
        NodoSkip aux = busca(elem,start);
        return aux;
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
            if( aux.getElem()!= null && aux.getElem().compareTo(elem) == 0)
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
                    return aux;
                }
            }
        }
        return aux;
    }
    
    private boolean volado()
    {
        double x = Math.random();
        return x >= 0.5;
        //return true;
    }
    
    public void imprime()
    {
       NodoSkip aux = start;
       if(aux.getAba()!= null)
       {
            while(aux.getAba()!= null)
            {
                NodoSkip act = aux.getDer();
                while( act.getElem()!= null && act.getDer()!= null)
                {
                    System.out.print(act.getElem().toString()+"--");
                    act = act.getDer();
                }
                System.out.println("");
                aux = aux.getAba();
            }
            NodoSkip act = aux.getDer();
            while( act.getElem()!= null && act.getDer()!= null)
            {
                System.out.print(act.getElem().toString()+"--");
                act = act.getDer();
            }
       }
       else
       {
            NodoSkip act = aux.getDer();
            while( act.getElem()!= null && act.getDer()!= null)
            {
                System.out.println(act.getElem().toString()+"--");
                act = act.getDer();
            }
       }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        SkipList<Integer> li = new SkipList<>();
        
        li.imprime();
        li.add(5);
        li.imprime();
        li.add(10);
        li.imprime();
    }
    
}
