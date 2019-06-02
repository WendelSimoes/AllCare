package FrontEnd.Telas.Controllers;

import FrontEnd.Telas.Models.SintomaToDoencaModel;
import FrontEnd.Telas.Views.NovoJOptionPaneView;
import FrontEnd.Telas.Views.SintomasToDoencaView;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SintomaToDoencaController {

    private SintomaToDoencaModel sintomaToDoencaModel;
    private SintomasToDoencaView sintomaToDoencaView;
    
    public void build(){
        sintomaToDoencaModel = new SintomaToDoencaModel();
        sintomaToDoencaView = new SintomasToDoencaView();
        sintomaToDoencaView.atualizarLista(getItems());
        sintomaToDoencaView.addButtonConcluirListener(eventButtonConcluir());
        sintomaToDoencaView.addSceneListener(eventKeyENTER());
    }
    
    public void show(){
        sintomaToDoencaView.getWindow().showAndWait();
    }
    
    public ArrayList<CheckBox> getItems(){
        return sintomaToDoencaModel.getItems();
    }
    
    public EventHandler<ActionEvent> eventButtonConcluir(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                ArrayList<CheckBox> boxs = new ArrayList<>();
                for(int i = 0; i < sintomaToDoencaView.getLayoutCheckBoxs().getChildren().size(); i++){
                    boxs.add((CheckBox) sintomaToDoencaView.getLayoutCheckBoxs().getChildren().get(i));
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

                    sintomaToDoencaModel.concluir(sintomas);
                    sintomaToDoencaView.getWindow().close();
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
                    eventButtonConcluir().handle(null);
                }
            }
        };
    }

    public SintomaToDoencaModel getSintomaToDoencaModel() {
        return sintomaToDoencaModel;
    }

    public SintomasToDoencaView getSintomaToDoencaView() {
        return sintomaToDoencaView;
    }
    
}
