package FrontEnd.Telas.Controllers;

import FrontEnd.Telas.Models.ApagarSintomasModel;
import FrontEnd.Telas.Views.ApagarSintomasView;
import FrontEnd.Telas.Views.NovoJOptionPaneView;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ApagarSintomasController {
    
    private ApagarSintomasModel apagarSintomasModel;
    private ApagarSintomasView apagarSintomasView;
    
    public void build(){
        apagarSintomasModel = new ApagarSintomasModel();
        apagarSintomasView = new ApagarSintomasView();
        apagarSintomasView.atualizarLista(getItems());
        apagarSintomasView.addButtonApagarListener(eventButtonApagar());
        apagarSintomasView.addSceneListener(eventKeyENTER());
    }
    
    public void show(){
        apagarSintomasView.getWindow().showAndWait();
    }
    
    public ArrayList<CheckBox> getItems(){
        return apagarSintomasModel.getItems();
    }
    
    public EventHandler<ActionEvent> eventButtonApagar(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                ArrayList<CheckBox> boxs = new ArrayList<>();
                for(int i = 0; i < apagarSintomasView.getLayoutCheckBoxs().getChildren().size(); i++){
                    boxs.add((CheckBox) apagarSintomasView.getLayoutCheckBoxs().getChildren().get(i));
                }
                
                boolean temSelecionado = false;
                for(CheckBox boxx : boxs){
                    if(boxx.isSelected()){
                        temSelecionado = true;
                    }
                }
                
                if(temSelecionado){
                    ArrayList <String> sintomas = new ArrayList<>();
                    for(CheckBox box : boxs){
                        if(box.isSelected()){
                            sintomas.add(box.getText());
                        }
                    }

                    apagarSintomasModel.apagar(sintomas);
                    apagarSintomasView.getWindow().close();
                    NovoJOptionPaneView.display("Sintoma(s) removido(s)", true);
                }else{
                    NovoJOptionPaneView.display("Você não selecionou nenhum sintoma", false);
                }
            }
        };
    }
    
    public EventHandler<KeyEvent> eventKeyENTER(){
        return new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    eventButtonApagar().handle(null);
                }
            }
        };
    }

    public ApagarSintomasModel getApagarSintomasModel() {
        return apagarSintomasModel;
    }

    public ApagarSintomasView getApagarSintomasView() {
        return apagarSintomasView;
    }
    
}
