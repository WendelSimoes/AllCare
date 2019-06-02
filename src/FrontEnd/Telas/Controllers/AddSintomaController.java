package FrontEnd.Telas.Controllers;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import FrontEnd.CSS.DefaultStyles;
import FrontEnd.Telas.Models.AddSintomaModel;
import FrontEnd.Telas.Views.AddSintomaView;
import FrontEnd.Telas.Views.NovoJOptionPaneView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddSintomaController {
    
    private AddSintomaModel addSintomaModel;
    private AddSintomaView addSintomaView;
    
    public void build(){
        addSintomaModel = new AddSintomaModel();
        addSintomaModel.atualizar();
        addSintomaView = new AddSintomaView();
        addSintomaView.addButtonAdicionarListener(eventButtonAdicionar());
        addSintomaView.addSceneListener(eventKeyENTER());
    }
    
    public void show(){
        addSintomaView.getWindow().showAndWait();
    }
    
    public EventHandler<ActionEvent> eventButtonAdicionar(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //Ver se o sintoma já não existe no banco
                boolean existe = addSintomaModel.existe(addSintomaView.getFieldSintoma().getText());

                if(existe){
                    addSintomaView.getFieldSintoma().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                }else{
                    addSintomaView.getFieldSintoma().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD());
                }

                if(!existe){
                    if(!addSintomaView.getFieldSintoma().getText().isEmpty()){
                        Banco_de_Valores.empurra_Sintoma(AllCareApplication.getCON().getDeclaracao_de_comandos(), addSintomaModel.getSintoma(addSintomaView.getFieldSintoma().getText()));
                        addSintomaView.getWindow().close();
                        NovoJOptionPaneView.display("Sintoma cadastrado", true);
                    }else{
                        addSintomaView.getFieldSintoma().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                        NovoJOptionPaneView.display("Preencha o campo", false);
                    }
                }else{
                    addSintomaView.getFieldSintoma().setStyle(DefaultStyles.getSTYLE_PADRAO_FIELD_ERRO());
                    NovoJOptionPaneView.display("Sintoma já existente na base de dados", false);
                }
            }
        };
    }
    
    public EventHandler<KeyEvent> eventKeyENTER(){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonAdicionar().handle(null);
                }
            }
        };
    }

    public AddSintomaModel getAddSintomaModel() {
        return addSintomaModel;
    }

    public AddSintomaView getAddSintomaView() {
        return addSintomaView;
    }
    
}
