package Clases.PopUps;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Saved
{
    private VBox root;
    
    public Saved()
    {
        root = new VBox();
        Label advice = new Label("Accion realizada con Exito");
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
