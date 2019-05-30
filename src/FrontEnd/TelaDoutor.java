package FrontEnd;

import DataBase.Banco_de_Valores;
import DataBase.ConectionFactory;
import allcare.Doenca;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TelaDoutor {
    
    private ListView<String> listDoencas;
    private Button buttonApagar, buttonAdd, buttonEdit, buttonAddSintoma;
    private BorderPane layoutRight;
    
    public void display(Stage windowLogin, ConectionFactory con, TelaDoutor tela){
        //Stage
        Stage windowDoutor = new Stage();
        windowDoutor.initModality(Modality.APPLICATION_MODAL);
        
        //Lista doenças
        listDoencas = new ListView<String>();
        atualizarLista(con);
        BorderPane.setAlignment(listDoencas, Pos.CENTER_LEFT);
        listDoencas.setPrefWidth(200);
        listDoencas.setStyle("-fx-opacity: 0.7; -fx-font-size: 19");
        listDoencas.setFocusTraversable(false);
        listDoencas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //Ação para tornar enable o botão edit caso haja algo selecionado na lista de doenças
        listDoencas.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(listDoencas.getSelectionModel().getSelectedItems().isEmpty()){
                    buttonEdit.setDisable(true);
                    buttonApagar.setDisable(true);
                }else{
                    buttonEdit.setDisable(false);
                    buttonApagar.setDisable(false);
                }
            }
        });
        
        //Button apagar doenca
        buttonApagar = new Button("Apagar");
        buttonApagar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonApagar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Banco_de_Valores.deletarDoenca(con.getDeclaracao_de_comandos(), con.getResult_consultas(), listDoencas.getSelectionModel().getSelectedItem());
                buttonEdit.setDisable(true);
                buttonApagar.setDisable(true);
                atualizarLista(con);
            }
        });
        buttonApagar.setDisable(true);
        //
        
        //Button add
        buttonAdd = new Button("Adicionar doença");
        buttonAdd.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonAdd(con, tela);
            }
        });
        
        //Button edit
        buttonEdit = new Button("Editar");
        buttonEdit.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");        
        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonEdit(con, tela);
            }
        });
        buttonEdit.setDisable(true);
        //
        
        //Layout buttons
        HBox layoutButtons = new HBox();
        layoutButtons.setSpacing(5);
        layoutButtons.getChildren().addAll(buttonAdd, buttonApagar, buttonEdit);
        BorderPane.setAlignment(layoutButtons, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(layoutButtons, new Insets(0, 3, 2, 0));
        
        //Button addSintomas
        buttonAddSintoma = new Button("Adicionar sintoma");
        buttonAddSintoma.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonAddSintoma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonAddSintoma(con);
            }
        });
        
        //Button apagarSintomas
        Button buttonApagarSintomas = new Button("Apagar sintomas");
        buttonApagarSintomas.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonApagarSintomas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonApagarSintomas(con);
            }
        });
        
        //Layout buttonsSintomas
        HBox layoutButtonsSintomas = new HBox();
        layoutButtonsSintomas.setSpacing(5);
        layoutButtonsSintomas.setAlignment(Pos.TOP_RIGHT);
        layoutButtonsSintomas.getChildren().addAll(buttonAddSintoma, buttonApagarSintomas);
        BorderPane.setAlignment(layoutButtonsSintomas, Pos.TOP_RIGHT);
        BorderPane.setMargin(layoutButtonsSintomas, new Insets(2, 3, 0, 0));
        
        
        //Border layout main/right
        layoutRight = new BorderPane();
        layoutRight.setBottom(layoutButtons);
        layoutRight.setTop(layoutButtonsSintomas);
        
        //Border Layout main
        BorderPane layoutMain = new BorderPane();
        layoutMain.setLeft(listDoencas);
        layoutMain.setRight(layoutRight);
        layoutMain.setId("pane");
        
        //Scene
        Scene cena = new Scene(layoutMain, 430, 430);
        cena.getStylesheets().addAll(TelaDoutor.class.getResource("StyleDoutor.css").toExternalForm());
        cena.getStylesheets().addAll(TelaDoutor.class.getResource("StyleListView.css").toExternalForm());
        
        //Stage
        windowLogin.hide();
        
        windowDoutor.getIcons().add(new Image(TelaDoutor.class.getResourceAsStream("SickChan.png")));
        windowDoutor.setTitle("Doctor Home");
        windowDoutor.setScene(cena);
        windowDoutor.setResizable(false);
        windowDoutor.show();
        
        NovoJOptionPane.display("Login bem sucedido", true);
        
        //Ação para fechar a janela e retornar para a tela de login
        windowDoutor.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                windowLogin.show();
            }
        });
    }
    
    //Atualiza a lista de doenças já cadastradas
    public void atualizarLista(ConectionFactory con){
        ArrayList<Doenca> doencas = new ArrayList<>();
        ArrayList<String> doencasNome = new ArrayList<>();
        doencas = Banco_de_Valores.getAllDoencas(con.getDeclaracao_de_comandos(), con.getResult_consultas());
        for(Doenca doencax : doencas){
            doencasNome.add(doencax.getNome());
        }
        listDoencas.getItems().setAll(doencasNome);
    }
    
    //Ação para abrir a minitela para adicionar sintoma
    public void eventButtonAddSintoma(ConectionFactory con){
        listDoencas.getSelectionModel().clearSelection();
        buttonEdit.setDisable(true);
        buttonApagar.setDisable(true);
        AddSintoma.display(con);
    }
    
    public void eventButtonApagarSintomas(ConectionFactory con){
        ApagarSintomas.display(con);
    }
    
    //Açao para adicionar uma doença e seus sintomas
    public void eventButtonAdd(ConectionFactory con, TelaDoutor tela){
        listDoencas.getSelectionModel().clearSelection();
        
        buttonEdit.setDisable(true);
        buttonApagar.setDisable(true);
        
        AddDoenca.display(con, tela);
    }
    
    //Ação para abrir a lista de opções após o botão edit
    public void eventButtonEdit(ConectionFactory con, TelaDoutor tela){
        String doencaSelecionada = listDoencas.getSelectionModel().getSelectedItem();
        
        TelaEdit.display(con, doencaSelecionada, tela, buttonApagar, buttonEdit);
    }
    
}