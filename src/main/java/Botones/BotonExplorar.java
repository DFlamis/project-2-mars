package Botones;

import Clases.Crater;
import Clases.Rover;
import LecturaArchivos.Directory;
import LecturaArchivos.ResourcesReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
  /**
 *Clase del Boton Explorar para crear la ventana donde se visualizaran los crateres y el rover
 * rover.
 * @author Grupo 2 Rocio
 */
public  class BotonExplorar {
    public Pane contenedorFigura;
    public ArrayList<Crater> crateres;
    public BorderPane root;
    public HBox right;
    public HBox top;
    public HBox comander;
    public HBox lineaTexto;
    public HBox lineaTexto2;
    public AnchorPane createT;
    public VBox container;
    public Button regresar;
    public TextField comando;
    public TextArea comandoIng;
    public Label mensaje;
    public Circle circulo;
    public Circle circulo1;
    public Rover nave;
    
    /**
     *Constructor de la clase BotonExplorar, llama a los metodos que crean los componentes de la ventana
     * de exploracion y los almacena en el contenedor raiz.
     * 
     * @throws IOException
     */
    public BotonExplorar() throws IOException{
        root = new BorderPane();
        root.setTop(topL());
        root.setRight(paneles());
        root.setCenter(vistaCrateres());
        root.setPadding(new Insets(15,15,15,15));
        root.setOnKeyPressed((eh) -> {
             KeyEventEnter(eh);
        });
    }

     /**
     *Creacion del Pane que contiene el fondo, y dibuja los circulos de los crateres
     * Contiene al ROVER
     * Se ubica en la seccion izquierda del contenedor raiz
     * @return Pane
     * @throws IOException
     */
    public Pane vistaCrateres() throws IOException{
        contenedorFigura = new Pane();
          //Llamar a la imagen del archivo
         
          
          crateres = ResourcesReader.readCrateres();
          
          for (Crater c: crateres){
            circulo = new Circle(c.getCentro().getLatitud(),c.getCentro().getLongitud(),c.getRadio());
            circulo.setStrokeWidth(2);
            circulo.setStroke(Color.RED);
            circulo.setFill(Color.rgb(27, 20, 90,0 ));  
            
            contenedorFigura.getChildren().add(circulo);
            contenedorFigura.setClip(new Rectangle(850, 700));
          }
        
        nave = new Rover();
        final var milenaryFalcon = nave.getRobot();
        contenedorFigura.getChildren().add(milenaryFalcon);
      

        Image imgv1 = new Image(new FileInputStream(Directory.RESOURCE_FOLDER+"/marte.jpg"),900,700,false,true);
          // create a background image 
        BackgroundImage backgroundimage = new BackgroundImage(imgv1,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundPosition.DEFAULT,  
                                                BackgroundSize.DEFAULT); 
            // create Background 
        Background background = new Background(backgroundimage); 
            // set background 
        contenedorFigura.setBackground(background );
        contenedorFigura.setPrefSize(850,700); 
        contenedorFigura.setPadding(new Insets(10,10,10,10));
        return contenedorFigura;
    }
    
    /**
     *Creacion del Anchor pane en el top del contenedor raiz
     * Creacion del boton regresar y su respectiva accion para volver a la pantalla principal
     * @return
     */
    public AnchorPane topL(){
       //Creacion del boton regresar
        createT = new AnchorPane();
        
        top = new HBox();
        regresar = new Button("Regresar");
        top.getChildren().addAll(regresar);
        createT.getChildren().add(regresar);
        AnchorPane.setLeftAnchor(regresar, 10d);
        AnchorPane.setTopAnchor(regresar, 10d);
         //Volver a la ventana de inicio
         
        regresar.setOnMouseClicked((e) -> {
            Stage stage = (Stage) regresar.getScene().getWindow();
            stage.close();
        });
        
        return createT;
    }
    
