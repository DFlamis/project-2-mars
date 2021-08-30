package Clases;

/**
 *
 * @author Grupo 2
 */
public class Coordenadas
{
    private double latitud;
    private double longitud;
    //3,389.5-> marte || 6378.137->tierra
    private final double radioMars;  

    //constructor

    /**
     *
     * @param lat
     * @param lon
     */
    public Coordenadas(double lat, double lon)
    {
        this.radioMars = 3389.5;
        this.latitud = lat;
        this.longitud = lon;
    }

    
    /**
     *
     * @param latitud
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    
    
    /**
     *
     * @param longitud
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    
    
    //getters

    /**
     *Retorna la latitud
     * @return
     */
    public double getLatitud()
    {
        return this.latitud;
    }

    /**
     *retorna la Longitud 
     * @return Longitud
     */
    public double getLongitud()
    {
        return this.longitud;
    }
    
    
    //metodo para calcular la distancia entre el rover y crater

    /**
     *Recibe coordenadas como parametros y retorna un valor doble que resulta del 
     * calculo de la distancia entre dos puntos
     * @param c1
     * @param c2
     * @return
     */
    public double calcularDistanciaDosPuntos(Coordenadas c1, Coordenadas c2) {

        double dlat = c2.getLatitud() - c1.getLatitud();
        double dlong = c2.getLongitud() - c1.getLongitud();
        double a = Math.pow(Math.sin(Math.toRadians(dlat) / 2),2) + 
        Math.cos(Math.toRadians(c1.getLatitud())) * 
        Math.cos(Math.toRadians(c2.getLatitud())) * 
        Math.pow(Math.sin(Math.toRadians(dlong) / 2),2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double d = radioMars * c;
        return d; 
    }    
    
}