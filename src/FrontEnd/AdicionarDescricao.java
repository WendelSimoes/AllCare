package FrontEnd;

import DataBase.ConectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdicionarDescricao {
    
    private static final String STYLE_PADRAO_FIELD = "-fx-background-color: linear-gradient(#1081f2, #4ba4fc); -fx-text-fill: #ffffff; -fx-prompt-text-fill: #d8d6d6; -fx-border-color: e8e3e3; -fx-border-width: 2px";
    private static final int TEXT_FIELD_MAX_CHARS = 100;
    private static final int TEXT_AREA_MAX_CHARS = 300;
    
    private static TextField fieldPropagacao;
    private static TextField fieldTratamento;
    private static TextField fieldDuracao;
    private static ComboBox boxDiagnosticavel;
    private static TextField fieldMedicos;
    private static TextArea areaDescricaoGeral;
    private static Button buttonConcluir;
    private static String descricao;
    
    private static Scene cena;
    
    public static String display(ConectionFactory con, String descricao2){
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        //Field prograpagação
        fieldPropagacao = new TextField();
        fieldPropagacao.setStyle(STYLE_PADRAO_FIELD);
        fieldPropagacao.setPromptText("Propagação");
        fieldPropagacao.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        if(!descricao2.isEmpty()){
            int i;
            int z;
            i = descricao2.indexOf("Propagação") + 12;
            z = descricao2.indexOf("Tratamento") - 1;
            fieldPropagacao.setText(descricao2.substring(i, z));
        }
        
        //Field tratamento
        fieldTratamento = new TextField();
        fieldTratamento.setStyle(STYLE_PADRAO_FIELD);
        fieldTratamento.setPromptText("Tratamento");
        fieldTratamento.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        if(!descricao2.isEmpty()){
            int i;
            int z;
            i = descricao2.indexOf("Tratamento") + 12;
            z = descricao2.indexOf("Duração") - 1;
            fieldTratamento.setText(descricao2.substring(i, z));
        }
        
        //Field duração
        fieldDuracao = new TextField();
        fieldDuracao.setStyle(STYLE_PADRAO_FIELD);
        fieldDuracao.setPromptText("Duração");
        fieldDuracao.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        if(!descricao2.isEmpty()){
            int i;
            int z;
            i = descricao2.indexOf("Duração") + 9;
            z = descricao2.indexOf("Diagnosticável") - 1;
            fieldDuracao.setText(descricao2.substring(i, z));
        }
        
        //Box diagnosticavel
        boxDiagnosticavel = new ComboBox();
        boxDiagnosticavel.setPrefWidth(250);
        boxDiagnosticavel.getItems().addAll("Sim", "Não");
        boxDiagnosticavel.setPromptText("Diagnosticável?");
        if(!descricao2.isEmpty()){
            int i;
            int z;
            i = descricao2.indexOf("Diagnosticável") + 16;
            z = descricao2.indexOf("Médico(s)") - 1;
            if(descricao2.substring(i, z).equals("Sim")){
                boxDiagnosticavel.getSelectionModel().select(0);
            }else{
                boxDiagnosticavel.getSelectionModel().select(1);
            }
        }
        
        //Field medicos
        fieldMedicos = new TextField();
        fieldMedicos.setStyle(STYLE_PADRAO_FIELD);
        fieldMedicos.setPromptText("Médico(s)");
        fieldMedicos.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_FIELD_MAX_CHARS ? change : null));
        if(!descricao2.isEmpty()){
            int i;
            int z;
            i = descricao2.indexOf("Médico(s)") + 11;
            z = descricao2.indexOf("Descrição geral") - 1;
            fieldMedicos.setText(descricao2.substring(i, z));
        }
        
        //Area descrição
        areaDescricaoGeral = new TextArea();
        areaDescricaoGeral.setPrefSize(230, 75);
        areaDescricaoGeral.setFocusTraversable(false);
        areaDescricaoGeral.setWrapText(true);
        areaDescricaoGeral.setPromptText("Descrição geral");
        areaDescricaoGeral.setTextFormatter(new TextFormatter<String>(change -> change.getControlNewText().length() <= TEXT_AREA_MAX_CHARS ? change : null));
        if(!descricao2.isEmpty()){
            int i;
            i = descricao2.indexOf("Descrição geral") + 17;
            areaDescricaoGeral.setText(descricao2.substring(i));
        }
        
        //Button concluir
        buttonConcluir = new Button("Concluir");
        buttonConcluir.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        BorderPane.setAlignment(buttonConcluir, Pos.CENTER);
        buttonConcluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonConcluir(window);
            }
        });
        
        //LayoutDados
        VBox layoutDados = new VBox();
        layoutDados.setAlignment(Pos.CENTER);
        layoutDados.setSpacing(5);
        layoutDados.setPrefWidth(230);
        BorderPane.setMargin(layoutDados, new Insets(2, 2, 2, 2));
        layoutDados.getChildren().addAll(fieldPropagacao, fieldTratamento, fieldDuracao, boxDiagnosticavel, fieldMedicos, areaDescricaoGeral, buttonConcluir);
        
        //Layout main
        BorderPane layoutMain = new BorderPane();
        layoutMain.setCenter(layoutDados);
        
        //Scene
        cena = new Scene(layoutMain);
        cena.getStylesheets().addAll(AdicionarDescricao.class.getResource("StyleTextArea.css").toExternalForm());
        
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonConcluir(window);
                }
            }
        });
        
        //Salvar descrição pré se o usuário clicar no X
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                descricao = descricao2;
            }
        });
        
        //Stage
        window.getIcons().add(new Image(MostrarDescricaoDoenca.class.getResourceAsStream("SickChan.png")));
        window.setTitle("Descrição");
        window.setScene(cena);
        window.setResizable(false);
        window.showAndWait();
        
        return descricao;
    }

    public static void eventButtonConcluir(Stage window){
        if(!fieldPropagacao.getText().isEmpty() && !fieldTratamento.getText().isEmpty() 
            && !fieldDuracao.getText().isEmpty() && boxDiagnosticavel.getSelectionModel().getSelectedIndex() > -1
            && !fieldMedicos.getText().isEmpty() && !areaDescricaoGeral.getText().isEmpty()){
            descricao = "Propagação: " + fieldPropagacao.getText() + "\n" +
                    "Tratamento: " + fieldTratamento.getText() + "\n" +
                    "Duração: " + fieldDuracao.getText() + "\n" +
                    "Diagnosticável: " + boxDiagnosticavel.getSelectionModel().getSelectedItem().toString() + "\n" +
                    "Médico(s): " + fieldMedicos.getText() + "\n" +
                    "Descrição geral: " + areaDescricaoGeral.getText();
            window.close();
            fieldPropagacao.setStyle(STYLE_PADRAO_FIELD);
            fieldTratamento.setStyle(STYLE_PADRAO_FIELD);
            fieldDuracao.setStyle(STYLE_PADRAO_FIELD);
            fieldMedicos.setStyle(STYLE_PADRAO_FIELD);
            cena.getStylesheets().addAll(AdicionarDescricao.class.getResource("StyleTextArea.css").toExternalForm());
        }else{
            if(fieldPropagacao.getText().isEmpty()){
                fieldPropagacao.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldPropagacao.setStyle(STYLE_PADRAO_FIELD);
            }
            if(fieldTratamento.getText().isEmpty()){
                fieldTratamento.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldTratamento.setStyle(STYLE_PADRAO_FIELD);
            }
            if(fieldDuracao.getText().isEmpty()){
                fieldDuracao.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldDuracao.setStyle(STYLE_PADRAO_FIELD);
            }
            if(fieldMedicos.getText().isEmpty()){
                fieldMedicos.setStyle(STYLE_PADRAO_FIELD + ";-fx-border-color: #ff0000");
            }else{
                fieldMedicos.setStyle(STYLE_PADRAO_FIELD);
            }
            if(areaDescricaoGeral.getText().isEmpty()){
                cena.getStylesheets().setAll(AdicionarDescricao.class.getResource("StyleTextAreaErro.css").toExternalForm());
            }else{
                cena.getStylesheets().setAll(AdicionarDescricao.class.getResource("StyleTextArea.css").toExternalForm());
            }
            NovoJOptionPane.display("Preencha todos os campos", false);
        }
    }
    
}
