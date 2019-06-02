package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
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

public class TelaPacienteView extends BorderPane {
    
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Layouts
    private VBox layoutLeft;
    private VBox layoutCheckBoxs;
    
    //Common nodes
    private Label labelSintomas;
    private Label labelDoencas;
    private ScrollPane scrollSintomas;
    private ListView<String> listDoencas;
    private TextField fieldSearch;
    
    public TelaPacienteView(){
        window = new Stage();
        
        //WorkArea
            //Carregar checkBoxs sintomas
            layoutCheckBoxs = new VBox();
            layoutCheckBoxs.setSpacing(3);
            layoutCheckBoxs.setFocusTraversable(false);
            layoutCheckBoxs.setStyle("-fx-font-size: 19");
            
            //Scroll sintomas
            scrollSintomas = new ScrollPane(layoutCheckBoxs);
            scrollSintomas.setFocusTraversable(false);
            scrollSintomas.setPrefHeight(430);
            scrollSintomas.setStyle("-fx-opacity: 0.7; -fx-font-size: 20");
            
            //Label sintomas
            labelSintomas = new Label("Sintomas:");
            labelSintomas.setStyle("-fx-text-fill: #aa0a0a; -fx-font-size: 20; -fx-font-weight: bold");
            
            //layout search
            HBox layoutSearch = new HBox();
            layoutSearch.setSpacing(5);
            VBox.setMargin(layoutSearch, new Insets(0, 0, 2, 0));
            layoutSearch.getChildren().add(new ImageView(new Image(TelaPacienteView.class.getResourceAsStream("/FrontEnd/Midia/SearchIcon.png"))));
            fieldSearch = new TextField();
            fieldSearch.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldSearch.setPromptText("Pesquisar");
            fieldSearch.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));
            fieldSearch.setPrefWidth(210);
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
            labelDoencas = new Label("Doenças possíveis:");
            labelDoencas.setStyle("-fx-text-fill: #aa0a0a; -fx-font-size: 20; -fx-font-weight: bold");
            
            //Lista doenças
            listDoencas = new ListView<>();
            listDoencas.setPrefHeight(430);
            listDoencas.setStyle("-fx-opacity: 0.7; -fx-font-size: 19");
            listDoencas.setFocusTraversable(false);
            listDoencas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            
            //LayoutRight
            VBox layoutRight = new VBox();
            layoutRight.setAlignment(Pos.CENTER);
            layoutRight.setPrefWidth(210);
            BorderPane.setAlignment(layoutRight, Pos.CENTER_RIGHT);
            BorderPane.setMargin(layoutRight, new Insets(0, 5, 0, 0));
            layoutRight.getChildren().addAll(labelDoencas, listDoencas);
        //
            
        //Adicionando componentes
        this.setLeft(layoutLeft);
        this.setRight(layoutRight);
        this.setId("pane");
        //
        
        //Scene
        Scene cena = new Scene(this, 430, 430);
        cena.getStylesheets().addAll(TelaPacienteView.class.getResource("/FrontEnd/CSS/StylePaciente.css").toExternalForm());
        cena.getStylesheets().addAll(TelaPacienteView.class.getResource("/FrontEnd/CSS/StyleListView.css").toExternalForm());
        cena.getStylesheets().addAll(TelaPacienteView.class.getResource("/FrontEnd/CSS/StyleCheckBox.css").toExternalForm());
    
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Patient Home");
        window.setResizable(false);
        window.setScene(cena);
    }
    
    //Embebedar lista
    public void preencherLista(ArrayList<CheckBox> items){
        layoutCheckBoxs.getChildren().setAll(items);
    }
    //
    
    //Add listeners
    public void addFieldSearchListener(ChangeListener<String> eventHandler){
        fieldSearch.textProperty().addListener(eventHandler);
    }
    
    public void addListDoencasListener(EventHandler<MouseEvent> eventHandler){
        listDoencas.setOnMouseClicked(eventHandler);
    }
    
    public void addOnCloseRequest(EventHandler<WindowEvent> eventHandler){
        window.setOnCloseRequest(eventHandler);
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

    public VBox getLayoutLeft() {
        return layoutLeft;
    }

    public void setLayoutLeft(VBox layoutLeft) {
        this.layoutLeft = layoutLeft;
    }

    public VBox getLayoutCheckBoxs() {
        return layoutCheckBoxs;
    }

    public void setLayoutCheckBoxs(VBox layoutCheckBoxs) {
        this.layoutCheckBoxs = layoutCheckBoxs;
    }

    public Label getLabelSintomas() {
        return labelSintomas;
    }

    public void setLabelSintomas(Label labelSintomas) {
        this.labelSintomas = labelSintomas;
    }

    public Label getLabelDoencas() {
        return labelDoencas;
    }

    public void setLabelDoencas(Label labelDoencas) {
        this.labelDoencas = labelDoencas;
    }

    public ScrollPane getScrollSintomas() {
        return scrollSintomas;
    }

    public void setScrollSintomas(ScrollPane scrollSintomas) {
        this.scrollSintomas = scrollSintomas;
    }

    public ListView<String> getListDoencas() {
        return listDoencas;
    }

    public void setListDoencas(ListView<String> listDoencas) {
        this.listDoencas = listDoencas;
    }

    public TextField getFieldSearch() {
        return fieldSearch;
    }

    public void setFieldSearch(TextField fieldSearch) {
        this.fieldSearch = fieldSearch;
    }
    
}
