package FrontEnd.Telas.Views;

import FrontEnd.CSS.DefaultStageIcon;
import FrontEnd.CSS.DefaultStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MostrarDescricaoView {
    
    //Stage & scene
    private static Stage window;
    private static Scene cena;
    
    //Layouts
    private static BorderPane layoutMain;
    private static GridPane layoutDescricao;
    
    //Common nodes
    private static Button buttonOK;
    private static AudioClip audioClip;
    
    public static void display(String descricao){
        window = new Stage();
        
        audioClip = new AudioClip(MostrarDescricaoView.class.getResource("/FrontEnd/Midia/Ok-onii-chan.mp3").toString());
        
        //WorkArea
            //Label propagação
            Label labelPropagacao = new Label("Propagação:");
            labelPropagacao.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO());
            labelPropagacao.setWrapText(true);
            GridPane.setConstraints(labelPropagacao, 0, 0);
            GridPane.setValignment(labelPropagacao, VPos.TOP);
            GridPane.setHalignment(labelPropagacao, HPos.LEFT);
            
            //Label tratamento
            Label labelTratamento = new Label("Tratamento:");
            labelTratamento.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO());
            labelTratamento.setWrapText(true);
            GridPane.setConstraints(labelTratamento, 0, 1);
            GridPane.setValignment(labelTratamento, VPos.TOP);
            GridPane.setHalignment(labelTratamento, HPos.LEFT);
            
            //Label duração
            Label labelDuracao = new Label("Duração:");
            labelDuracao.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO());
            labelDuracao.setWrapText(true);
            GridPane.setConstraints(labelDuracao, 0, 2);
            GridPane.setValignment(labelDuracao, VPos.TOP);
            GridPane.setHalignment(labelDuracao, HPos.LEFT);
            
            //Label diagnóstico
            Label labelDiagnosticavel = new Label("Diagnosticável:");
            labelDiagnosticavel.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO());
            labelDiagnosticavel.setWrapText(true);
            GridPane.setConstraints(labelDiagnosticavel, 0, 3);
            GridPane.setValignment(labelDiagnosticavel, VPos.TOP);
            GridPane.setHalignment(labelDiagnosticavel, HPos.LEFT);
            
            //Label médicos
            Label labelMedicos = new Label("Médico(s):");
            labelMedicos.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO());
            labelMedicos.setWrapText(true);
            GridPane.setConstraints(labelMedicos, 0, 4);
            GridPane.setValignment(labelMedicos, VPos.TOP);
            GridPane.setHalignment(labelMedicos, HPos.LEFT);
            
            //Label descrição geral
            Label labelDescricaoGeral = new Label("Descrição geral:");
            labelDescricaoGeral.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_LEFT_DESCRICAO());
            labelDescricaoGeral.setWrapText(true);
            GridPane.setConstraints(labelDescricaoGeral, 0, 5);
            GridPane.setValignment(labelDescricaoGeral, VPos.TOP);
            GridPane.setHalignment(labelDescricaoGeral, HPos.LEFT);
            
            int i, z;
            
            //Label propagação result
            i = descricao.indexOf("Propagação") + 12;
            z = descricao.indexOf("Tratamento") - 1;
            Label labelPropagacaoResult = new Label(descricao.substring(i, z));
            labelPropagacaoResult.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO());
            labelPropagacaoResult.setMaxWidth(300);
            labelPropagacaoResult.setWrapText(true);
            GridPane.setConstraints(labelPropagacaoResult, 1, 0);
            
            //Label tratamento result
            i = descricao.indexOf("Tratamento") + 12;
            z = descricao.indexOf("Duração") - 1;
            Label labelTratamentoResult = new Label(descricao.substring(i, z));
            labelTratamentoResult.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO());
            labelTratamentoResult.setMaxWidth(300);
            labelTratamentoResult.setWrapText(true);
            GridPane.setConstraints(labelTratamentoResult, 1, 1);
            
            //Label duração result
            i = descricao.indexOf("Duração") + 9;
            z = descricao.indexOf("Diagnosticável") - 1;
            Label labelDuracaoResult = new Label(descricao.substring(i, z));
            labelDuracaoResult.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO());
            labelDuracaoResult.setMaxWidth(300);
            labelDuracaoResult.setWrapText(true);
            GridPane.setConstraints(labelDuracaoResult, 1, 2);
            
            //Label requerDiagnósticoResult
            i = descricao.indexOf("Diagnosticável") + 16;
            z = descricao.indexOf("Médico(s)") - 1;
            Label labelDiagnosticavelResult = new Label(descricao.substring(i, z));
            labelDiagnosticavelResult.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO());
            labelDiagnosticavelResult.setMaxWidth(300);
            labelDiagnosticavelResult.setWrapText(true);
            GridPane.setConstraints(labelDiagnosticavelResult, 1, 3);
            
            //Label médicosResult
            i = descricao.indexOf("Médico(s)") + 12;
            z = descricao.indexOf("Descrição geral") - 1;
            Label labelMedicosResult = new Label(descricao.substring(i, z));
            labelMedicosResult.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO());
            labelMedicosResult.setMaxWidth(300);
            labelMedicosResult.setWrapText(true);
            GridPane.setConstraints(labelMedicosResult, 1, 4);
            
            //Label descrição geralResult
            i = descricao.indexOf("Descrição geral") + 17;
            Label labelDescricaoGeralResult = new Label(descricao.substring(i));
            labelDescricaoGeralResult.setStyle(DefaultStyles.getSTYLE_PADRAO_LABELS_RIGHT_DESCRICAO());
            labelDescricaoGeralResult.setMaxWidth(300);
            labelDescricaoGeralResult.setWrapText(true);
            GridPane.setConstraints(labelDescricaoGeralResult, 1, 5);
            
            //Button OK
            buttonOK = new Button("OK");
            buttonOK.setStyle(DefaultStyles.getSTYLE_PADRAO_BUTTON());
            BorderPane.setAlignment(buttonOK, Pos.CENTER);
            BorderPane.setMargin(buttonOK, new Insets(0, 0, 7, 0));
            buttonOK.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    eventButtonOK();
                }
            });
            
            //Layout descrição
            layoutDescricao = new GridPane();
            layoutDescricao.setStyle("-fx-font-size: 15");
            layoutDescricao.setVgap(5);
            layoutDescricao.setHgap(10);
            BorderPane.setMargin(layoutDescricao, new Insets(5, 5, 5, 5));
            layoutDescricao.getChildren().addAll(labelPropagacao, labelTratamento, labelDuracao, labelDiagnosticavel, labelMedicos, labelDescricaoGeral, labelPropagacaoResult, labelTratamentoResult, labelDuracaoResult, labelDiagnosticavelResult, labelMedicosResult, labelDescricaoGeralResult);
        
            //Layout main
            layoutMain = new BorderPane();
            layoutMain.setCenter(layoutDescricao);
            layoutMain.setBottom(buttonOK);
            layoutMain.setId("pane");
        //
            
        //Scene
        cena = new Scene(layoutMain);
        cena.getStylesheets().addAll(MostrarDescricaoView.class.getResource("/FrontEnd/CSS/StyleDescricao.css").toExternalForm());
        cena.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonOK();
                }
            }
        });
        
        //Stage
        window.getIcons().add(DefaultStageIcon.getStageIcon());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Descrição");
        window.setResizable(false);
        window.setScene(cena);
        audioClip.play();
        window.showAndWait();
    }
    
    //Add listeners
    public static void eventButtonOK(){
        window.close();
    }
    //
    
}
