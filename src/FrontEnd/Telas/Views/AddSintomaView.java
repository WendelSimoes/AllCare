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

public class AddSintomaView extends HBox{
    
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Common nodes
    private TextField fieldSintoma;
    private Button buttonAdicionar;
    
    public AddSintomaView(){
        window = new Stage();
        
        //WorkArea
            //fieldSintoma
            fieldSintoma = new TextField();
            fieldSintoma.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldSintoma.setPromptText("Sintoma");
            fieldSintoma.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));

            //buttonAdicionar
            buttonAdicionar = new Button("Adicionar");
            buttonAdicionar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        //
        
        //Adicionando componentes
        this.getChildren().addAll(fieldSintoma, buttonAdicionar);
        this.setPrefHeight(30);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        //
        
        //Scene
        cena = new Scene(this);
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sintoma");
        window.setResizable(false);
        window.setScene(cena);
    }
    
    //Add listeners
    public void addButtonAdicionarListener(EventHandler<ActionEvent> eventHandler){
        buttonAdicionar.setOnAction(eventHandler);
    }
    
    public void addSceneListener(EventHandler<KeyEvent> eventHandler){
        cena.setOnKeyPressed(eventHandler);
    }
    //

    public Stage getWindow() {
        return window;
    }

    public Scene getCena() {
        return cena;
    }

    public TextField getFieldSintoma() {
        return fieldSintoma;
    }

    public Button getButtonAdicionar() {
        return buttonAdicionar;
    }
    
}
