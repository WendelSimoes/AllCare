package FrontEnd.Telas.Models;

import DataBase.Banco_de_Valores;
import FrontEnd.AllCareApplication;
import allcare.Sintoma;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class ApagarSintomasModel {
    
    //ArrayList de todos os sintomas, para carregar a lista de sintomas disponiveis para exclusão
    private ArrayList<Sintoma> todosSintomas;
    //ArrayList de sintomas escolhidos para exclusão
    private ArrayList<Sintoma> sintomasSelecionados;
    
    //Contrutor preenchendo os sintomas existentes para exclusão
    public ApagarSintomasModel(){
        todosSintomas = new ArrayList<Sintoma>();
        sintomasSelecionados = new ArrayList<Sintoma>();
        todosSintomas = Banco_de_Valores.puxa_Sintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), AllCareApplication.getCON().getResult_consultas());
    }
    
    //Obtenção de checkBox's com nome dos sintomas para preencher a lista na view
    public ArrayList<CheckBox> getItems(){
        ArrayList<CheckBox> items = new ArrayList<CheckBox>();
        for(Sintoma sintoma : todosSintomas){
            CheckBox box = new CheckBox(sintoma.getNome());
            VBox.setMargin(box, new Insets(2, 0, 0, 2));
            items.add(box);
        }
        
        return items;
    }
    
    //Apagar sintomas do banco a partir de um arrayList de strings
    public void apagar(ArrayList<String> sintomas){
        for(String sintoma : sintomas){
            for(Sintoma sintomax : todosSintomas){
                if(sintomax.getNome().equalsIgnoreCase(sintoma)){
                    sintomasSelecionados.add(sintomax);
                }
            }
        }
        
        Banco_de_Valores.apagarSintomas(AllCareApplication.getCON().getDeclaracao_de_comandos(), sintomasSelecionados);
    }
    
}
