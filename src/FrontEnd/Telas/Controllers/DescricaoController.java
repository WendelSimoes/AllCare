package FrontEnd.Telas.Controllers;

import FrontEnd.CSS.DefaultStyles;
import FrontEnd.Telas.Models.DescricaoModel;
import FrontEnd.Telas.Views.DescricaoView;
import FrontEnd.Telas.Views.NovoJOptionPaneView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DescricaoController {
    
    DescricaoModel descricaoModel;
    DescricaoView descricaoView;
    
    public void build(){
        descricaoModel = new DescricaoModel();
        descricaoView = new DescricaoView();
        //Se já há uma descrição feita, atribuir aos campos para edição
        if(!descricaoModel.getDescricao().isEmpty()){
            atualizarGeral();
        }
        descricaoView.addButtonConcluirListener(eventButtonConcluir());
        descricaoView.addSceneListener(eventKeyENTER());
    }
    
    public void show(){
        descricaoView.getWindow().showAndWait();
    }
    
    //Gambiarra ao concluir para pegar todos os campos e fundir numa string só
    public EventHandler<ActionEvent> eventButtonConcluir(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String descricao;
                if(!descricaoView.getFieldPropagacao().getText().isEmpty() && !descricaoView.getFieldTratamento().getText().isEmpty() 
                    && !descricaoView.getFieldDuracao().getText().isEmpty() && descricaoView.getBoxDiagnosticavel().getSelectionModel().getSelectedIndex() > -1
                    && !descricaoView.getFieldMedicos().getText().isEmpty() && !descricaoView.getAreaDescricaoGeral().getText().isEmpty()){
                    descricao = "Propagação: " + descricaoView.getFieldPropagacao().getText() + "\n" +
                            "Tratamento: " + descricaoView.getFieldTratamento().getText() + "\n" +
                            "Duração: " + descricaoView.getFieldDuracao().getText() + "\n" +
                            "Diagnosticável: " + descricaoView.getBoxDiagnosticavel().getSelectionModel().getSelectedItem().toString() + "\n" +
                            "Médico(s): " + descricaoView.getFieldMedicos().getText() + "\n" +
                            "Descrição geral: " + descricaoView.getAreaDescricaoGeral().getText();
                    descricaoModel.concluir(descricao);
                    descricaoView.getWindow().close();
                    descricaoView.getFieldPropagacao().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    descricaoView.getFieldTratamento().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    descricaoView.getFieldDuracao().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    descricaoView.getFieldMedicos().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    descricaoView.getCena().getStylesheets().addAll(DescricaoController.class.getResource("/FrontEnd/CSS/StyleTextArea.css").toExternalForm());
                }else{
                    if(descricaoView.getFieldPropagacao().getText().isEmpty()){
                        descricaoView.getFieldPropagacao().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        descricaoView.getFieldPropagacao().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    if(descricaoView.getFieldTratamento().getText().isEmpty()){
                        descricaoView.getFieldTratamento().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        descricaoView.getFieldTratamento().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    if(descricaoView.getFieldDuracao().getText().isEmpty()){
                        descricaoView.getFieldDuracao().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        descricaoView.getFieldDuracao().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    if(descricaoView.getFieldMedicos().getText().isEmpty()){
                        descricaoView.getFieldMedicos().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        descricaoView.getFieldMedicos().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    if(descricaoView.getAreaDescricaoGeral().getText().isEmpty()){
                        descricaoView.getCena().getStylesheets().setAll(DescricaoController.class.getResource("/FrontEnd/CSS/StyleTextAreaErro.css").toExternalForm());
                    }else{
                        descricaoView.getCena().getStylesheets().setAll(DescricaoController.class.getResource("/FrontEnd/CSS/StyleTextArea.css").toExternalForm());
                    }
                    NovoJOptionPaneView.display("Preencha todos os campos", false);
                }
            }
        };
    }
    
    public EventHandler<KeyEvent> eventKeyENTER(){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonConcluir().handle(null);
                }
            }
        };
    }
    
    public void atualizarGeral(){
        String descricao = descricaoModel.getDescricao();
        int i, z;
        
        i = descricao.indexOf("Propagação") + 12;
        z = descricao.indexOf("Tratamento") - 1;
        descricaoView.atualizarFieldPropagacao(descricao.substring(i, z));
        
        i = descricao.indexOf("Tratamento") + 12;
        z = descricao.indexOf("Duração") - 1;
        descricaoView.atualizarFieldTratamento(descricao.substring(i, z));
        
        i = descricao.indexOf("Duração") + 9;
        z = descricao.indexOf("Diagnosticável") - 1;
        descricaoView.atualizarFieldDuracao(descricao.substring(i, z));
        
        i = descricao.indexOf("Diagnosticável") + 16;
        z = descricao.indexOf("Médico(s)") - 1;
        if(descricao.substring(i, z).equalsIgnoreCase("Sim")){
            descricaoView.atualizarBoxDiagnosticavel(0);
        }else{
            descricaoView.atualizarBoxDiagnosticavel(1);
        }
        
        i = descricao.indexOf("Médico(s)") + 11;
        z = descricao.indexOf("Descrição geral") - 1;
        descricaoView.atualizarFieldMedicos(descricao.substring(i, z));
        
        i = descricao.indexOf("Descrição geral") + 17;
        descricaoView.atualizarAreaDescricaoGeral(descricao.substring(i));
    }
    
}
