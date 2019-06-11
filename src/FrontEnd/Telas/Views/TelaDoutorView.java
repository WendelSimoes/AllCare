package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//Tela destinada ao usuário doutor do programa, permitindo-o adicionar e apagar sintomas e adicionar, apagar e editar doenças
public class TelaDoutorView extends BorderPane{
    
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Layouts
    private BorderPane layoutRight;
    private HBox layoutButtonsSintomas;
    private HBox layoutButtons;
    
    //Common nodes
    private ListView<String> listDoencas;
    private Button buttonAddSintoma, buttonApagarSintomas, buttonAdd, buttonApagar, buttonEdit;
    
    public TelaDoutorView(){
        window = new Stage();
        
        //WorkArea
            //Lista doenças
            listDoencas = new ListView<String>();
            BorderPane.setAlignment(listDoencas, Pos.CENTER_LEFT);
            listDoencas.setPrefWidth(200);
            listDoencas.setStyle("-fx-opacity: 0.7; -fx-font-size: 19");
            listDoencas.setFocusTraversable(false);
            listDoencas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            
            //Button addSintomas
            buttonAddSintoma = new Button("Adicionar sintoma");
            buttonAddSintoma.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            
            //Button apagarSintomas
            buttonApagarSintomas = new Button("Apagar sintomas");
            buttonApagarSintomas.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            
            //Button add
            buttonAdd = new Button("Adicionar doença");
            buttonAdd.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            
            //Button apagar doenca
            buttonApagar = new Button("Apagar");
            buttonApagar.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            buttonApagar.setDisable(true);
            
            
            //Button edit
            buttonEdit = new Button("Editar");
            buttonEdit.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());  
            buttonEdit.setDisable(true);
            
            //Layout buttonsSintomas
            layoutButtonsSintomas = new HBox();
            layoutButtonsSintomas.setSpacing(5);
            layoutButtonsSintomas.setAlignment(Pos.TOP_RIGHT);
            layoutButtonsSintomas.getChildren().addAll(buttonAddSintoma, buttonApagarSintomas);
            BorderPane.setAlignment(layoutButtonsSintomas, Pos.TOP_RIGHT);
            BorderPane.setMargin(layoutButtonsSintomas, new Insets(2, 3, 0, 0));
            
            //Layout buttons
            layoutButtons = new HBox();
            layoutButtons.setSpacing(5);
            layoutButtons.getChildren().addAll(buttonAdd, buttonApagar, buttonEdit);
            BorderPane.setAlignment(layoutButtons, Pos.BOTTOM_RIGHT);
            BorderPane.setMargin(layoutButtons, new Insets(0, 3, 2, 0));
            
            //Border layout main/right
            layoutRight = new BorderPane();
            layoutRight.setBottom(layoutButtons);
            layoutRight.setTop(layoutButtonsSintomas);
        //
            
        //Adicionando componentes
        this.setLeft(listDoencas);
        this.setRight(layoutRight);
        this.setId("pane");
        //
        
        //Scene
        Scene cena = new Scene(this, 430, 430);
        cena.getStylesheets().addAll(TelaDoutorView.class.getResource("/FrontEnd/CSS/StyleDoutor.css").toExternalForm());
        cena.getStylesheets().addAll(TelaDoutorView.class.getResource("/FrontEnd/CSS/StyleListView.css").toExternalForm());
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Doctor Home");
        window.setResizable(false);
        window.setScene(cena);
    }

    //Add listeners
    public void addOnMouseClickedLista(EventHandler<MouseEvent> eventHandler){
        listDoencas.setOnMouseClicked(eventHandler);
    }
    
    public void addButtonAddSintomaListener(EventHandler<ActionEvent> eventHandler){
        buttonAddSintoma.setOnAction(eventHandler);
    }
    
    public void addButtonApagarSintomasListener(EventHandler<ActionEvent> eventHandler){
        buttonApagarSintomas.setOnAction(eventHandler);
    }
    
    public void addButtonAddListener(EventHandler<ActionEvent> eventHandler){
        buttonAdd.setOnAction(eventHandler);
    }
    
    public void addButtonApagarListener(EventHandler<ActionEvent> eventHandler){
        buttonApagar.setOnAction(eventHandler);
    }
    
    public void addButtonEditListener(EventHandler<ActionEvent> eventHandler){
        buttonEdit.setOnAction(eventHandler);
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

    public BorderPane getLayoutRight() {
        return layoutRight;
    }

    public void setLayoutRight(BorderPane layoutRight) {
        this.layoutRight = layoutRight;
    }

    public HBox getLayoutButtonsSintomas() {
        return layoutButtonsSintomas;
    }

    public void setLayoutButtonsSintomas(HBox layoutButtonsSintomas) {
        this.layoutButtonsSintomas = layoutButtonsSintomas;
    }

    public HBox getLayoutButtons() {
        return layoutButtons;
    }

    public void setLayoutButtons(HBox layoutButtons) {
        this.layoutButtons = layoutButtons;
    }

    public ListView<String> getListDoencas() {
        return listDoencas;
    }

    public void setListDoencas(ListView<String> listDoencas) {
        this.listDoencas = listDoencas;
    }

    public Button getButtonAddSintoma() {
        return buttonAddSintoma;
    }

    public void setButtonAddSintoma(Button buttonAddSintoma) {
        this.buttonAddSintoma = buttonAddSintoma;
    }

    public Button getButtonApagarSintomas() {
        return buttonApagarSintomas;
    }

    public void setButtonApagarSintomas(Button buttonApagarSintomas) {
        this.buttonApagarSintomas = buttonApagarSintomas;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public void setButtonAdd(Button buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public Button getButtonApagar() {
        return buttonApagar;
    }

    public void setButtonApagar(Button buttonApagar) {
        this.buttonApagar = buttonApagar;
    }

    public Button getButtonEdit() {
        return buttonEdit;
    }

    public void setButtonEdit(Button buttonEdit) {
        this.buttonEdit = buttonEdit;
    }
}
