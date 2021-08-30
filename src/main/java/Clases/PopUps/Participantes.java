package Clases.PopUps;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Participantes
{
    private VBox root;

    public Participantes()
    {
        root = new VBox();

        Label integrantes = new Label("Integrantes: ");
        integrantes.setFont(new Font("Times New Roman", 25));
        Label space = new Label("");
        Label rocio = new Label("Rocio Isabel Chavez Lucio");
        rocio.setFont(new Font("Times New Roman", 18));
        Label ivan = new Label("Jefferson Ivan Vega Sarango");
        ivan.setFont(new Font("Times New Roman", 18));
        Label derek = new Label("Derek Daniel Aviles Bastidas");
        derek.setFont(new Font("Times New Roman", 18));

        root.getChildren().addAll(integrantes, space, rocio, ivan, derek);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(12);
    }

    public VBox newIntegrantes()
    {
        return root;
    }
}
