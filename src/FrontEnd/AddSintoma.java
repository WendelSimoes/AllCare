package FrontEnd;

import DataBase.Banco_de_Valores;
import DataBase.ConectionFactory;
import allcare.Sintoma;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddSintoma {
    
    private static final String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    private static final int TEXT_FIELD_MAX_CHARS = 50;
    
    private static TextField fieldSintoma;
    private static Button buttonAdicionar;
    
    public static void display(ConectionFactory con){
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        //fieldSintoma
        fieldSintoma = new TextField();
        fieldSintoma.setStyle(STYLE_PADRAO_FIELD);
        fieldSintoma.setPromptText("Sintoma");
        fieldSintoma.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        
        //buttonAdicionar
        buttonAdicionar = new Button("Adicionar");
        buttonAdicionar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonAdicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonAdicionar(con, window);
            }
        });
        
        //Layout components
        HBox layoutMain = new HBox();
        layoutMain.setPrefHeight(30);
        layoutMain.getChildren().addAll(fieldSintoma, buttonAdicionar);
        layoutMain.setSpacing(10);
        layoutMain.setAlignment(Pos.CENTER);
                
        //Scene
        Scene cena = new Scene(layoutMain);
        
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonAdicionar(con, window);
                }
            }
        });
        
        //Stage
        window.getIcons().add(new Image(AddSintoma.class.getResourceAsStream("SickChan.png")));
        window.setTitle("Adicionar Sintoma");
        window.setScene(cena);
        window.setResizable(false);
        window.showAndWait();
    }
    
    public static void eventButtonAdicionar(ConectionFactory con, Stage window){
        //Ver se o sintoma já não existe no banco
        boolean existe = false;
        
        for(Sintoma sintomaNoBanco : Banco_de_Valores.puxa_Sintomas(con.getDeclaracao_de_comandos(), con.getResult_consultas())){
            if(fieldSintoma.getText().equalsIgnoreCase(sintomaNoBanco.getNome())){
                existe = true;
            }
        }
        
        if(existe){
            fieldSintoma.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
        }else{
            fieldSintoma.setStyle(STYLE_PADRAO_FIELD);
        }
        
        if(!existe){
            if(!fieldSintoma.getText().isEmpty()){
                Sintoma sintoma = new Sintoma();
                sintoma.setNome(fieldSintoma.getText());
                Banco_de_Valores.empurra_Sintoma(con.getDeclaracao_de_comandos(), sintoma);
                window.close();
                NovoJOptionPane.display("Sintoma cadastrado", true);
            }else{
                NovoJOptionPane.display("Preencha o campo", false);
            }
        }else{
            fieldSintoma.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            NovoJOptionPane.display("Sintoma já existente na base de dados", false);
        }
    }
    
}
