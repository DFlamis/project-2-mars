package Clases;

/**
 *
 * @author Grupo 2 
 */
public class Crater
{
    private boolean sensado;
    private String id;
    private String nombre;
    private double radio;
    private double distancia;
    private Coordenadas centro;
    
    //constructor

    /**
     *Constructor de la clase Crater que inicializa las variables
     * @param id
     * @param nombre
     * @param radio
     * @param centro
     * @param sensado
     */
    public Crater(String id, String nombre, double radio, Coordenadas centro, boolean sensado)
    {
        this.id = id;
        this.nombre = nombre;
        this.radio = radio;
        this.centro = centro;
        this.sensado = sensado;
    }
    public Crater(String name, Coordenadas c, double radio, double distancia){
        this.nombre = name;
        this.centro = c;
        this.radio = radio;
        this.distancia = distancia;
        
    }
    
    /**
     *Obtener el valor de censo de un Crater
     * @return
     */
    public boolean isSensado() {
        return sensado;
    }

    /**
     *Obtener el numero de identificacion de un Crater
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *Obtener coordenadas de un Crater
     * @return
     */
    public double getRadio() {
        return radio;
    }

    /**
     *Obtener coordenadas de un Crater
     * @return
     */
    public Coordenadas getCentro() {
        return centro;
    }

    
    
    //setters

    /**
     *Cambiar el valor de censo de un Crater
     * @param valor
     */
    public void setSensado(boolean valor)
    {
        this.sensado = valor;
    }

    
    
    //getters

    /**
     *Nombre del Crater
     * @return
     */
    public String getNombre()
    {
        return this.nombre;
    }
    
    /**
     *Retorna un String con los atributos del Crater
     * @return
     */
     //public void String@Override
    @Override
    public String toString(){
          return "id "+id+"| nombre "+nombre+"| radio "+radio+"| centro "+centro.getLatitud()+","+centro.getLongitud()+"| sensado "+sensado;
    }
    
    
    

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    
}