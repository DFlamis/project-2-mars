import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import Clases.BaseDatos;
import Clases.Crater;
import Clases.Rover;
import LecturaArchivos.Directory;
import LecturaArchivos.ResourcesReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Principal extends Application {

    @Override
    public void start(Stage principal)
    {
        VistaOpciones vo = new VistaOpciones();
        Scene sc = new Scene(vo.getInicio(),600,600);
        principal.setTitle("Project 2 mars");
        principal.getIcons().add(new Image( "file:"+Directory.RESOURCE_FOLDER+"/Icono G2 POO.png"));
        principal.setScene(sc);
        principal.show();
    }

    public static void main(String[] args) {
        launch();
    }

}