package FrontEnd;

import DataBase.ConectionFactory;
import DataBase.Login;
import allcare.Medico;
import allcare.Paciente;
import javafx.application.Application;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TelaCadastro extends Application {
    
    private final String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    private final int TEXT_FIELD_MAX_CHARS = 50;
    
    //Stage
    private Stage windowLogin;
    
    //VariavelConection
    private ConectionFactory con;
    
    //Variaveis javafx
    private Label labelTitle, labelPowered;
    private TextField fieldUser;
    private PasswordField fieldPass;
    private RadioButton radioPatient, radioDoctor;
    private ToggleGroup group;
    private Button buttonEntrar, buttonCadastrar;
    private AudioClip onc;  
 
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //OhayoGoodBye.display(true);
        
        windowLogin = primaryStage;
        
        //DatabaseConection
        con = new ConectionFactory();
        if(con.conectar()){
            System.out.println("A conexão foi estabelecida com success");
        }else{
        System.out.println("A conexão não foi estabelecida :/");
        }
        //
        
        GridPane layoutCenter = new GridPane();
        layoutCenter.setAlignment(Pos.CENTER);
        layoutCenter.setVgap(10);
        layoutCenter.setHgap(-73);
        
        BorderPane layoutMain = new BorderPane();
        layoutMain.setCenter(layoutCenter);
        layoutMain.setId("pane");
        
        //Label title
        labelTitle = new Label("");
        layoutMain.setTop(labelTitle);
        labelTitle.setFont(Font.font("Lucida Console", 50));
        
        //Label powered
        labelPowered = new Label("Powered by Webnamoradores™");
        layoutMain.setBottom(labelPowered);
        labelPowered.setStyle("-fx-text-fill: #000000");
        BorderPane.setAlignment(labelPowered, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(labelPowered, new Insets(0, 3, 2, 0));
        
        //Field username
        fieldUser = new TextField();
        fieldUser.setStyle(STYLE_PADRAO_FIELD);
        fieldUser.setPromptText("Usuário");
        fieldUser.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        GridPane.setConstraints(fieldUser, 0, 0);
        
        //Field password
        fieldPass = new PasswordField();
        fieldPass.setStyle(STYLE_PADRAO_FIELD);
        fieldPass.setPromptText("Senha");
        fieldPass.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
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
        
        //Layout for buttonEntrar and ButtonCadastrar
        HBox layoutLogSign = new HBox();
        layoutLogSign.setSpacing(50);
        GridPane.setConstraints(layoutLogSign, 0, 3);

        //ButtonEntrar
        buttonEntrar = new Button("Entrar");
        buttonEntrar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonEntrar();
            }
        });
        
        //ButtonCadastrar
        buttonCadastrar = new Button("Cadastrar");
        buttonCadastrar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        buttonCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonCadastrar();
            }
        });
        //Adicionando componentes
        layoutLogSign.getChildren().addAll(buttonEntrar, buttonCadastrar);
        
        //Adicionando componentes
        layoutCenter.getChildren().addAll(fieldUser, fieldPass, radioPatient, radioDoctor, layoutLogSign);
        
        //Scene
        Scene scene = new Scene(layoutMain, 280, 380);
        scene.getStylesheets().addAll(TelaCadastro.class.getResource("StyleLogin.css").toExternalForm());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonEntrar();
                }
            }
        });
        
        windowLogin.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                OhayoGoodBye.display(false);
            }
        });
        
        OhayoGoodBye.display(true);

        //Stage
        windowLogin.getIcons().add(new Image(TelaCadastro.class.getResourceAsStream("SickChan.png")));
        windowLogin.setTitle("All Care");
        windowLogin.setScene(scene);
        windowLogin.setResizable(false);
        windowLogin.show();
    }
    
    public void eventButtonEntrar(){
        if(radioPatient.isSelected() && !fieldUser.getText().isEmpty() && !fieldPass.getText().isEmpty()){
            Paciente patient = new Paciente();
            patient.setId_user(fieldUser.getText());
            patient.setSenha(fieldPass.getText());
            if(Login.Logar(patient, con.getDeclaracao_de_comandos(),con.getResult_consultas())){
                radioPatient.setSelected(true);
                fieldUser.setStyle(STYLE_PADRAO_FIELD);
                fieldUser.setText("");
                fieldPass.setStyle(STYLE_PADRAO_FIELD);
                fieldPass.setText("");
                TelaPaciente telaPaciente = new TelaPaciente();
                telaPaciente.display(windowLogin, con);
            }else{
                fieldUser.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                fieldPass.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                NovoJOptionPane.display("Usuário ou senha não reconhecidos", false);
            }
        } else if(radioDoctor.isSelected() && !fieldUser.getText().isEmpty() && !fieldPass.getText().isEmpty()){
            Medico medician = new Medico();
            medician.setId_user(fieldUser.getText());
            medician.setSenha(fieldPass.getText());
            if(Login.Logar(medician, con.getDeclaracao_de_comandos(),con.getResult_consultas())){
                radioPatient.setSelected(true);
                fieldUser.setStyle(STYLE_PADRAO_FIELD);
                fieldUser.setText("");
                fieldPass.setStyle(STYLE_PADRAO_FIELD);
                fieldPass.setText("");
                TelaDoutor telaDoutor = new TelaDoutor();
                telaDoutor.display(windowLogin, con, telaDoutor);
            }else{
                fieldUser.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                fieldPass.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                NovoJOptionPane.display("Usuário ou senha não reconhecidos", false);
            }
        } else if(fieldUser.getText().isEmpty() || fieldPass.getText().isEmpty()){
            if(fieldUser.getText().isEmpty()){
                fieldUser.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldUser.setStyle(STYLE_PADRAO_FIELD);
            }
            if(fieldPass.getText().isEmpty()){
                fieldPass.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldPass.setStyle(STYLE_PADRAO_FIELD);
            }
            NovoJOptionPane.display("Preencha todos os campos", false);
        }
    }
    
    public void eventButtonCadastrar(){
        String errorDuplicate = "";
        
        if(fieldUser.getText().isEmpty() || fieldPass.getText().isEmpty()){
            if(fieldUser.getText().isEmpty()){
                fieldUser.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldUser.setStyle(STYLE_PADRAO_FIELD);
            }
            if(fieldPass.getText().isEmpty()){
                fieldPass.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldPass.setStyle(STYLE_PADRAO_FIELD);
            }
            NovoJOptionPane.display("Preencha todos os campos", false);
        }
                
        if(radioPatient.isSelected() && !fieldUser.getText().isEmpty() && !fieldPass.getText().isEmpty()){
            Paciente patient = new Paciente();
            patient.setId_user(fieldUser.getText());
            patient.setSenha(fieldPass.getText());
            errorDuplicate = Login.Cadastro(patient, con.getDeclaracao_de_comandos());
            if(errorDuplicate.isEmpty()){               
                fieldUser.setStyle(STYLE_PADRAO_FIELD);
                fieldUser.setText("");
                fieldPass.setStyle(STYLE_PADRAO_FIELD);
                fieldPass.setText("");
                NovoJOptionPane.display("Cadastro efetuado", true);
            }else{
                fieldUser.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                NovoJOptionPane.display("Nome de usuário já utilizado", false);
            }
        }
        if(radioDoctor.isSelected() && !fieldUser.getText().isEmpty() && !fieldPass.getText().isEmpty()){
            Medico medician = new Medico();
            medician.setId_user(fieldUser.getText());
            medician.setSenha(fieldPass.getText());
            errorDuplicate = Login.Cadastro(medician, con.getDeclaracao_de_comandos());
            if(errorDuplicate.isEmpty()){
                fieldUser.setStyle(STYLE_PADRAO_FIELD);
                fieldUser.setText("");
                fieldPass.setStyle(STYLE_PADRAO_FIELD);
                fieldPass.setText("");
                NovoJOptionPane.display("Cadastro efetuado", true);
            }else{
                fieldUser.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
                NovoJOptionPane.display("Nome de usuário já utilizado", false);
            }
        }     
    }
}
