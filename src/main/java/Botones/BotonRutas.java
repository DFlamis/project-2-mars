/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Botones;

import Clases.Coordenadas;
import Clases.Crater;
import LecturaArchivos.ResourcesReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Grupo2 VegaJ
 */
public class BotonRutas {
    public VBox root;
    public HBox top;
    public HBox subTop;
    public TextField cratersList;
    public ArrayList<String> cratersArray;    
    
    public ArrayList<Crater> listaCrateres;
    /**
     *Ventana para la planificacion de rutas
     */
    public BotonRutas(){
    
        root = new VBox();
        top = new HBox();
        Button back = new Button("Regresar");
        top.getChildren().addAll(back);

        subTop = new HBox();
        Label texto = new Label("Nombre cráteres: ");
        cratersList = new TextField();
        Button btnR = new Button("Continuar");
        subTop.setSpacing(10);
        
        subTop.getChildren().addAll(texto, cratersList, btnR);
        
        subTop.setAlignment(Pos.CENTER);

        VBox rutasList = new VBox();
        rutasList.setAlignment(Pos.BOTTOM_CENTER);
        TextArea textRutas = new TextArea("LISTA DE CRÁTERES OPTIMIZADA:\n");
                
        rutasList.getChildren().add(textRutas);
        rutasList.setPadding(new Insets(20));
        rutasList.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(top,subTop);
        root.setPadding(new Insets(10,10,10,10));

        //MouseAction para retorna a la vista principal
        back.setOnMouseClicked((e) -> {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
        });
        
        
        //MouseAction para mostrar las rutas al dar clic en CONTINUAR
        btnR.setOnMouseClicked((e) -> {
            try {
                
                ArrayList<Crater> crateresList = ResourcesReader.readCrateres();
                String[] cratersExp = capitalizeString(cratersList.getText().trim()).split(",");
                cratersArray = new ArrayList<>(Arrays.asList(cratersExp));
                limpiarCraters(cratersArray);
                int valor = 1;
                listaCrateres = new ArrayList<>(cratersDistance(cratersArray,crateresList));
                System.out.println(cratersArray);
                System.out.println(crateresList);
                
                for (Crater c: listaCrateres){
                    textRutas.appendText(valor+". "+c.getNombre()+"   "+c.getDistancia()+" KMs"+"\n");
                    valor++;
                }
                
                cratersList.setDisable(true);
                btnR.setDisable(true);
                root.getChildren().add(rutasList);
                cratersList.clear();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        //KeyAction para mostrar las rutas al dar clic en ENTER
        cratersList.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ENTER){
                try {
                    
                    ArrayList<Crater> crateresList = ResourcesReader.readCrateres();
                    String[] cratersExp = capitalizeString(cratersList.getText()).trim().split(",");
                    cratersArray = new ArrayList<>(Arrays.asList(cratersExp));
                    
                    int valor = 1;
                    listaCrateres = new ArrayList<>(cratersDistance(cratersArray,crateresList));
                    for (Crater c: listaCrateres){
                        textRutas.appendText(valor+". "+c.getNombre()+"   "+c.getDistancia()+"\n");
                        valor++;
                    }
                    
                    cratersList.setDisable(true);
                    btnR.setDisable(true);
                    root.getChildren().add(rutasList);
                    cratersList.clear();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }  
        });
    }
    
    /**
     *Retorna el contenedor raiz
     * @return
     */
    public VBox newPane()
    {
        return root;
    }   
   
    /**
     *  Este metodo se encarga de modificar el texto que ingrese el usario en los crateres para que sean identicos a los nombres
     *  del archivo
     * @param string recibe un string que es el texto obtenido del texfielf
     * @return los string modificados 
     */
    public static String capitalizeString(String string) {
        
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
        if (!found && Character.isLetter(chars[i])) {
            chars[i] = Character.toUpperCase(chars[i]);
            found = true;
        } else if (Character.isWhitespace(chars[i]) || chars[i]=='\'') { // You can add other chars here
        found = false;
            }
        }
    return String.valueOf(chars);
    }
    
    /**
     * Este metodo se encarga de recorrer la lista de crateres y eliminar espacios entre valores inneesarios
     * @param craters recibe la lista creada de crateres
     * @return la misma lista modificada
     */
    public static ArrayList limpiarCraters(ArrayList<String> craters){
        for(String c: craters){
            if(c != null && c.length()>0){
                c.trim();
            }else{
                craters.remove(c);
            } 
        }
        return craters;
    } 
            
    /**
     * Este metodo se encarga de comparar los valores en la lista ingresada de crateres con los existentes y calcula la distancia
     * que existe entre cada crater
     * @param cratersText la lista de crateres ingresados por el usuario
     * @param cratersFile el archivo procesado con objetos tipo crater
     * @return devuelve una nueva lista creada con los crateres y sus distancias
     */
    public static ArrayList<Crater> cratersDistance(ArrayList<String> cratersText, ArrayList<Crater> cratersFile){
        ArrayList<Crater> ar = new ArrayList<>();
        double latitud = 0;
        double longitud = 0;
        Coordenadas cInit = new Coordenadas(latitud,longitud);
        
        for(Crater c: cratersFile) {
              if(cratersText.contains(c.getNombre())){
                  latitud = c.getCentro().getLatitud();
                  longitud = c.getCentro().getLongitud();
                  Coordenadas c2 = new Coordenadas(c.getCentro().getLatitud(),c.getCentro().getLongitud());
                  double distancia = calcularDistanciaDosPuntos(cInit,c2);
                  Crater craterV = new Crater(c.getNombre(),c2,c.getRadio(),distancia);
                  ar.add(craterV);
                  cInit.setLatitud(latitud);
                  cInit.setLongitud(longitud);
              }
       
        }
        return ar;
    }
    
    /**
     * metodo necesario que calcula la distancia dada dos coordenadas como parametros
     * @param c1 es la coordenada inicial
     * @param c2 es la coordenada destino
     * @return un valor numerico que sera el calculo final de la distancia en kms
     */
    public static double calcularDistanciaDosPuntos(Coordenadas c1, Coordenadas c2) {

        double dlat = c2.getLatitud() - c1.getLatitud();
        double dlong = c2.getLongitud() - c1.getLongitud();
        double a = Math.pow(Math.sin(Math.toRadians(dlat) / 2),2) + 
        Math.cos(Math.toRadians(c1.getLatitud())) * 
        Math.cos(Math.toRadians(c2.getLatitud())) * 
        Math.pow(Math.sin(Math.toRadians(dlong) / 2),2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double radioMars = 3389.5;
        double d = radioMars * c;
        return d; 
    }      
}