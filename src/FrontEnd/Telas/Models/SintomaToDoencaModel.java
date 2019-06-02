package FrontEnd.Telas.Models;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import allcare.Sintoma;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class SintomaToDoencaModel {
    
    private ArrayList<Sintoma> todosSintomas;
    private ArrayList<Sintoma> sintomasSelecionados;
    
    public SintomaToDoencaModel(){
        todosSintomas = new ArrayList<Sintoma>();
        sintomasSelecionados = new ArrayList<Sintoma>();
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
        sintomasSelecionados = AllCareApplication.getDoencaController().getDoencaModel().getSintomas_escolhidos();
    }
    
    public ArrayList<CheckBox> getItems(){
        ArrayList<CheckBox> items = new ArrayList<CheckBox>();
        for(Sintoma sintoma : todosSintomas){
            CheckBox box = new CheckBox(sintoma.getNome());
            VBox.setMargin(box, new Insets(2, 0, 0, 2));
            for(Sintoma sintomax : sintomasSelecionados){
                if(sintomax.getNome().equalsIgnoreCase(sintoma.getNome())){
                    box.setSelected(true);
                }
            }
            items.add(box);
        }
        
        return items;
    }
    
    public void concluir(ArrayList<String> sintomas){
        sintomasSelecionados.clear();
        for(String sintoma : sintomas){
            for(Sintoma sintomax : todosSintomas){
                if(sintomax.getNome().equalsIgnoreCase(sintoma)){
                    sintomasSelecionados.add(sintomax);
                }
            }
        }
        
        AllCareApplication.getDoencaController().getDoencaModel().setSintomas_escolhidos(sintomasSelecionados);
    }
    
}
