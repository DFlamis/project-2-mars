package LecturaArchivos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;

import Clases.BaseDatos;
import Clases.Coordenadas;
import Clases.Crater;
import Clases.Reporte;

public class ResourcesReader
{
    private static String filePathC = Directory.RESOURCE_FOLDER + "/crateres_info.txt";
    private static String filePathS = Directory.RESOURCE_FOLDER + "/saved_crater.txt";


    public ResourcesReader()
    {
    }

    public static ArrayList<Crater> readCrateres() throws IOException
    {
        ArrayList<Crater> cr = new ArrayList();
        try(BufferedReader bf = new BufferedReader(new FileReader(filePathC)))
        {
            String linea;
            while((linea = bf.readLine())!=null)
            {
                String l[] = linea.split(",");
                cr.add(new Crater(l[0], l[1],  Double.parseDouble(l[4]), new Coordenadas(Double.parseDouble(l[2]),Double.parseDouble(l[3])), false));
            }
        }
        return cr;
    }
    
    //Guarda un txt con reportes separados con guiones
    //Forma -> Fecha[0]-minerales[1]-id[2]-nombre[3]-radio[4]-latitud[5]-longitud[6]-sensado[7]
    public static boolean toSave()
    {
        try
        {
            ArrayList<Reporte> voki = BaseDatos.reporteData;
            File file = new File(Directory.RESOURCE_FOLDER + "/saved_crater.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String toWrite = "";
            //Para poder ser cambiada en caso de que el string de split este usado en la cadena de texto
            String sp = "/";

            for(Reporte r: voki)
            {
                Crater cr = r.getCrater();
                toWrite += r.getSFecha()+sp+r.getMineral()+sp+ cr.getId()+sp+cr.getNombre()+sp+cr.getRadio()+sp+cr.getCentro().getLatitud()+sp+cr.getCentro().getLongitud()+sp+cr.isSensado()+"\n";
            }
            
            bw.write(toWrite);
            bw.close();

            return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }

    //Lee el txt de reportes y lo almacena directamente en reporteData
    public static void loadSaved() throws IOException
    {
        try(BufferedReader bf = new BufferedReader(new FileReader(filePathS)))
        {
            String linea;
            while((linea = bf.readLine())!=null)
            {
                String l[] = linea.split("/");
                LocalDate fecha = LocalDate.parse(l[0], DateTimeFormatter.ofPattern("y-M-d"));
                Crater crat = new Crater(l[2], l[3], Double.parseDouble(l[4]), new Coordenadas(Double.parseDouble(l[5]), Double.parseDouble(l[6])), Boolean.parseBoolean(l[7]));
                BaseDatos.reporteData.add(new Reporte(fecha, crat, l[1],l[3]));
            }
        }
    }

}
