package FrontEnd.Telas.Controllers;

import FrontEnd.AllCareApplication;
import FrontEnd.CSS.DefaultStyles;
import FrontEnd.Telas.Models.DoencaModel;
import FrontEnd.Telas.Views.DoencaView;
import FrontEnd.Telas.Views.NovoJOptionPaneView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DoencaController {
    
    private DoencaModel doencaModel;
    private DoencaView doencaView;
    
    //Build para adição de doença
    public void build(){
        doencaModel = new DoencaModel();
        doencaView = new DoencaView();
        doencaView.addButtonAddSintomasListener(eventButtonAddSintomas());
        doencaView.addButtonDescricaoListener(eventButtonDescricao());
        doencaView.addButtonFinalizarListener(eventButtonFinalizar());
        doencaView.addSceneListener(eventKeyENTER());
    }
    
    //Build para edição de doença, pegando o nome, sintomas e descrição antigos
    public void build(String doencaSelecionada){
        doencaModel = new DoencaModel(doencaSelecionada);
        doencaView = new DoencaView();
        doencaView.addButtonAddSintomasListener(eventButtonAddSintomas());
        doencaView.addButtonDescricaoListener(eventButtonDescricao());
        doencaView.addButtonFinalizarListener(eventButtonFinalizar());
        doencaView.addSceneListener(eventKeyENTER());
        doencaView.getFieldDoenca().setText(doencaModel.getDoencaSelecionada());
    }
    
    public void show(){
        doencaView.getWindow().showAndWait();
    }
    
    public EventHandler<ActionEvent> eventButtonAddSintomas(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllCareApplication.getSintomaToDoencaController().build();
                AllCareApplication.getSintomaToDoencaController().show();
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonDescricao(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AllCareApplication.getDescricaoController().build();
                AllCareApplication.getDescricaoController().show();
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonFinalizar(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Verificar existencia
                if(doencaModel.verificarExistencia(doencaView.getFieldDoenca().getText())){
                    doencaView.getFieldDoenca().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    NovoJOptionPaneView.display("Doença já existente na base de dados", false);
                    return;
                }else{
                    doencaView.getFieldDoenca().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                }
                
                //Finish
                if(!doencaView.getFieldDoenca().getText().isEmpty() && !doencaModel.getSintomas_escolhidos().isEmpty() && !doencaModel.getDescricao().isEmpty()){
                    if(doencaModel.getDoencaSelecionada().isEmpty()){
                        doencaModel.adicionar(doencaView.getFieldDoenca().getText());
                        doencaView.getWindow().close();
                        AllCareApplication.getTelaDoutorController().getTelaDoutorView().getButtonApagar().setDisable(true);
                        AllCareApplication.getTelaDoutorController().getTelaDoutorView().getButtonEdit().setDisable(true);
                        AllCareApplication.getTelaDoutorController().getTelaDoutorModel().atualizar();
                        AllCareApplication.getTelaDoutorController().atualizarLista();
                        NovoJOptionPaneView.display("Doença cadastrada", true);
                    }else{
                        doencaModel.editar(doencaView.getFieldDoenca().getText());
                        doencaView.getWindow().close();
                        AllCareApplication.getTelaDoutorController().getTelaDoutorView().getButtonApagar().setDisable(true);
                        AllCareApplication.getTelaDoutorController().getTelaDoutorView().getButtonEdit().setDisable(true);
                        AllCareApplication.getTelaDoutorController().getTelaDoutorModel().atualizar();
                        AllCareApplication.getTelaDoutorController().atualizarLista();
                        NovoJOptionPaneView.display("Doença modificada", true);
                    }
                }else{
                    if(doencaView.getFieldDoenca().getText().isEmpty()){
                        doencaView.getFieldDoenca().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        doencaView.getFieldDoenca().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    NovoJOptionPaneView.display("Preencha todos os dados", false);
                }
            }
        };
    }
    
    public EventHandler<KeyEvent> eventKeyENTER(){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonFinalizar().handle(null);
                }
            }
        };
    }

    public DoencaModel getDoencaModel() {
        return doencaModel;
    }

    public DoencaView getDoencaView() {
        return doencaView;
    }
    
}
