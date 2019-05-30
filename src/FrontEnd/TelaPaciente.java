package FrontEnd;

import DataBase.Banco_de_Valores;
import DataBase.ConectionFactory;
import allcare.Doenca;
import allcare.Sintoma;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TelaPaciente {
    
    private final String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    private final int TEXT_FIELD_MAX_CHARS = 50;
    
    private ArrayList<Sintoma> todosSintomas;
    private ArrayList<Sintoma> sintomas_escolhidos;
    private ArrayList<Doenca> todasDoencas;
    private VBox layoutCheckBoxs;
    private ScrollPane scrollSintomas;
    private ListView<String> listDoencas;
    private TextField fieldSearch;
    ArrayList<CheckBox> boxs;
    
    public void display(Stage windowLogin, ConectionFactory con){
        sintomas_escolhidos = new ArrayList<>();
        
        //Todos sintomas
        todosSintomas = new ArrayList<>();
        todosSintomas = Banco_de_Valores.puxa_Sintomas(con.getDeclaracao_de_comandos(), con.getResult_consultas());
        
        //Todas doencas
        todasDoencas = new ArrayList<>();
        todasDoencas = Banco_de_Valores.getAllDoencas(con.getDeclaracao_de_comandos(), con.getResult_consultas());
        
        //Criar checkboxs com sintomas
        boxs = new ArrayList<>();
        for(Sintoma todos : todosSintomas){
            CheckBox box = new CheckBox(todos.getNome());
            VBox.setMargin(box, new Insets(2, 0, 0, 2));
            box.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    atualizarListaDoencas(con);
                }
            });
                boxs.add(box);
        }
        
        //Stage
        Stage windowPaciente = new Stage();
        windowPaciente.initModality(Modality.APPLICATION_MODAL);
        
        //Carregar checkBoxs sintomas + ação de atualização
        layoutCheckBoxs = new VBox();
        layoutCheckBoxs.setSpacing(3);
        layoutCheckBoxs.setFocusTraversable(false);
        layoutCheckBoxs.setStyle("-fx-font-size: 19");
        atualizarListaSintomas(con);
               
        //Scroll sintomas
        scrollSintomas = new ScrollPane(layoutCheckBoxs);
        scrollSintomas.setFocusTraversable(false);
        scrollSintomas.setPrefHeight(430);
        scrollSintomas.setStyle("-fx-opacity: 0.7; -fx-font-size: 20");
        
        //Label sintomas
        Label labelSintomas = new Label("Sintomas:");
        labelSintomas.setStyle("-fx-text-fill: #aa0a0a; -fx-font-size: 20; -fx-font-weight: bold");
        
        //layout search
        HBox layoutSearch = new HBox();
        layoutSearch.setSpacing(5);
        VBox.setMargin(layoutSearch, new Insets(0, 0, 2, 0));
        layoutSearch.getChildren().add(new ImageView(new Image(TelaPaciente.class.getResourceAsStream("SearchIcon.png"))));
        fieldSearch = new TextField();
        fieldSearch.setStyle(STYLE_PADRAO_FIELD);
        fieldSearch.setPromptText("Pesquisar");
        fieldSearch.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        fieldSearch.setPrefWidth(210);
        //Adiciona pesquisa de dados
        fieldSearch.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                pesquisarSintomas(con);
            }
        });
        layoutSearch.getChildren().add(fieldSearch);
        
        //LayoutLeft
        VBox layoutLeft = new VBox();
        layoutLeft.setFocusTraversable(false);
        layoutLeft.setAlignment(Pos.CENTER);
        layoutLeft.setPrefWidth(210);
        BorderPane.setAlignment(layoutLeft, Pos.CENTER_LEFT);
        BorderPane.setMargin(layoutLeft, new Insets(0, 0, 0, 5));
        layoutLeft.getChildren().addAll(labelSintomas, layoutSearch, scrollSintomas);
        
        //Label doenças
        Label labelDoencas = new Label("Doenças possíveis:");
        labelDoencas.setStyle("-fx-text-fill: #aa0a0a; -fx-font-size: 20; -fx-font-weight: bold");
        
        //Lista doenças
        listDoencas = new ListView<>();
        listDoencas.setPrefHeight(430);
        listDoencas.setStyle("-fx-opacity: 0.7; -fx-font-size: 19");
        listDoencas.setFocusTraversable(false);
        listDoencas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listDoencas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(!listDoencas.getSelectionModel().getSelectedItems().isEmpty()){
                    descreverDoenca(con);
                }
            }
        });
        
        //LayoutRight
        VBox layoutRight = new VBox();
        layoutRight.setAlignment(Pos.CENTER);
        layoutRight.setPrefWidth(210);
        BorderPane.setAlignment(layoutRight, Pos.CENTER_RIGHT);
        BorderPane.setMargin(layoutRight, new Insets(0, 5, 0, 0));
        layoutRight.getChildren().addAll(labelDoencas, listDoencas);
        
        //Border Layout
        BorderPane layoutMain = new BorderPane();
        layoutMain.setLeft(layoutLeft);
        layoutMain.setRight(layoutRight);
        layoutMain.setId("pane");
        
        //Scene
        Scene cena = new Scene(layoutMain, 430, 430);
        cena.getStylesheets().addAll(TelaPaciente.class.getResource("StylePaciente.css").toExternalForm());
        cena.getStylesheets().addAll(TelaPaciente.class.getResource("StyleListView.css").toExternalForm());
        cena.getStylesheets().addAll(TelaPaciente.class.getResource("StyleCheckBox.css").toExternalForm());
        
        //Stage
        windowLogin.hide();
        
        windowPaciente.getIcons().add(new Image(TelaPaciente.class.getResourceAsStream("SickChan.png")));
        windowPaciente.setTitle("Patient Home");
        windowPaciente.setScene(cena);
        windowPaciente.setResizable(false);
        windowPaciente.show();
        
        NovoJOptionPane.display("Login bem sucedido", true);
        
        windowPaciente.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                windowLogin.show();
            }
        });
    }
    
    public void atualizarListaSintomas(ConectionFactory con){
        layoutCheckBoxs.getChildren().setAll(boxs);
    }
    
    public void atualizarListaDoencas(ConectionFactory con){
        listDoencas.setDisable(false);
        
        ArrayList<String> sintomas = new ArrayList<>();
        for(CheckBox box : boxs){
            if(box.isSelected()){
                sintomas.add(box.getText());
            }else{
                sintomas.remove(box.getText());
            }
        }
        
        sintomas_escolhidos.clear();
        for(String nome : sintomas){
            for(Sintoma sintomax : todosSintomas){
                if(sintomax.getNome().equalsIgnoreCase(nome)){
                    sintomas_escolhidos.add(sintomax);
                }
            }
        }
        
        ArrayList<Doenca> doencas = new ArrayList<>();
        doencas = Banco_de_Valores.procura_Doencas(sintomas_escolhidos, con.getDeclaracao_de_comandos(), con.getResult_consultas());
        ArrayList<String> nomeDoencas = new ArrayList<>();
        for(Doenca doenca : doencas){
            nomeDoencas.add(doenca.getNome());
        }
        listDoencas.getItems().setAll(nomeDoencas);
        if(listDoencas.getItems().isEmpty()){
            listDoencas.getItems().add("Nada encontrado!");
            listDoencas.setDisable(true);
        }
    }
    
    public void pesquisarSintomas(ConectionFactory con){
        atualizarListaSintomas(con);
        if(fieldSearch.getText().isEmpty()){
            atualizarListaSintomas(con);
        }else{
            for(CheckBox box : boxs){
                if(!box.getText().toLowerCase().startsWith(fieldSearch.getText().toLowerCase()) || !box.getText().toUpperCase().startsWith(fieldSearch.getText().toUpperCase())){
                    layoutCheckBoxs.getChildren().remove(box);
                }
            }
        }
        if(layoutCheckBoxs.getChildren().isEmpty()){
            fieldSearch.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
        }else{
            fieldSearch.setStyle(STYLE_PADRAO_FIELD);
        }
    }
    
    public void descreverDoenca(ConectionFactory con){
        String doencaNome = listDoencas.getSelectionModel().getSelectedItem();
        Doenca doenca = new Doenca();
        for(Doenca doencax : todasDoencas){
            if(doencax.getNome().equalsIgnoreCase(doencaNome)){
                doenca = doencax;
            }
        }
        MostrarDescricaoDoenca.display(con, doenca);
        listDoencas.getSelectionModel().clearSelection();
    }
    
}
