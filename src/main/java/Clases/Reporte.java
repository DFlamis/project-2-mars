package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Reporte
{
    private LocalDate fecha;
    private Crater lugar;
    //Specials
    private SimpleStringProperty mineral;
    private SimpleStringProperty nombre;
    private SimpleStringProperty sFecha;

    //Constructor vacio
    public Reporte()
    {
    }
    
    //Constructor
    public Reporte(LocalDate fecha, Crater lugar, String mineral, String nombre)
    {
        this.fecha = fecha;
        this.lugar = lugar;
        this.sFecha = new SimpleStringProperty(fecha.toString());
        this.mineral = new SimpleStringProperty(mineral);
        this.nombre = new SimpleStringProperty(nombre);
    }

    //filtro por fecha
    public ArrayList<Reporte> filtrarFecha(LocalDate fi, LocalDate ff, ArrayList<Reporte> report)
    {
        ArrayList<Reporte> voki = new ArrayList();
        for(Reporte r: report)
        {
            LocalDate dia = r.getFecha();
            if(dia.isAfter(fi) && dia.isBefore(ff))
            {
                voki.add(r);
            }
            else if(dia.equals(fi) || dia.equals(ff))
            {
                voki.add(r);
            }
        }
        System.out.println(voki.size());
        return voki;
    }

    //getters
    public LocalDate getFecha()
    {
        return this.fecha;
    }

    public Crater getCrater()
    {
        return this.lugar;
    }

    //get Especiales
    public String getMineral()
    {
        return this.mineral.get();
    }

    public String getNombre()
    {
        return this.nombre.get();
    }

    public String getSFecha()
    {
        return this.sFecha.get();
    }

}