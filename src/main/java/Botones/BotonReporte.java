package Botones;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import Clases.BaseDatos;
import Clases.Reporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BotonReporte
{
    //Contenedores
    private VBox root;
    private HBox top;
    private VBox MidTop;
    private HBox Mid;
    private HBox MidBot;
    private HBox Bot;
    private TableView<Reporte> reports;

    //Specials
    private TableColumn<Reporte, String> sFecha;
    private TableColumn<Reporte, String> nombre;
    private TableColumn<Reporte, String> mineral;

    public BotonReporte()
    {
        root = new VBox();
        root.setSpacing(5);
        BaseDatos.shareData();

        showBack();
        showLabelsAdvice();
        showAskDate();
        ShowAskName();
    }

    //Retorno
    public VBox newPane()
    {
        return root;
    }
    
    //Boton Regresar
    public void showBack()
    {
        top = new HBox();
        Button back = new Button("Regresar");
        back.setOnMouseClicked((e) -> {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
        });
        top.getChildren().addAll(back);
        root.getChildren().addAll(top);
    }

    //Labels
    public void showLabelsAdvice()
    {
        MidTop = new VBox();
        Label formato = new Label("Formato de fecha: Day - Month - Year");
        Label advice = new Label("Se recomienda llenar los cuadros en ese orden para evitar conflictos y al final escriba algo en el recuadro");
        MidTop.getChildren().addAll(formato,advice);
        MidTop.setAlignment(Pos.CENTER);
        root.getChildren().addAll(MidTop);
    }

    //Perdi datos de fecha
    public void showAskDate()
    {
        Mid = new HBox();
        Label fechaI = new Label("Fecha inicial: ");

        //TextFields de fechas
        TextField fechaID = new TextField("1");
        TextField fechaIM = new TextField("1");
        TextField fechaIY = new TextField("1");
        
        //Textfields formatos
        fechaID.setMaxWidth(40);
        fechaIM.setMaxWidth(40);
        fechaIY.setMaxWidth(50);
        
        //TextFields eventos
        fechaID.textProperty().addListener((eD) -> {
            try
            {
                Integer.parseInt(fechaID.getText());
                if(Integer.parseInt(fechaID.getText()) <= 0)
                {
                    fechaID.setText("01");
                }
                else if(Integer.parseInt(fechaID.getText()) >= 32)
                {
                    fechaID.setText("31");
                }

                //Cambiar mes para evitar problemas
                if( Integer.parseInt(fechaID.getText()) == 31)
                {
                    fechaIM.setText("01");
                }

                if(Integer.parseInt(fechaID.getText()) == 29 & Integer.parseInt(fechaIM.getText()) == 2)
                {
                    fechaIY.setText("2000");
                }

                //dia mayor a 30 con mes 2 y year biciesto -> dia sera = 29
                if(Integer.parseInt(fechaIM.getText()) == 2 & isLeapYear(Integer.parseInt(fechaIY.getText())) & Integer.parseInt(fechaID.getText()) >= 30 )
                {
                    fechaID.setText("29");
                }
            }
            catch(Exception eD1)
            {
                fechaID.setText("01");
            }
        });

        //Guion 1
        Label guionA = new Label("-");

        fechaIM.textProperty().addListener((eM) -> {
            try
            {
                Integer.parseInt(fechaIM.getText());
                if(Integer.parseInt(fechaIM.getText()) <= 0)
                {
                    fechaIM.setText("01");
                }
                else if(Integer.parseInt(fechaIM.getText()) >= 13)
                {
                    fechaIM.setText("12");
                }

                //Si tiene 30 dias, cambiar el dia
                if(Arrays.asList(BaseDatos.monthShort).contains(Integer.parseInt(fechaIM.getText())))
                {
                    if(fechaID.getText().equals("31"))
                    {
                        fechaID.setText("30");
                    }
                }

                //Validar si se refiere a un year biciesto
                if(Integer.parseInt(fechaID.getText()) > 29 & Integer.parseInt(fechaIM.getText()) == 2)
                {
                    fechaIY.setText("2000");
                }

            }
            catch(Exception eM1)
            {
                fechaIM.setText("01");
            }
        });

        //Guion 2
        Label guionB = new Label("-");
        fechaIY.textProperty().addListener((eY) -> {
            try
            {
                Integer.parseInt(fechaIY.getText());
                if(Integer.parseInt(fechaIY.getText()) <= 0)
                {
                    //Si admite negativos pero no es realista
                    fechaIY.setText("0");
                }
                else if(Integer.parseInt(fechaIY.getText()) > 99999)
                {
                    //Admite mayores pero aun ni la decada se termina asi que...
                    fechaIY.setText("99998");
                }

                if(isLeapYear(Integer.parseInt(fechaIY.getText())))
                {
                    if(Integer.parseInt(fechaID.getText()) >= 30 & Integer.parseInt(fechaIM.getText()) == 2)
                    {
                        fechaID.setText("29");
                    }
                }
                else
                {
                    if(Integer.parseInt(fechaID.getText()) >= 29 & Integer.parseInt(fechaIM.getText()) == 2)
                    {
                        fechaID.setText("28");
                    }
                }
            }
            catch(Exception eY1)
            {
                fechaIY.setText("1");
            }
        });

        Label fechaF = new Label("  ->    Fecha final: ");

        //TextFields fechas 2
        TextField fechaFD = new TextField("1");
        TextField fechaFM = new TextField("1");
        TextField fechaFY = new TextField("10");

        //TextFields formatos 2
        fechaFD.setMaxWidth(40);
        fechaFM.setMaxWidth(40);
        fechaFY.setMaxWidth(50);

        //TextFields eventos 2
        fechaFD.textProperty().addListener((eDD) -> {
            try
            {
                Integer.parseInt(fechaFD.getText());
                if(Integer.parseInt(fechaFD.getText()) <= 0)
                {
                    fechaFD.setText("01");
                }
                else if(Integer.parseInt(fechaFD.getText()) >= 32)
                {
                    fechaFD.setText("31");
                }

                //Cambiar mes para evitar problemas
                if( Integer.parseInt(fechaFD.getText()) == 31)
                {
                    fechaFM.setText("01");
                }

                if(Integer.parseInt(fechaFD.getText()) == 29 & Integer.parseInt(fechaFM.getText()) == 2)
                {
                    fechaFY.setText("2000");
                }

                //dia mayor a 30 con mes 2 y year biciesto -> dia sera = 29
                if(Integer.parseInt(fechaFM.getText()) == 2 & isLeapYear(Integer.parseInt(fechaFY.getText())) & Integer.parseInt(fechaFD.getText()) >= 30 )
                {
                    fechaFD.setText("29");
                }
            }
            catch(Exception eDD1)
            {
                fechaFD.setText("01");
            }
        });

        //Guion 3
        Label guionC = new Label("-");

        fechaFM.textProperty().addListener((eMM) -> {
            try
            {
                Integer.parseInt(fechaFM.getText());
                if(Integer.parseInt(fechaFM.getText()) <= 0)
                {
                    fechaFM.setText("01");
                }
                else if(Integer.parseInt(fechaFM.getText()) >= 13)
                {
                    fechaFM.setText("12");
                }

                //Si tiene 30 dias, cambiar el dia
                if(Arrays.asList(BaseDatos.monthShort).contains(Integer.parseInt(fechaFM.getText())))
                {
                    if(fechaFD.getText().equals("31"))
                    {
                        fechaFD.setText("30");
                    }
                }

                //Validar si se refiere a un year biciesto
                if(Integer.parseInt(fechaFD.getText()) > 29 & Integer.parseInt(fechaFM.getText()) == 2)
                {
                    fechaFY.setText("2000");
                }

            }
            catch(Exception eMM1)
            {
                fechaFM.setText("01");
            }
        });

        //Guion 4
        Label guionD = new Label("-");
        //fix
        fechaFY.textProperty().addListener((eYY) -> {
            try
            {
                Integer.parseInt(fechaFY.getText());
                if(Integer.parseInt(fechaFY.getText()) <= 0)
                {
                    //Si admite negativos pero no es realista
                    fechaFY.setText("0");
                }
                else if(Integer.parseInt(fechaFY.getText()) > 99999)
                {
                    //Admite mayores pero aun ni la decada se termina asi que...
                    fechaFY.setText("99998");
                }

                if(isLeapYear(Integer.parseInt(fechaFY.getText())))
                {
                    if(Integer.parseInt(fechaFD.getText()) >= 30 & Integer.parseInt(fechaFM.getText()) == 2)
                    {
                        fechaFD.setText("29");
                    }
                }
                else
                {
                    if(Integer.parseInt(fechaFD.getText()) >= 29 & Integer.parseInt(fechaFM.getText()) == 2)
                    {
                        fechaFD.setText("28");
                    }
                }
            }
            catch(Exception eYY1)
            {
                fechaFY.setText("1");
            }
        });

        //Fue la unica solucion que encontre para que no me cree el recuadro de aplicar filtros a cada rato
        Label testoL = new Label(" -> ");
        TextField testo = new TextField();
        testo.setMaxWidth(20);
        testo.textProperty().addListener((ts) -> {
            showSearch(Integer.parseInt(fechaID.getText()), Integer.parseInt(fechaIM.getText()), Integer.parseInt(fechaIY.getText()), Integer.parseInt(fechaFD.getText()), Integer.parseInt(fechaFM.getText()), Integer.parseInt(fechaFY.getText()));
            testo.setDisable(true);
        });


        Mid.getChildren().addAll(fechaI,fechaID,guionA,fechaIM,guionB,fechaIY,fechaF,fechaFD,guionC,fechaFM,guionD,fechaFY,testoL,testo);
        Mid.setAlignment(Pos.CENTER);
        root.getChildren().addAll(Mid);

    }

    //Boton buscar
    public void showSearch(int ID, int IM, int IY, int FD, int FM, int FY)
    {
        MidBot = new HBox();
        Button search = new Button("Aplicar rango");
        search.setOnMouseClicked((e) -> {

            //Convertir de String a fecha
            String sf1 = ID+"/"+IM+"/"+IY;
            LocalDate f1 = LocalDate.parse(sf1, DateTimeFormatter.ofPattern("d/M/y"));

            String sf2 = FD+"/"+FM+"/"+FY;
            LocalDate f2 = LocalDate.parse(sf2, DateTimeFormatter.ofPattern("d/M/y"));
            
            ArrayList<Reporte> reportesNetos = applyFilter(f1, f2, BaseDatos.reporteData);
            BaseDatos.reportsFiltered.clear();
            for(Reporte r: reportesNetos)
            {
                System.out.println("r");
                BaseDatos.reportsFiltered.add(r);
            }
            root.getChildren().clear();
            showBack();
            showLabelsAdvice();
            showAskDate();
            ShowAskName();
        });
        MidBot.getChildren().addAll(search);
        MidBot.setAlignment(Pos.CENTER);
        root.getChildren().addAll(MidBot);
    }

    //Pedir mineral o nombre crater
    public void ShowAskName()
    {
        Bot = new HBox();
        Label texto = new Label("Buscar por fecha o nombre o mineral: ");
        TextField addMineral = new TextField();
        Bot.getChildren().addAll(texto,addMineral);
        Bot.setAlignment(Pos.CENTER);
        root.getChildren().addAll(Bot);
        ShowTable(addMineral);
    }

    //Table view
    public void ShowTable(TextField addMineral)
    {
        reports = new TableView<>();
        Label empty = new Label("   Aun no hay reportes que mostrar\n¡Prueba explorando algunos crateres!\n           O carga una copia local");
        empty.setAlignment(Pos.CENTER);
        reports.setPlaceholder(empty);
        TableColumn<Reporte, String> dateE = new TableColumn<Reporte, String>("Fecha Exploracion");
        dateE.setMinWidth(150);
        dateE.setCellValueFactory(new PropertyValueFactory<Reporte, String>("sFecha"));

        TableColumn<Reporte, String> name = new TableColumn<>("Nombre");
        name.setMinWidth(80);
        name.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Reporte, String> mineral = new TableColumn<>("Minerales");
        mineral.setCellValueFactory(new PropertyValueFactory<>("mineral"));

        ObservableList<Reporte> tr = FXCollections.observableArrayList(BaseDatos.reportsFiltered);
        reports.getColumns().addAll(Arrays.asList(dateE,name,mineral));

        //Filtrar nombre del crater y minerales
        //Recibe una lista y un parametro para la comparacion
        FilteredList<Reporte> listaFiltrada = new FilteredList<>(tr, b -> true);
        
        //Recibe un String y retorna un valor de verdad segun la coincidencia
        addMineral.textProperty().addListener((Observable, oldValue, newValue) -> {
            listaFiltrada.setPredicate(reporte -> {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //Evitar conflictos con las mayusculas y minusculas
                String minuscula = newValue.toLowerCase();

                //Filtrar nombres
                if(reporte.getNombre().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //filtra minerales
                else if(reporte.getMineral().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //Filtrar fecha especifica
                else if(reporte.getSFecha().toLowerCase().indexOf(minuscula) != -1)
                {
                    return true;
                }

                //Si no hay coincidencias
                else
                {
                    return false;
                }
            });           
        });

        //Convertirlos en un sortedList para utilizar comparatorProperty()
        SortedList<Reporte> listaOrdenada = new SortedList<>(listaFiltrada);

        //Relacionar los comparadores
        listaOrdenada.comparatorProperty().bind(reports.comparatorProperty());

        //Agregar los elementos al table view
        reports.setItems(listaOrdenada);

        // reports.getColumns().addAll(Arrays.asList(dateE, name, mineral));
        root.getChildren().addAll(reports);
    }

    //Validar si es biciesto
    public boolean isLeapYear(int year)
    {
        if(year % 4 == 0)
        {
            if(year % 400 == 0 || year % 100 == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    //Filtrar
    public ArrayList<Reporte> applyFilter(LocalDate f1, LocalDate f2, ArrayList<Reporte> rep)
    {
        Reporte r = new Reporte();
        //Si f1 es menor a f2
        if(f1.isBefore(f2))
        {
            System.out.println("f1 - f2");
            return r.filtrarFecha(f1, f2, rep);
        }
        //Si f2 es menor a f1
        else if(f2.isBefore(f1))
        {
            System.out.println("f2 - f1");
            return r.filtrarFecha(f2, f1, rep);
        }
        else
        {
            return rep;
        }
    }
}
