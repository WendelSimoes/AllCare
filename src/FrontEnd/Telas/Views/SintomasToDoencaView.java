package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SintomasToDoencaView extends BorderPane {
    
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Layouts
    private VBox layoutCheckBoxs;
    
    //Common nodes
    private ScrollPane scrollSintomas;
    private Button buttonConcluir;
    
    public SintomasToDoencaView(){
        window = new Stage();
        
        //WorkArea
            //buttonApagar
            buttonConcluir = new Button("Concluir");
            buttonConcluir.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            BorderPane.setAlignment(buttonConcluir, Pos.TOP_RIGHT);
            BorderPane.setMargin(buttonConcluir, new Insets(2, 3, 0, 0));
        
            //CheckBoxs sintomas
            layoutCheckBoxs = new VBox();
            layoutCheckBoxs.setSpacing(3);
            
            //Scroll sintomas
            scrollSintomas = new ScrollPane(layoutCheckBoxs);
            scrollSintomas.setPrefWidth(200);
            scrollSintomas.setStyle("-fx-font-size: 15");
            scrollSintomas.setFocusTraversable(false);
            BorderPane.setAlignment(scrollSintomas, Pos.CENTER_LEFT);
            BorderPane.setMargin(scrollSintomas, new Insets(2, 0, 2, 3));
        //
            
        //Adicionando componentes
        this.setStyle("-fx-focus-color: transparent");
        this.setLeft(scrollSintomas);
        this.setRight(buttonConcluir);
        this.setPrefHeight(150);
        //
        
        //Scene
        cena = new Scene(this);
        cena.getStylesheets().addAll(SintomasToDoencaView.class.getResource("/FrontEnd/CSS/StyleCheckBox.css").toExternalForm());
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sintomas");
        window.setResizable(false);
        window.setScene(cena);
    }
    
    //Embebedar lista
    public void atualizarLista(ArrayList<CheckBox> items){
        layoutCheckBoxs.getChildren().addAll(items);
    }
    //
    
    //Add listeners
    public void addButtonConcluirListener(EventHandler<ActionEvent> eventHandler){
        buttonConcluir.setOnAction(eventHandler);
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

    public VBox getLayoutCheckBoxs() {
        return layoutCheckBoxs;
    }

    public void setLayoutCheckBoxs(VBox layoutCheckBoxs) {
        this.layoutCheckBoxs = layoutCheckBoxs;
    }

    public ScrollPane getScrollSintomas() {
        return scrollSintomas;
    }

    public void setScrollSintomas(ScrollPane scrollSintomas) {
        this.scrollSintomas = scrollSintomas;
    }

    public Button getButtonConcluir() {
        return buttonConcluir;
    }

    public void setButtonConcluir(Button buttonConcluir) {
        this.buttonConcluir = buttonConcluir;
    }
}
