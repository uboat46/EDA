/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablahash;

/**
 *
 * @author uboat46
 */
public class TablaHash <T>
{
    Nodo<T> tabla[];
    int cant;
    double factorCarga;
    
    public void insert(T elem)
    {
        if( cant/tabla.length > factorCarga)
            expand();
        int i = hashIn(elem);
       Nodo<T> n =tabla[i];
       Nodo<T> x = new Nodo<T>(elem);
       x.setDer(n);
       tabla[i] = x;
    }
    
    public void expand()
    {
        Nodo<T> nueva = new Nodo<T>[size];
        for (int i = 0; i < tabla.length; i++)
        {
            if(tabla[i] != null)
            {
                Nodo<T> aux = tabla[i];
                while(aux != null)
                {
                    inserta(aux.getElem(),nueva);
                    aux = aux.getDer();
                }
            }
        }
        this.tabla = nueva;
    }
    
    public void inserta(Nodo<T>[] arr, T elem)
    {
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
