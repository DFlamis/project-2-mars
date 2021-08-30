package Clases.PopUps;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//Panel que muestra un mensaje en caso que no exista una copia
public class NoSaves
{
    private VBox root;
    
    public NoSaves()
    {
        root = new VBox();
        Label advice = new Label("No hay archivos guardados para cargar");
        advice.setFont(new Font("Times New Roman", 14));
        Button exit = new Button("Ok");
        exit.setOnMouseClicked((e) -> {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });
        root.getChildren().addAll(advice,exit);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(12);
    }

    public VBox newAdvice()
    {
        return root;
    }
}
