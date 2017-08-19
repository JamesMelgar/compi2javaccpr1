/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1compilarodores2;
import gramatica_usu.Token;
import java.lang.Math.*;
/**
 *
 * @author James_PC
 */
public class Recorrer {
    public static int conbin=1;
    public static int DevolverNumero(Nodo nodo){
        int numero = 0;
        int tem=0;
        tem=Integer.parseInt(nodo.getValor());
        for(Nodo hijos : nodo.getHijos()){  
            numero = DevolverNumero(hijos);
            tem=(int) (tem*Math.pow(2,conbin)+numero);
            conbin += 1;
        }
        return tem;
    }
    
    public static void ImprimirToken(Nodo nodo){
        System.err.println("*********************");
        System.err.println("El token es: ");
        for(Nodo hijos : nodo.getHijos()){  
            ImprimirToken(hijos);
        }
    }

}
