package FrontEnd;

import DataBase.Banco_de_Valores;
import DataBase.ConectionFactory;
import allcare.Doenca;
import allcare.Sintoma;
import java.util.ArrayList;
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

public class AddDoenca {
    
    private static final String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    private static final int TEXT_FIELD_MAX_CHARS = 50;
    
    private static TextField fieldDoenca;
    private static Button buttonAddSintomas;
    private static Button buttonDescricao;
    private static Button buttonFinalizar;
    private static ArrayList<Sintoma> sintomas_escolhidos;
    private static String descricao;
    
    public static void display(ConectionFactory con, TelaDoutor tela){
        descricao = "";
        
        sintomas_escolhidos = new ArrayList<>();
        
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        //FieldDoença
        fieldDoenca = new TextField();
        fieldDoenca.setStyle(STYLE_PADRAO_FIELD);
        fieldDoenca.setPromptText("Nome");
        fieldDoenca.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        
        //ButtonAddSintomas
        buttonAddSintomas = new Button("Sintomas");
        buttonAddSintomas.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonAddSintomas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonSintomas(con);
            }
        });
        
        //ButtonAddDescrição
        buttonDescricao = new Button("Descrição");
        buttonDescricao.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonDescricao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonDescricao(con);
            }
        });
        
        //ButtonFinalizar
        buttonFinalizar = new Button("Finalizar");
        buttonFinalizar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonFinalizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonFinalizar(con, window, tela);
            }
        });
        
        //Layout components
        HBox layoutMain = new HBox();
        layoutMain.setPrefHeight(30);
        layoutMain.getChildren().addAll(fieldDoenca, buttonAddSintomas, buttonDescricao, buttonFinalizar);
        layoutMain.setSpacing(5);
        layoutMain.setAlignment(Pos.CENTER);
                
        //Scene
        Scene cena = new Scene(layoutMain);
        
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonFinalizar(con, window, tela);
                }
            }
        });
        
        //Stage
        window.getIcons().add(new Image(AddDoenca.class.getResourceAsStream("SickChan.png")));
        window.setTitle("Adicionar Doença");
        window.setScene(cena);
        window.setResizable(false);
        window.showAndWait();
    }
    
    public static void eventButtonSintomas(ConectionFactory con){
        sintomas_escolhidos = AddSintomasToDoencas.display(con, sintomas_escolhidos);
    }
    
    public static void eventButtonDescricao(ConectionFactory con){
        descricao = AdicionarDescricao.display(con, descricao);
    }
    
    public static void eventButtonFinalizar(ConectionFactory con, Stage window, TelaDoutor tela){
        //Ver se a doenca já não existe no banco
        boolean existe = false;
        for(Doenca disease : Banco_de_Valores.getAllDoencas(con.getDeclaracao_de_comandos(), con.getResult_consultas())){
            if(fieldDoenca.getText().equalsIgnoreCase(disease.getNome())){
                existe = true;
            }
        }
        
        if(existe){
            fieldDoenca.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
        }else{
            fieldDoenca.setStyle(STYLE_PADRAO_FIELD);
        }
        
        if(!existe){
            if(!fieldDoenca.getText().isEmpty() && !sintomas_escolhidos.isEmpty() && !descricao.isEmpty()){
                Doenca doenca = new Doenca();
                doenca.setNome(fieldDoenca.getText());
                doenca.setDescricao(descricao);
                Banco_de_Valores.empurra_Doenca(con.getDeclaracao_de_comandos(), doenca, sintomas_escolhidos, con.getResult_consultas());
                window.close();
                tela.atualizarLista(con);
                NovoJOptionPane.display("Doença cadastrada", true);
            }else{
                if(fieldDoenca.getText().isEmpty()){
                    fieldDoenca.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                }else{
                    fieldDoenca.setStyle(STYLE_PADRAO_FIELD);
                }
                NovoJOptionPane.display("Preencha todos os dados", false);
            }
        }else{
            fieldDoenca.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            NovoJOptionPane.display("Doença já existente na base de dados", false);
        }    
    }
    
}
