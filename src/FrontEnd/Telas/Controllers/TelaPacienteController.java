package FrontEnd.Telas.Controllers;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import FrontEnd.CSS.DefaultStyles;
import FrontEnd.Telas.Models.TelaPacienteModel;
import FrontEnd.Telas.Views.MostrarDescricaoView;
import FrontEnd.Telas.Views.TelaPacienteView;
import allcare.Doenca;
import allcare.Sintoma;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

public class TelaPacienteController {
    
    private TelaPacienteModel telaPacienteModel;
    private TelaPacienteView telaPacienteView;  
    private ArrayList<CheckBox> boxs;
    
    public void build(){
        telaPacienteModel = new TelaPacienteModel();
        telaPacienteView = new TelaPacienteView();
        telaPacienteView.addFieldSearchListener(eventFieldSearch());
        telaPacienteView.addListDoencasListener(eventListDoencas());
        telaPacienteView.addOnCloseRequest(eventWindowClose());
        produzirCheckBoxs();
        telaPacienteView.preencherLista(boxs);
    }
    
    public void show(){
        telaPacienteView.getWindow().show();
    }
    
    public ChangeListener<String> eventFieldSearch(){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                telaPacienteView.preencherLista(boxs);
                if(telaPacienteView.getFieldSearch().getText().isEmpty()){
                    telaPacienteView.preencherLista(boxs);
                }else{
                    for(CheckBox box : boxs){
                        if(!box.getText().toLowerCase().startsWith(telaPacienteView.getFieldSearch().getText().toLowerCase()) || !box.getText().toUpperCase().startsWith(telaPacienteView.getFieldSearch().getText().toUpperCase())){
                            telaPacienteView.getLayoutCheckBoxs().getChildren().remove(box);
                        }
                    }
                }
                if(telaPacienteView.getLayoutCheckBoxs().getChildren().isEmpty()){
                    telaPacienteView.getFieldSearch().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                }else{
                    telaPacienteView.getFieldSearch().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                }
            }
        };
    }
    
    public EventHandler<MouseEvent> eventListDoencas(){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String doencaNome = telaPacienteView.getListDoencas().getSelectionModel().getSelectedItem();
                for(Doenca doencax : telaPacienteModel.getTodasDoencas()){
                    if(doencax.getNome().equalsIgnoreCase(doencaNome)){
                        MostrarDescricaoView.display(doencax.getDescricao());
                        telaPacienteView.getListDoencas().getSelectionModel().clearSelection();
                        break;
                    }
                }
            }
        };
    }
    
    public void atualizarListaDoencas(){
        telaPacienteView.getListDoencas().setDisable(false);
        
        ArrayList<String> sintomas = new ArrayList<>();
        for(CheckBox box : boxs){
            if(box.isSelected()){
                sintomas.add(box.getText());
            }else{
                sintomas.remove(box.getText());
            }
        }
        
        telaPacienteModel.getSintomas_escolhidos().clear();
        for(String nome : sintomas){
            for(Sintoma sintomax : telaPacienteModel.getTodosSintomas()){
                if(sintomax.getNome().equalsIgnoreCase(nome)){
                    telaPacienteModel.getSintomas_escolhidos().add(sintomax);
                }
            }
        }
        
        telaPacienteModel.setDoencas(Banco_de_Valores.procura_Doencas(telaPacienteModel.getSintomas_escolhidos(), AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas()));
        ArrayList<String> nomeDoencas = new ArrayList<>();
        for(Doenca doenca : telaPacienteModel.getDoencas()){
            nomeDoencas.add(doenca.getNome());
        }
        telaPacienteView.getListDoencas().getItems().setAll(nomeDoencas);
        if(telaPacienteView.getListDoencas().getItems().isEmpty() && !sintomas.isEmpty()){
            telaPacienteView.getListDoencas().getItems().add("Nada encontrado!");
            telaPacienteView.getListDoencas().setDisable(true);
        }
    }
    
    public void produzirCheckBoxs(){
        boxs = new ArrayList<CheckBox>();
        for(Sintoma todos : telaPacienteModel.getTodosSintomas()){
            CheckBox box = new CheckBox(todos.getNome());
            VBox.setMargin(box, new Insets(2, 0, 0, 2));
            box.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    atualizarListaDoencas();
                }
            });
            boxs.add(box);
        }
    }
    
    public EventHandler<WindowEvent> eventWindowClose(){
        return new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                AllCareApplication.getTelaInicialController().show();
            }
        };
    }
    
}
