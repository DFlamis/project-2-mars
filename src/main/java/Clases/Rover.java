package Clases;

import LecturaArchivos.Directory;
import LecturaArchivos.ResourcesReader;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Math.abs;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;

/**
 *
 * @author Grupo2 Rocio
 */
public class Rover implements Movimiento{
    public  Coordenadas ubicacion;
    public Minerales mineral;
    public Double grados;
    private Double distanciaRecorrer;
    public ImageView robot;
    private double d;
    private int pos;
    public Crater cr;
    public ArrayList<Crater> crateres;
    public Circle circulo1;
    
    
     public Rover() throws IOException{
        robot = new ImageView(new Image(new FileInputStream(Directory.RESOURCE_FOLDER+"/HM1.png"),81,60,false,true));
        this.ubicacion = new Coordenadas(0,0);
        robot.setLayoutX(ubicacion.getLatitud());
        robot.setLayoutY(ubicacion.getLongitud());
        this.grados = 0.0;
        this.d = 10;
        crateres = ResourcesReader.readCrateres();
        
    }

    /**
     *Circulo del crater sensado
     * @return circulo
     */
    public final Circle getCirculo1() {
        return circulo1;
    }
    
    /**
     *
     * @return
     */
    @Override
     public final ImageView getRobot() {
        return robot;
    }

    /**
     *Actualizar angulo de rover
     * @param grados
     */
    public final void setGrados(Double grados) {
        this.grados = grados;
    }
  
    /**
     *Retorna las coordenadas del Rover 
     * @return
     */
    public final Coordenadas getUbicacion() {
        return ubicacion;
    }
    //Utilizar esta funcion cuando se explore un crater
    //Genera  un reporte con la fecha actual y los minerales aleatorios

    /**
     *Utilizar esta funcion cuando se explore un crater
     * Genera  un reporte con la fecha actual y los minerales aleatorios
     * @param crater
     */
    public final void generarReporte(Crater crater)
    {
        String minerales;
        Minerales min = new Minerales();
        minerales = min.mineralesEcontrados();
        Reporte r = new Reporte(LocalDate.now(), crater, minerales, crater.getNombre());
        BaseDatos.reporteData.add(r);
        Alert dialogoA = new Alert(AlertType.INFORMATION);
        dialogoA.setTitle("CENSO DE CRATER: "+crater.getNombre()+", "+crater.getId());
        dialogoA.setHeaderText("MINERALES ENCONTRADOS");
        dialogoA.setResizable(true);
        dialogoA.setContentText(minerales);
        dialogoA.setHeight(200);
        dialogoA.setWidth(800);
        dialogoA.initStyle(StageStyle.DECORATED);
        dialogoA.showAndWait();
    }
   
    /**
     *Metodo sin retorno: avanzar del Rover que sera implementado en la opcion Exlorar,
     * Va actualizando la ubicacion del Rover para que aparezca en la nueva posicion.
     */
    
     @Override
    public final void avanzar() {
        
        double rad = Math.toRadians(grados);
        double x = (Math.cos(rad)*this.d) + ubicacion.getLatitud();
        double y = Math.sin(rad)*this.d + ubicacion.getLongitud();
        this.robot.setTranslateX(x);
        this.robot.setTranslateY(y);
        ubicacion.setLatitud(x);
        ubicacion.setLongitud(y);

    }

    /**
     *Recibe como parametro un double g que sera ingresado por el usuario
     * Double g, son los grados que el Rover debera girar.
     * @param g
     */

     @Override
    public final void girar(double g) {
        
        this.robot.setRotate(g+grados);
        grados = g+grados;
    }
    
    /**
     *
     * @param degree
     */
    public final void girarDirigir(final double degree){
        girar(degree);
    }
    
    /**
     *Recibe dos Double como parametros que representan las coordenadas de la nueva ubicacion a la cual se dirige el Rover
     * Actualiza la ubicacion del Rover e implementa el metodo avanzar mediante hilos para apreciar el movimiento de objeto Rover. 
     * @param x
     * @param y
     */
    //aun no hago pull

     @Override
    public final void dirigir(final double x, final double y) {
        //ubicacion.setLatitud(0.0);
        //ubicacion.setLongitud(0.0);
        girar(-grados);
        Coordenadas newUbicacion = new Coordenadas(x,y);
        double c =     ubicacion.getLatitud();
        double d =     ubicacion.getLongitud();
        double puntosX = x-c;
        double puntosY = y-d;
        //distanciaRecorrer = newUbicacion.calcularDistanciaDosPuntos(newUbicacion, ubicacion);
        distanciaRecorrer = distanciaR(newUbicacion,ubicacion);
        double degrees =  Math.toDegrees(abs(Math.atan ((y-d)/(x-c))));
       //** pos = calcularPos(puntosX,puntosY);
        //**cambioAngulo(grades, pos);
        if (puntosX >= 0 && puntosY >= 0){
            girarDirigir(degrees);
        }else if(puntosX < 0  &&  puntosY >= 0){
            girarDirigir(180-degrees);
        }else if(puntosX < 0 && puntosY < 0){
            degrees += 180;
            girarDirigir(degrees);
        }else if(puntosX >= 0 && puntosY < 0){
            degrees = 360 - degrees;
            girarDirigir(degrees);
        }
        ///**girar(grados);
        for (Crater crt: crateres){
           double distancia = distanciaR(newUbicacion,crt.getCentro());
           if (distancia<crt.getRadio()){
               cr = crt;
               System.out.print(cr+","+distancia+"\n");
          }
        }
        IniciarRecorrido ir = new IniciarRecorrido();
        Thread t = new Thread(ir);
        t.start();  
         
    }
     /**
     *Sensa el crater y retorna string de minerales
     */
    //public Circle sensar( ){

    @Override
    public final void sensar( ){
            System.out.print(cr);
            circulo1 = new Circle( cr.getCentro().getLatitud(),cr.getCentro().getLongitud(),cr.getRadio());
            circulo1.setStrokeWidth(2);
            circulo1.setStroke(Color.RED);
            if (cr.isSensado()== false){
                circulo1.setFill(Color.rgb(255,0,0,0.3));  
                System.out.print(cr);
                cr.setSensado(true);
                this.generarReporte(cr);
                
            }else{
                System.out.print(cr);
                this.generarReporte(cr);
            } 
            
    }         
    
    public final void Alerta(){
        Alert dialogoA = new Alert(AlertType.CONFIRMATION);
        dialogoA.setTitle("");
    }
         
    /**
     *Retorna un doble que representa la  distancia entre dos puntos
     * @param c1
     * @param c2
     * @return
     */
     @Override
    public final double distanciaR(final Coordenadas c1, final Coordenadas c2){
        double c = c2.getLatitud();
        double d =     c2.getLongitud();
        double puntosX = c1.getLatitud()-c;
        double puntosY = c1.getLongitud()-d;
        double recorrer = Math.sqrt(Math.pow((puntosX),2)+Math.pow((puntosY),2));
       return recorrer;
    }
       
    /**
     *Hilo creado para visualizar el avance del Rover
     * 
     */
    class IniciarRecorrido implements Runnable{
    
        @Override
        public void run(){
            try {
                final var veces = (int) (distanciaRecorrer/d);
                for (int i=0; i<veces;i++){
                  Platform.runLater(()->{
                      avanzar();
                      distanciaRecorrer = distanciaRecorrer - d;
                  });
                Thread.sleep(200);
               }
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
    };
    
}