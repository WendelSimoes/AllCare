package FrontEnd.Telas.Controllers;

import FrontEnd.AllCareApplication;
import DataBase.Login;
import FrontEnd.CSS.DefaultStyles;
import FrontEnd.Telas.Models.TelaInicialModel;
import FrontEnd.Telas.Views.NovoJOptionPaneView;
import FrontEnd.Telas.Views.OhayoGoodByeView;
import FrontEnd.Telas.Views.TelaInicialView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TelaInicialController {
    
    private TelaInicialModel telaInicialModel;
    private TelaInicialView telaInicialView;
    
    public void build(Stage window){
        telaInicialModel = new TelaInicialModel();
        telaInicialView = new TelaInicialView(window);
        telaInicialView.addButtonEntrarListener(eventButtonEntrar());
        telaInicialView.addButtonCadastrarListener(eventButtonCadastrar());
        telaInicialView.addSceneListener(eventKeyENTER());
        telaInicialView.addOnCloseRequest(eventWindowClose());
    }
    
    public void show(){
        telaInicialView.getWindow().show();
    }
    
    public void hide(){
        telaInicialView.getWindow().hide();
    }
    
    public EventHandler<ActionEvent> eventButtonEntrar(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(telaInicialView.getRadioPatient().isSelected() && !telaInicialView.getFieldUser().getText().isEmpty() && !telaInicialView.getFieldPass().getText().isEmpty()){
                    if(Login.Logar(telaInicialModel.getPaciente(telaInicialView.getFieldUser().getText(), telaInicialView.getFieldPass().getText()), AllCareApplication.getCON().getDeclaracao_de_comandos(),AllCareApplication.getCON().getResult_consultas())){
                        telaInicialView.getRadioPatient().setSelected(true);
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        telaInicialView.getFieldUser().setText("");
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        telaInicialView.getFieldPass().setText("");
                        AllCareApplication.getTelaInicialController().hide();     
                        AllCareApplication.getTelaPacienteController().build();
                        AllCareApplication.getTelaPacienteController().show();
                        NovoJOptionPaneView.display("Login bem sucedido", true);
                    }else{
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        NovoJOptionPaneView.display("Usuário ou senha não reconhecidos", false);
                    }
                } else if(telaInicialView.getRadioDoctor().isSelected() && !telaInicialView.getFieldUser().getText().isEmpty() && !telaInicialView.getFieldPass().getText().isEmpty()){
                    if(Login.Logar(telaInicialModel.getMedico(telaInicialView.getFieldUser().getText(), telaInicialView.getFieldPass().getText()), AllCareApplication.getCON().getDeclaracao_de_comandos(),AllCareApplication.getCON().getResult_consultas())){
                        telaInicialView.getRadioPatient().setSelected(true);
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        telaInicialView.getFieldUser().setText("");
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        telaInicialView.getFieldPass().setText("");
                        AllCareApplication.getTelaInicialController().hide();
                        AllCareApplication.getTelaDoutorController().build();
                        AllCareApplication.getTelaDoutorController().show();
                        NovoJOptionPaneView.display("Login bem sucedido", true);
                    }else{
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        NovoJOptionPaneView.display("Usuário ou senha não reconhecidos", false);
                    }
                } else if(telaInicialView.getFieldUser().getText().isEmpty() || telaInicialView.getFieldPass().getText().isEmpty()){
                    if(telaInicialView.getFieldUser().getText().isEmpty()){
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    if(telaInicialView.getFieldPass().getText().isEmpty()){
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    NovoJOptionPaneView.display("Preencha todos os campos", false);
                }
            }
        };
    }
    
    public EventHandler<ActionEvent> eventButtonCadastrar(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String errorDuplicate = "";

                if(telaInicialView.getFieldUser().getText().isEmpty() || telaInicialView.getFieldPass().getText().isEmpty()){
                    if(telaInicialView.getFieldUser().getText().isEmpty()){
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    if(telaInicialView.getFieldPass().getText().isEmpty()){
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    }else{
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                    }
                    NovoJOptionPaneView.display("Preencha todos os campos", false);
                }

                if(telaInicialView.getRadioPatient().isSelected() && !telaInicialView.getFieldUser().getText().isEmpty() && !telaInicialView.getFieldPass().getText().isEmpty()){
                    errorDuplicate = Login.Cadastro(telaInicialModel.getPaciente(telaInicialView.getFieldUser().getText(), telaInicialView.getFieldPass().getText()), AllCareApplication.getCON().getDeclaracao_de_comandos());
                    if(errorDuplicate.isEmpty()){               
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        NovoJOptionPaneView.display("Cadastro efetuado", true);
                    }else{
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        NovoJOptionPaneView.display("Nome de usuário já utilizado", false);
                    }
                }
                if(telaInicialView.getRadioDoctor().isSelected() && !telaInicialView.getFieldUser().getText().isEmpty() && !telaInicialView.getFieldPass().getText().isEmpty()){
                    errorDuplicate = Login.Cadastro(telaInicialModel.getMedico(telaInicialView.getFieldUser().getText(), telaInicialView.getFieldPass().getText()), AllCareApplication.getCON().getDeclaracao_de_comandos());
                    if(errorDuplicate.isEmpty()){
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        telaInicialView.getFieldPass().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                        NovoJOptionPaneView.display("Cadastro efetuado", true);
                    }else{
                        telaInicialView.getFieldUser().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        NovoJOptionPaneView.display("Nome de usuário já utilizado", false);
                    }
                }
            }
        };
    }
    
    public EventHandler<KeyEvent> eventKeyENTER(){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonEntrar().handle(null);
                }
            }
        };
    }
    
    public EventHandler<WindowEvent> eventWindowClose(){
        return new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                OhayoGoodByeView.display(false);
            }
        };
    }

    public TelaInicialModel getTelaInicialModel() {
        return telaInicialModel;
    }

    public TelaInicialView getTelaInicialView() {
        return telaInicialView;
    }
    
}
