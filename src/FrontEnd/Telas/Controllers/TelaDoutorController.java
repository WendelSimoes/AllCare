package FrontEnd.Telas.Controllers;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import FrontEnd.Telas.Models.TelaDoutorModel;
import FrontEnd.Telas.Views.TelaDoutorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

public class TelaDoutorController {

    private TelaDoutorModel telaDoutorModel;
    private TelaDoutorView telaDoutorView;
    
    public void build(){
        telaDoutorModel = new TelaDoutorModel();
        telaDoutorModel.atualizar();
        telaDoutorView = new TelaDoutorView();
        telaDoutorView.addOnMouseClickedLista(eventMouseCLickedLista());
        telaDoutorView.addButtonAddSintomaListener(eventButtonAddSintoma());
        telaDoutorView.addButtonApagarSintomasListener(eventButtonApagarSintomas());
        telaDoutorView.addButtonAddListener(eventButtonAdd());
        telaDoutorView.addButtonApagarListener(eventButtonApagar());
        telaDoutorView.addButtonEditListener(eventButtonEdit());
        telaDoutorView.addOnCloseRequest(eventWindowClose());
        atualizarLista();
    }
    
    public void show(){
        telaDoutorView.getWindow().show();
    }
    
    public void atualizarLista(){
        telaDoutorView.getListDoencas().getItems().setAll(telaDoutorModel.getItems());
    }
    
    public EventHandler<MouseEvent> eventMouseCLickedLista(){
        return new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(telaDoutorView.getListDoencas().getSelectionModel().getSelectedItems().isEmpty()){
                    telaDoutorView.getButtonApagar().setDisable(true);
                    telaDoutorView.getButtonEdit().setDisable(true);
                }else{
                    telaDoutorView.getButtonApagar().setDisable(false);
                    telaDoutorView.getButtonEdit().setDisable(false);
                }
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonAddSintoma(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                telaDoutorView.getListDoencas().getSelectionModel().clearSelection();
                telaDoutorView.getButtonEdit().setDisable(true);
                telaDoutorView.getButtonApagar().setDisable(true);
                AllCareApplication.getAddSintomaController().build();
                AllCareApplication.getAddSintomaController().show();
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonApagarSintomas(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                telaDoutorView.getListDoencas().getSelectionModel().clearSelection();
                telaDoutorView.getButtonEdit().setDisable(true);
                telaDoutorView.getButtonApagar().setDisable(true);
                AllCareApplication.getApagarSintomaController().build();
                AllCareApplication.getApagarSintomaController().show();
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonAdd(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                telaDoutorView.getListDoencas().getSelectionModel().clearSelection();
                telaDoutorView.getButtonEdit().setDisable(true);
                telaDoutorView.getButtonApagar().setDisable(true);
                AllCareApplication.getDoencaController().build();
                AllCareApplication.getDoencaController().show();
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonApagar(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Banco_de_Valores.deletarDoenca(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas(), telaDoutorView.getListDoencas().getSelectionModel().getSelectedItem());
                telaDoutorView.getButtonApagar().setDisable(true);
                telaDoutorView.getButtonEdit().setDisable(true);
                telaDoutorModel.atualizar();
                atualizarLista();
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonEdit(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AllCareApplication.getDoencaController().build(telaDoutorView.getListDoencas().getSelectionModel().getSelectedItem());
                AllCareApplication.getDoencaController().show();
            }
        };
    }
    
    public EventHandler<WindowEvent> eventWindowClose(){
        return new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                AllCareApplication.getTelaInicialController().show();
            }
        };
    }

    public TelaDoutorModel getTelaDoutorModel() {
        return telaDoutorModel;
    }

    public TelaDoutorView getTelaDoutorView() {
        return telaDoutorView;
    }
    
}
