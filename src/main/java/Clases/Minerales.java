package Clases;
import java.util.ArrayList;

public class Minerales
{
    //retorna un ArryList de minerales aleatorios
    public String mineralesEcontrados()
    {
        String voki[] = {"Titanomagnetita","Magnetita","Olivino","Hematita ", "Kieserita", "Sulfato anhidro", "Bassanita", "Hexahidrita", "Epsomita", "Yeso"};
        String mineral = "";
        //valore entre cero y el maximo de la lista - 1
        double cantidad = Math.round(Math.random()*(voki.length-1));
        //por si cantidad se hace cero al menos un mineral fue encontrado
        if(cantidad == 0)
        {
            cantidad = 1;
        }
        for(int x = 0; x <= cantidad; x++)
        {
            int indice = (int) Math.round(Math.random()*(voki.length-1));
            mineral += voki[indice];
            if(x < cantidad)
            {
                mineral += ", ";
            }
        }
        return mineral;
    }
}