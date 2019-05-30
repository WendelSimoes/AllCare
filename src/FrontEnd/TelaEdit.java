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

public class TelaEdit {
    
    private static final String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    
    private static final int TEXT_FIELD_MAX_CHARS = 50;
    
    private static TextField fieldNomeDoenca;
    private static Button buttonSintomas;
    private static Button buttonDescricao;
    private static Button buttonFinalizar;
    private static ArrayList<Sintoma> sintomas_escolhidos;
    private static ArrayList<Doenca> todas_doencas;
    private static String descricao;
    
    public static void display(ConectionFactory con, String doencaSelecionada, TelaDoutor tela, Button btnApagar, Button btnEdit){
        descricao = "";
        
        sintomas_escolhidos = new ArrayList<>();
        ArrayList<String> sintomasPreEstabelecidos = Banco_de_Valores.sintomas_de_doenca_especifica(con.getDeclaracao_de_comandos(), con.getResult_consultas(), doencaSelecionada);
        
        todas_doencas = new ArrayList<>();
        todas_doencas = Banco_de_Valores.getAllDoencas(con.getDeclaracao_de_comandos(), con.getResult_consultas());
        for(Doenca doencax : todas_doencas){
            if(doencax.getNome().equalsIgnoreCase(doencaSelecionada)){
                descricao = doencax.getDescricao();
            }
        }
        
        for(String nome : sintomasPreEstabelecidos){
            Sintoma sintoma = new Sintoma();
            sintoma.setNome(nome);
            
            sintomas_escolhidos.add(sintoma);
        }
        
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        //FieldNomeDoença
        fieldNomeDoenca = new TextField();
        fieldNomeDoenca.setStyle(STYLE_PADRAO_FIELD);
        fieldNomeDoenca.setPromptText("Nome");
        fieldNomeDoenca.setText(doencaSelecionada);
        fieldNomeDoenca.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        
        //ButtonMudarSintomas
        buttonSintomas = new Button("Sintomas");
        buttonSintomas.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonSintomas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonSintomas(con, doencaSelecionada);
            }
        });
        
        //ButtonDescricao
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
                eventButtonFinalizar(con, window, doencaSelecionada, tela, btnApagar, btnEdit);
            }
        });
        
        //Layout components
        HBox layoutMain = new HBox();
        layoutMain.setPrefHeight(30);
        layoutMain.getChildren().addAll(fieldNomeDoenca, buttonSintomas, buttonDescricao, buttonFinalizar);
        layoutMain.setSpacing(5);
        layoutMain.setAlignment(Pos.CENTER);
                
        //Scene
        Scene cena = new Scene(layoutMain);
        
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonFinalizar(con, window, doencaSelecionada, tela, btnApagar, btnEdit);
                }
            }
        });
        
        //Stage
        window.getIcons().add(new Image(TelaEdit.class.getResourceAsStream("SickChan.png")));
        window.setTitle("Editar Doença");
        window.setScene(cena);
        window.setResizable(false);
        window.showAndWait();
    }
    
    public static void eventButtonSintomas(ConectionFactory con, String doencaSelecionada){
        sintomas_escolhidos = MudarSintomasToDoencas.display(con, doencaSelecionada, sintomas_escolhidos);
    }
    
    public static void eventButtonDescricao(ConectionFactory con){
        descricao = AdicionarDescricao.display(con, descricao);
    }
    
    public static void eventButtonFinalizar(ConectionFactory con, Stage window, String doencaSelecionada, TelaDoutor tela, Button btnApagar, Button btnEdit){
        //Ver se a doenca já não existe no banco
        boolean existe = false;
        for(Doenca disease : Banco_de_Valores.getAllDoencas(con.getDeclaracao_de_comandos(), con.getResult_consultas())){
            if(fieldNomeDoenca.getText().equalsIgnoreCase(disease.getNome())){
                existe = true;
            }
        }
        if(fieldNomeDoenca.getText().equalsIgnoreCase(doencaSelecionada)){
            existe = false;
        }
        
        if(existe){
            fieldNomeDoenca.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
        }else{
            fieldNomeDoenca.setStyle(STYLE_PADRAO_FIELD);
        }
        
        if(!existe){
            if(!fieldNomeDoenca.getText().isEmpty() && !sintomas_escolhidos.isEmpty() && !descricao.isEmpty()){
                Banco_de_Valores.modifica_Sintomas_e_nome_da_Doenca(con.getDeclaracao_de_comandos(), con.getResult_consultas(), doencaSelecionada, sintomas_escolhidos, fieldNomeDoenca.getText(), descricao);
                window.close();
                tela.atualizarLista(con);
                btnApagar.setDisable(true);
                btnEdit.setDisable(true);
                NovoJOptionPane.display("Doença modificada", true);
            }else{
                if(fieldNomeDoenca.getText().isEmpty()){
                    fieldNomeDoenca.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                }else{
                    fieldNomeDoenca.setStyle(STYLE_PADRAO_FIELD);
                }
                NovoJOptionPane.display("Preencha todos os dados", false);
            }
        }else{
            NovoJOptionPane.display("Nome já utilizado", false);
        }    
    }
    
}
