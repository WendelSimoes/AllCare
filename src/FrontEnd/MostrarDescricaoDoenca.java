package FrontEnd;

import DataBase.ConectionFactory;
import allcare.Doenca;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MostrarDescricaoDoenca {
    
    private static Button buttonOK;
    
    public static void display(ConectionFactory con, Doenca doenca){
        //Stage
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        
        //Layout descrição
        GridPane layoutDescricao = new GridPane();
        layoutDescricao.setStyle("-fx-font-size: 15");
        layoutDescricao.setVgap(5);
        layoutDescricao.setHgap(10);
        BorderPane.setMargin(layoutDescricao, new Insets(5, 5, 5, 5));
        
            //Label propagação
            Label labelPropagacao = new Label("Propagação:");
            labelPropagacao.setStyle("-fx-font-weight: bold");
            labelPropagacao.setWrapText(true);
            GridPane.setConstraints(labelPropagacao, 0, 0);
            GridPane.setValignment(labelPropagacao, VPos.TOP);
            GridPane.setHalignment(labelPropagacao, HPos.LEFT);
            //Label tratamento
            Label labelTratamento = new Label("Tratamento:");
            labelTratamento.setStyle("-fx-font-weight: bold");
            labelTratamento.setWrapText(true);
            GridPane.setConstraints(labelTratamento, 0, 1);
            GridPane.setValignment(labelTratamento, VPos.TOP);
            GridPane.setHalignment(labelTratamento, HPos.LEFT);
            //Label duração
            Label labelDuracao = new Label("Duração:");
            labelDuracao.setStyle("-fx-font-weight: bold");
            labelDuracao.setWrapText(true);
            GridPane.setConstraints(labelDuracao, 0, 2);
            GridPane.setValignment(labelDuracao, VPos.TOP);
            GridPane.setHalignment(labelDuracao, HPos.LEFT);
            //Label diagnóstico
            Label labelDiagnosticavel = new Label("Diagnosticável:");
            labelDiagnosticavel.setStyle("-fx-font-weight: bold");
            labelDiagnosticavel.setWrapText(true);
            GridPane.setConstraints(labelDiagnosticavel, 0, 3);
            GridPane.setValignment(labelDiagnosticavel, VPos.TOP);
            GridPane.setHalignment(labelDiagnosticavel, HPos.LEFT);
            //Label médicos
            Label labelMedicos = new Label("Médico(s):");
            labelMedicos.setStyle("-fx-font-weight: bold");
            labelMedicos.setWrapText(true);
            GridPane.setConstraints(labelMedicos, 0, 4);
            GridPane.setValignment(labelMedicos, VPos.TOP);
            GridPane.setHalignment(labelMedicos, HPos.LEFT);
            //Label descrição geral
            Label labelDescricaoGeral = new Label("Descrição geral:");
            labelDescricaoGeral.setStyle("-fx-font-weight: bold");
            labelDescricaoGeral.setWrapText(true);
            GridPane.setConstraints(labelDescricaoGeral, 0, 5);
            GridPane.setValignment(labelDescricaoGeral, VPos.TOP);
            GridPane.setHalignment(labelDescricaoGeral, HPos.LEFT);
            
            int i, z;
            
            //Label propagação result
            i = doenca.getDescricao().indexOf("Propagação") + 12;
            z = doenca.getDescricao().indexOf("Tratamento") - 1;
            Label labelPropagacaoResult = new Label(doenca.getDescricao().substring(i, z));
            labelPropagacaoResult.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px");
            labelPropagacaoResult.setMaxWidth(300);
            labelPropagacaoResult.setWrapText(true);
            GridPane.setConstraints(labelPropagacaoResult, 1, 0);
            //Label tratamento result
            i = doenca.getDescricao().indexOf("Tratamento") + 12;
            z = doenca.getDescricao().indexOf("Duração") - 1;
            Label labelTratamentoResult = new Label(doenca.getDescricao().substring(i, z));
            labelTratamentoResult.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px");
            labelTratamentoResult.setMaxWidth(300);
            labelTratamentoResult.setWrapText(true);
            GridPane.setConstraints(labelTratamentoResult, 1, 1);
            //Label duração result
            i = doenca.getDescricao().indexOf("Duração") + 9;
            z = doenca.getDescricao().indexOf("Diagnosticável") - 1;
            Label labelDuracaoResult = new Label(doenca.getDescricao().substring(i, z));
            labelDuracaoResult.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px");
            labelDuracaoResult.setMaxWidth(300);
            labelDuracaoResult.setWrapText(true);
            GridPane.setConstraints(labelDuracaoResult, 1, 2);
            //Label requerDiagnósticoResult
            i = doenca.getDescricao().indexOf("Diagnosticável") + 16;
            z = doenca.getDescricao().indexOf("Médico(s)") - 1;
            Label labelDiagnosticavelResult = new Label(doenca.getDescricao().substring(i, z));
            labelDiagnosticavelResult.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px");
            labelDiagnosticavelResult.setMaxWidth(300);
            labelDiagnosticavelResult.setWrapText(true);
            GridPane.setConstraints(labelDiagnosticavelResult, 1, 3);
            //Label médicosResult
            i = doenca.getDescricao().indexOf("Médico(s)") + 12;
            z = doenca.getDescricao().indexOf("Descrição geral") - 1;
            Label labelMedicosResult = new Label(doenca.getDescricao().substring(i, z));
            labelMedicosResult.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px");
            labelMedicosResult.setMaxWidth(300);
            labelMedicosResult.setWrapText(true);
            GridPane.setConstraints(labelMedicosResult, 1, 4);
            //Label descrição geralResult
            i = doenca.getDescricao().indexOf("Descrição geral") + 17;
            Label labelDescricaoGeralResult = new Label(doenca.getDescricao().substring(i));
            labelDescricaoGeralResult.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-background-radius: 5; -fx-padding: 3px");
            labelDescricaoGeralResult.setMaxWidth(300);
            labelDescricaoGeralResult.setWrapText(true);
            GridPane.setConstraints(labelDescricaoGeralResult, 1, 5);
            
        layoutDescricao.getChildren().addAll(labelPropagacao, labelTratamento, labelDuracao, labelDiagnosticavel, labelMedicos, labelDescricaoGeral, labelPropagacaoResult, labelTratamentoResult, labelDuracaoResult, labelDiagnosticavelResult, labelMedicosResult, labelDescricaoGeralResult);
        //
        
        //Button OK
        buttonOK = new Button("OK");
        buttonOK.setStyle("-fx-text-fill: #ffffff; -fx-background-color: linear-gradient(#ff00a6, #ffa3de)");
        BorderPane.setAlignment(buttonOK, Pos.CENTER);
        BorderPane.setMargin(buttonOK, new Insets(0, 0, 7, 0));
        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                eventButtonOK(window);
            }
        });
        
        //Layout main
        BorderPane layoutMain = new BorderPane();
        layoutMain.setCenter(layoutDescricao);
        layoutMain.setBottom(buttonOK);
        layoutMain.setId("pane");
        
        //Scene
        Scene cena = new Scene(layoutMain);
        cena.getStylesheets().addAll(MostrarDescricaoDoenca.class.getResource("StyleDescricao.css").toExternalForm());
        
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonOK(window);
                }
            }
        });
        
        //Stage
        window.getIcons().add(new Image(MostrarDescricaoDoenca.class.getResourceAsStream("SickChan.png")));
        window.setTitle("Descrição");
        window.setScene(cena);
        window.setResizable(false);
        window.showAndWait();
    }

    public static void eventButtonOK(Stage window){
        window.close();
    }
    
}
