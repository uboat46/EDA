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
    private int n = 0;

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
        int cant = ((int)(Math.log(n)/ Math.log(2))) +1;//+1??
        int i = 0;
        while(i < cant)//i < cant
        {
            if(volado)
            {
                aux.setTop(new NodoSkip(elem));
                aux = aux.getTop();
                NodoSkip anterior = aux.getAba();
                anterior = anterior.getIzq();
                while( anterior.getElem()!= null && anterior.getTop() == null)
                {
                    anterior = anterior.getIzq();
                }
                if(anterior.getElem() != null)// es un elemento no un extrenmo
                {
                    anterior.getTop().setDer(aux);
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
            i++;
        }
        n++;
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

    public NodoSkip elimina(T elem)
    {
        NodoSkip aux = busca(elem);
        while( aux.getAba() != null)
        {
            aux = aux.getAba();
        }
        if( aux.getElem().compareTo(elem) == 0)//si estaba el elemento
        {
            NodoSkip borra = aux;
            NodoSkip izq = aux.getIzq();
            izq.setFder(aux.getDer());
            
            while(borra.getTop() != null)
            {
                borra = borra.getTop();
                izq = borra.getIzq();
                izq.setFder(borra.getDer());
            }
            if( borra.getIzq().getElem() == null && borra.getDer().getElem() == null)//era el de hasta arriba
            {
                if( start.getAba() != null)//no era el unico elemento
                {
                    start = start.getAba();
                    start.setTop(null);
                    end = end.getAba();
                    end.setTop(null);
                }
            }
        }
        else//no estaba el elemento
        {
            aux = null;
        }
        return aux;
        
    }
    
    private boolean volado()
    {
        double x = Math.random();
        return x >= 0.5;
        //return true;
    }
    
    public void restructura()
    {   
        start = cabeza;
        end = cola;
        NodoSkip aux = cabeza;
        int cant = ((int)(Math.log(n)/ Math.log(2)));
        while( aux != null)
        {
            aux.setTop(null);
            aux = aux.getDer();
        }
        System.out.println("Imprimiendo despues de borrar todo");
        System.out.println("=============================");
        this.imprimeBien();
        //restructura(cant);
        int i = 0;
        System.out.println("Restructurando");
       System.out.println("=============================");
        while( i < cant)
        {
            restructura(cant,start,end);
            i++;
        }

    }
    
     private void restructura(int cant,NodoSkip act,NodoSkip fin)
     {
        if( cant > 0)
        {
            NodoSkip aux = act.getDer();
            int i = 2;
            while( aux != null && aux.getElem()!= null)
            {
                if( i%2 == 0)
                {
                    aux.agregaTop(aux.getElem(),act, fin);
                    if(start.getTop() != null)
                    {
                        start = start.getTop();
                        end = end.getTop();
                    }
                } 
            aux = aux.getDer();
            i++;
            } 
        }
     }
     
    private void restructura(int cant)
    {
        if (cant > 0)
        {
            NodoSkip act = start.getDer();
            for (int i = 1; i < cant + 1; i++)
            {
                NodoSkip aux = act;
                if( aux != null && aux.getElem() != null)
                {
                    for (int j = 0; j < i; j++)
                    {   
                        aux.agregaTop(aux.getElem(),start,end);
                        if(start.getTop() != null)
                        {
                            start = start.getTop();
                            end = end.getTop();
                        }
                        aux = aux.getTop();
                    }
                }
                
                
                for (int j = 0; j < 2; j++)
                {
                    if(act.getDer() != null)
                        act = act.getDer();
                }
            }
            for (int i = cant -1 ; i > 0; i--)
            {
                NodoSkip aux = act;
                if( aux != null && aux.getElem() != null)
                {
                    for (int j = 0; j < i; j++)
                    {
                        aux.agregaTop(aux.getElem(),start,end);
                        aux = aux.getTop();
                    }
                }
                
                for (int j = 0; j < 2; j++)
                {
                    if(act.getDer() != null)
                        act = act.getDer();
                }
            }
        }
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
    
    public void imprimeBien()
    {
        NodoSkip aux = cabeza;
        while(aux != null)
        {
            NodoSkip act = aux;
            while( act != null && act.getElem() != null)
            {   
                String res = String.format("%1$02d",act.getElem());
                System.out.print(res + "--");
                act = act.getTop();
            }
            aux = aux.getDer();
            System.out.println("");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        SkipList<Integer> li = new SkipList<>();

//        for (int i = 1; i < 19; i++) {
//            li.add(i);
//        }
//        System.out.println("Insercion de 20 elementos");
//        System.out.println("=============================");
//        li.imprime();
//        System.out.println("");
//        System.out.println("Insercion de 20 elementos");
//        System.out.println("=============================");
//        li.imprimeBien();
//        
//        li.restructura();
//        
//        li.imprimeBien();
//        
//        li.elimina(1);
//        
//        li.imprimeBien();
//        
//        li.elimina(9);
//        
//        li.imprimeBien();
       System.out.println("Insercion de 64 elementos");
       System.out.println("=============================");
       for (int i = 0; i < 64; i++) 
       {
            li.add(i);
       }
       li.imprimeBien();
       li.restructura();
       li.imprimeBien();
       System.out.println("Eliminando");
       System.out.println("=============================");
       li.elimina(32);
       li.elimina(30);
       li.elimina(34);
       li.elimina(28);
       li.elimina(36);
       
       li.imprimeBien();
       
       li.restructura();
       
       li.imprimeBien();
    }
    
}
