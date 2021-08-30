package Clases;
import java.util.ArrayList;


public class BaseDatos
{
    //Forma -> LocalDateTime, String, Crater
    public static ArrayList<Reporte> reporteData = new ArrayList<>();

    //ArrayList<Reporte> filtrada
    public static ArrayList<Reporte> reportsFiltered = new ArrayList<>();

    //Meses y dias - Al estar formados de pares e impares es ams sencillo esto que una ecuacion
    //Meses con 31 dias
    public static Integer monthLong[] = {1,3,5,7,8,10,12};
    //Meses con 30 dias
    public static Integer monthShort[] = {4,6,9,11};

    //Transfiere los valores de una lista a otra para no perder los datos de esta pues en esta nueva lista se aplicaran los filtros
    public static void shareData()
    {
        for(Reporte r: BaseDatos.reporteData)
        {
            BaseDatos.reportsFiltered.add(r);
        }
    }
}