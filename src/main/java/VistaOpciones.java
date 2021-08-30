import java.io.IOException;

import Botones.BotonExplorar;
import Botones.BotonReporte;
import Botones.BotonRutas;
import Clases.BaseDatos;
import Clases.Crater;
import Clases.Reporte;
import Clases.Rover;
import Clases.PopUps.NoSaves;
import Clases.PopUps.Participantes;
import Clases.PopUps.Saved;
import LecturaArchivos.Directory;
import LecturaArchivos.ResourcesReader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VistaOpciones
{
    public VBox inicio;
    public HBox backup;

    public VistaOpciones()
    {
        inicio = new VBox();

        //Agregar una imagen de cabecera
        ImageView img = new ImageView(new Image("file:"+Directory.RESOURCE_FOLDER+"/Icono1 G2 POO.png"));
        img.setFitHeight(200);
        img.setFitWidth(200);

        //Label
        Label head = new Label("Project 2 Mars");
        head.setFont(new Font("Times New Roman", 30));
        Label space = new Label("");
        space.setFont(new Font(10));

        //Botones
        Button explorar = new Button("Explorar");
        Button rutas = new Button("Planificar rutas");
        Button reporte = new Button("Ver reportes");
        Button save = new Button("Guardar datos");
        Button load = new Button("Cargar datos");
        Button info = new Button("Creditos");
        Button salir = new Button("Salir");

        //Contenedor de botones para datos no volatiles
        backup = new HBox();
        backup.getChildren().addAll(save,load);
        backup.setAlignment(Pos.CENTER);
        backup.setSpacing(5);


        //Agregar botones al contenedor
        inicio.getChildren().addAll(img, head, space, explorar, rutas, reporte, backup, info, salir);

        //Centrar los botones
        inicio.setAlignment(Pos.CENTER);
        inicio.setSpacing(12);
        
        //Accion del boton explorar
        explorar.setOnMouseClicked((e) -> {
            try {
                BotonExplorar explorer = new BotonExplorar();
                Scene sc0 = new Scene (explorer.newPane(),1000,600);
                Stage explorerPane = new Stage();
                explorerPane.setTitle("Explorar");
                explorerPane.getIcons().add(new Image( "file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
                explorerPane.setScene(sc0);
                explorerPane.setScene(sc0);
                explorerPane.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        
        
        //Accion del boton Exploras Rutas
        rutas.setOnMouseClicked((e1) -> {
            
            BotonRutas btn_rutas  = new BotonRutas();
            Scene sc0 = new Scene (btn_rutas.newPane(),600,600);
            Stage rutasPane = new Stage();
            rutasPane.setTitle("Rutas");
            rutasPane.getIcons().add(new Image( "file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
            rutasPane.setScene(sc0);
            rutasPane.show();
            
        });

        //Accion del boton reporte
        reporte.setOnMouseClicked((e2) -> {
            BotonReporte start = new BotonReporte();
            Scene sc = new Scene(start.newPane(),600,600);
            Stage reportPane = new Stage();
            reportPane.getIcons().add(new Image( "file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
            reportPane.setTitle("Reportes");
            reportPane.setScene(sc);
            reportPane.show();
        });
        
        //Accion boton guardar
        save.setOnMouseClicked((e3) -> {
            ResourcesReader.toSave();
            
            Saved sv = new Saved();
            Scene sc = new Scene(sv.newAdvice(),300,200);
            Stage nos = new Stage();
            nos.setTitle("Operacion exitosa");
            nos.getIcons().add(new Image( "file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
            nos.setScene(sc);
            nos.show();

        });

        //Accion boton cargar
        load.setOnMouseClicked((e4) -> {
            try
            {
                ResourcesReader.loadSaved();

                Saved sv = new Saved();
                Scene sc = new Scene(sv.newAdvice(),300,200);
                Stage nos = new Stage();
                nos.setTitle("Operacion exitosa");
                nos.getIcons().add(new Image("file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
                nos.setScene(sc);
                nos.show();    
            }
            catch(IOException e5)
            {
                NoSaves ns = new NoSaves();
                Scene sc = new Scene(ns.newAdvice(),300,200);
                Stage nos = new Stage();
                nos.setTitle("Operacion fallida");
                nos.getIcons().add(new Image("file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
                nos.setScene(sc);
                nos.show();
    
            }
        });

        //Accion boton creditos
        info.setOnMouseClicked((e6) -> {            
            Participantes sv = new Participantes();
            Scene sc = new Scene(sv.newIntegrantes(),300,200);
            Stage nos = new Stage();
            nos.setTitle("Integrantes");
            nos.getIcons().add(new Image("file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
            nos.setScene(sc);
            nos.show();
        });

        //Accion boton cerrar
        salir.setOnMouseClicked((e7) -> {
            Stage stage = (Stage) salir.getScene().getWindow();
            stage.close();
        });
    }
    
    public VBox getInicio()
    {
        return inicio;
    }
}