    /**
     *Creacion de VBox, contiene el textField para ingreso de comandos y un textArea para visualizacion
     * de historial de comandos ingresados
     * Cuenta con dos Label y un HBox para cada elemento
     * @return
     */
    public VBox paneles(){
           //Creacion de Label para ingreso de comandos 
        right = new HBox();
        lineaTexto = new HBox();
        //Creacion de cuadro de texto para ingreso de comandos del rover
        comando = new TextField();
        comando.setMaxSize(120,70);    
        
         //Fijacion de comando y comandoIng en el HBox
        lineaTexto.getChildren().addAll(new Label("Ingrese comando:"));
        lineaTexto.setAlignment(Pos.TOP_RIGHT);
        right.getChildren().addAll(comando);
        right.setAlignment(Pos.TOP_RIGHT);
        
        
        ////Creacion de Label para visualizar comandos ingresados
        comander = new HBox();
        lineaTexto2 = new HBox();
        //Creacion de historial de comandos usados que se muestra por pantalla
        comandoIng = new TextArea();
        comandoIng.setMaxSize(120, 500); 
        comandoIng.setEditable(false);
        comandoIng.setDisable(false);
        //Fijacion de comando y comandoIng en los HBox
        lineaTexto2.getChildren().addAll(new Label("Comandos Ingresados"));
        lineaTexto2.setAlignment(Pos.CENTER_RIGHT);
        comander.getChildren().addAll(comandoIng);
        comander.setAlignment(Pos.CENTER_RIGHT);
        
        //Creacion del Vbox
        container = new VBox();
        container.getChildren().addAll(lineaTexto,right,lineaTexto2,comander);
        container.setAlignment(Pos.TOP_RIGHT);
        container.setSpacing(10);
       
    
        return container;
    }
    
    /**
     *Metodo KeyEvent que efectua los comandos ingresados por el usuario luego de presionar ENTER
     * @param event
     */
    public void KeyEventEnter(KeyEvent event){
        
        switch(event.getCode()){
           case ENTER:
               String comand = comando.getText();
              comandoIng.appendText("\n"+comand);
              switch(leerComando(comand)){
                  case 1:
                      nave.avanzar();
                      break;
                  case 2:
                      nave.girar(leerDistanciaGrados(comand));
                      break;
                  case 3:
                      ArrayList<Double> c = leerCoordenadas(comand);
                      nave.dirigir(c.get(0),c.get(1));
                      
                      break;
                  case 4:
                      nave.sensar();
                      contenedorFigura.getChildren().add(nave.circulo1);
                      root.getChildren().add(contenedorFigura);
                      comando.clear();
                      break;
              }
              comando.clear();
              break;
       }   
    }
    
     /**
     *Metodo privado leerComando que retorna un entero, 
     * El usuario ingresa un comando para el ROVER, y los clasifica en casos
     * @return int
     * @param comando
     */
    private int leerComando(String comando) {
        int metodo=0;
        String[] splitted = comando.split(":");
        switch(splitted[0]) {
            case "avanzar":
                metodo = 1;
                break;
            case "girar":
                metodo = 2;
                break;
            case "dirigir":
                metodo = 3;
                break;
            case "sensar":
                metodo = 4;
                break;
        }
        return metodo;
    }
    
    /**
     *Metodo privado leerDistanciaGrados que retorna un doble 
     * Si el comando da al caso 2, el metodo privado leerDistanciaGrado> separa el texto
     * y convierte los grados a double
     * @return double
     * @param comando
     */
    private double leerDistanciaGrados(String comando) {
        String[] splitted = comando.split(":");
        return Double.parseDouble(splitted[1]);
    }
    

    /**
     *Metodo privado leerCoordenadas que retorna un ArrayList<Double> 
     * Si el comando da al caso 2, el metodo privado leerCoordenada separa el texto
     * y convierte las coordenadas en doubles que se almacenan en un arrayList
     * @return doubleArrayList<Double>
     * @param comando
     */
    private ArrayList<Double> leerCoordenadas(String comando) {
        String[] splitted = comando.split(":")[1].split(",");

        ArrayList<Double> n = new ArrayList();
        n.add(Double.parseDouble(splitted[0]));
        n.add(Double.parseDouble(splitted[1]));
        
        return n;
    }

    /**
     *
     * @return contenedor Raiz
     */
    public Pane newPane(){
        return root;    
    }

 }