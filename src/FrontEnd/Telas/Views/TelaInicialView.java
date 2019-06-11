package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//Tela inicial onde se pode entrar e cadastrar no programa, podendo levar a tela de paciente e doutor
public class TelaInicialView extends BorderPane{
    
    //Stage & scene
    private Stage window;
    private Scene cena;
    
    //Layouts
    GridPane layoutCenter;
    HBox layoutLogSign;
    
    //Common nodes
    private Label labelTitle, labelPowered;
    private TextField fieldUser;
    private PasswordField fieldPass;
    private RadioButton radioPatient, radioDoctor;
    private ToggleGroup group;
    private Button buttonEntrar, buttonCadastrar;
    
    public TelaInicialView(Stage window){
        this.window = window;
        
        //WorkArea
            //Label title
            labelTitle = new Label("");
            labelTitle.setFont(Font.font("Lucida Console", 50));
            this.setTop(labelTitle);

            //Label powered
            labelPowered = new Label("Powered by Webnamoradores™");
            labelPowered.setStyle("-fx-text-fill: #000000");
            BorderPane.setAlignment(labelPowered, Pos.BOTTOM_RIGHT);
            BorderPane.setMargin(labelPowered, new Insets(0, 3, 2, 0));
            this.setBottom(labelPowered);

            //Field username
            fieldUser = new TextField();
            fieldUser.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldUser.setPromptText("Usuário");
            fieldUser.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));
            GridPane.setConstraints(fieldUser, 0, 0);
            
            //Field password
            fieldPass = new PasswordField();
            fieldPass.setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
            fieldPass.setPromptText("Senha");
            fieldPass.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= DefaultStyles.getTEXT_FIELD_MAX_CHARS() ? change : null));
            GridPane.setConstraints(fieldPass, 0, 1);
            
            //Toggle group
            group = new ToggleGroup();
            
            //Radio patient
            radioPatient = new RadioButton("Paciente");
            radioPatient.setSelected(true);
            radioPatient.setStyle("-fx-text-fill: #000000");
            GridPane.setConstraints(radioPatient, 0, 2);
            radioPatient.setToggleGroup(group);
            
            //Radio doctor
            radioDoctor = new RadioButton("Doutor(a)");
            radioDoctor.setSelected(false);
            radioDoctor.setStyle("-fx-text-fill: #000000");
            GridPane.setConstraints(radioDoctor, 1, 2);
            radioDoctor.setToggleGroup(group);
            
            //ButtonEntrar
            buttonEntrar = new Button("Entrar");
            buttonEntrar.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());

            //ButtonCadastrar
            buttonCadastrar = new Button("Cadastrar");
            buttonCadastrar.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            
            //Layout for buttonEntrar and ButtonCadastrar
            layoutLogSign = new HBox();
            layoutLogSign.setSpacing(50);
            GridPane.setConstraints(layoutLogSign, 0, 3);
            layoutLogSign.getChildren().addAll(buttonEntrar, buttonCadastrar);
            
            //Layout crude
            layoutCenter = new GridPane();
            layoutCenter.setAlignment(Pos.CENTER);
            layoutCenter.setVgap(10);
            layoutCenter.setHgap(-73);
            this.setCenter(layoutCenter);
            layoutCenter.getChildren().addAll(fieldUser, fieldPass, radioPatient, radioDoctor, layoutLogSign);
        //
        
        //Adicionando componentes
        this.setCenter(layoutCenter);
        this.setId("pane");
        //
        
        //Scene
        cena = new Scene(this, 280, 380);
        cena.getStylesheets().addAll(TelaInicialView.class.getResource("/FrontEnd/CSS/StyleLogin.css").toExternalForm());
        
        //Stage
        this.window.getIcons().add(DefaultStageIcon.getStageIcon());
        this.window.setTitle("All Care");
        this.window.setResizable(false);
        this.window.setScene(cena);
    }

    //Add listeners
    public void addButtonEntrarListener(EventHandler<ActionEvent> eventHandler){
        buttonEntrar.setOnAction(eventHandler);
    }
    
    public void addButtonCadastrarListener(EventHandler<ActionEvent> eventHandler){
        buttonCadastrar.setOnAction(eventHandler);
    }
    
    public void addSceneListener(EventHandler<KeyEvent> eventHandler){
        cena.setOnKeyPressed(eventHandler);
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

    public GridPane getLayoutCenter() {
        return layoutCenter;
    }

    public void setLayoutCenter(GridPane layoutCenter) {
        this.layoutCenter = layoutCenter;
    }

    public HBox getLayoutLogSign() {
        return layoutLogSign;
    }

    public void setLayoutLogSign(HBox layoutLogSign) {
        this.layoutLogSign = layoutLogSign;
    }

    public Label getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(Label labelTitle) {
        this.labelTitle = labelTitle;
    }

    public Label getLabelPowered() {
        return labelPowered;
    }

    public void setLabelPowered(Label labelPowered) {
        this.labelPowered = labelPowered;
    }

    public TextField getFieldUser() {
        return fieldUser;
    }

    public void setFieldUser(TextField fieldUser) {
        this.fieldUser = fieldUser;
    }

    public PasswordField getFieldPass() {
        return fieldPass;
    }

    public void setFieldPass(PasswordField fieldPass) {
        this.fieldPass = fieldPass;
    }

    public RadioButton getRadioPatient() {
        return radioPatient;
    }

    public void setRadioPatient(RadioButton radioPatient) {
        this.radioPatient = radioPatient;
    }

    public RadioButton getRadioDoctor() {
        return radioDoctor;
    }

    public void setRadioDoctor(RadioButton radioDoctor) {
        this.radioDoctor = radioDoctor;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public void setGroup(ToggleGroup group) {
        this.group = group;
    }

    public Button getButtonEntrar() {
        return buttonEntrar;
    }

    public void setButtonEntrar(Button buttonEntrar) {
        this.buttonEntrar = buttonEntrar;
    }

    public Button getButtonCadastrar() {
        return buttonCadastrar;
    }

    public void setButtonCadastrar(Button buttonCadastrar) {
        this.buttonCadastrar = buttonCadastrar;
    }
    
}
