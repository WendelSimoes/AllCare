package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DoencaView extends HBox{
 
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Common nodes
    private TextField fieldDoenca;
    private Button buttonAddSintomas;
    private Button buttonDescricao;
    private Button buttonFinalizar;
    
    public DoencaView(){
        window = new Stage();
        
        //WorkArea
            //FieldDoença
            fieldDoenca = new TextField();
            fieldDoenca.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldDoenca.setPromptText("Nome");
            fieldDoenca.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));

            //ButtonAddSintomas
            buttonAddSintomas = new Button("Sintomas");
            buttonAddSintomas.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());

            //ButtonAddDescrição
            buttonDescricao = new Button("Descrição");
            buttonDescricao.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());

            //ButtonFinalizar
            buttonFinalizar = new Button("Finalizar");
            buttonFinalizar.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
        //
            
        //Adicionando componentes
        this.setPrefHeight(30);
        this.getChildren().addAll(fieldDoenca, buttonAddSintomas, buttonDescricao, buttonFinalizar);
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);  
        //
        
        //Scene
        cena = new Scene(this);
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Doença");
        window.setResizable(false);
        window.setScene(cena);
    }
    
    //Add listeners
    public void addButtonAddSintomasListener(EventHandler<ActionEvent> eventHandler){
        buttonAddSintomas.setOnAction(eventHandler);
    }
    
    public void addButtonDescricaoListener(EventHandler<ActionEvent> eventHandler){
        buttonDescricao.setOnAction(eventHandler);
    }
    
    public void addButtonFinalizarListener(EventHandler<ActionEvent> eventHandler){
        buttonFinalizar.setOnAction(eventHandler);
    }
    
    public void addSceneListener(EventHandler<KeyEvent> eventHandler){
        cena.setOnKeyPressed(eventHandler);
    }
    //

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Scene getCena() {
        return cena;
    }

    public void setCena(Scene cena) {
        this.cena = cena;
    }

    public TextField getFieldDoenca() {
        return fieldDoenca;
    }

    public void setFieldDoenca(TextField fieldDoenca) {
        this.fieldDoenca = fieldDoenca;
    }

    public Button getButtonAddSintomas() {
        return buttonAddSintomas;
    }

    public void setButtonAddSintomas(Button buttonAddSintomas) {
        this.buttonAddSintomas = buttonAddSintomas;
    }

    public Button getButtonDescricao() {
        return buttonDescricao;
    }

    public void setButtonDescricao(Button buttonDescricao) {
        this.buttonDescricao = buttonDescricao;
    }

    public Button getButtonFinalizar() {
        return buttonFinalizar;
    }

    public void setButtonFinalizar(Button buttonFinalizar) {
        this.buttonFinalizar = buttonFinalizar;
    }
    
}
